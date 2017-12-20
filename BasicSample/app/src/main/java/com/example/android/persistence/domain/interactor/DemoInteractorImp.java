package com.example.android.persistence.domain.interactor;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.android.persistence.Viper.DemoProtocol;
import com.example.android.persistence.databinding.ListFragmentBinding;
import com.example.android.persistence.db.entity.ProductEntity;
import com.example.android.persistence.viewmodel.ProductListViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.observers.DisposableObserver;
//import io.reactivex.schedulers.Schedulers;


/**
 * Created by marcogalicia on 01/05/17.
 */

public class DemoInteractorImp implements DemoProtocol.InteractorInput {
    private DemoProtocol.AacInteractorOutput mAacInteractorOutput;
    private ListFragmentBinding mBinding;

//    private final CompositeDisposable disposables = new CompositeDisposable();
//    private DemoProtocol.InteractorOutput presenterInt;

//    private DemoProtocol.AacInteractorOutput mAacInteractorOutput;
//
//    public void setmAacInteractorOutput(DemoProtocol.AacInteractorOutput aacInteractorOutput){
//        mAacInteractorOutput= aacInteractorOutput;
//    }

    public DemoInteractorImp() {
    }

//    private Observable<? extends Long> getObservable() {
//        return Observable.interval(0, 2, TimeUnit.SECONDS);
//    }
//
//    private DisposableObserver<Long> getObserver() {
//        return new DisposableObserver<Long>() {
//
//            @Override
//            public void onNext(Long value) {
//                getPresenterInt().didRetrieveDatas("Value: " + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                getPresenterInt().didRetrieveDatas("Complete!!!");
//            }
//        };
//    }

    public void removeSubscription() {
//        disposables.clear();
    }

    public void executeTask() {
//        disposables.add(getObservable()
//                // Run on a background thread
//                .subscribeOn(Schedulers.io())
//                // Be notified on the main thread
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(getObserver()));

        //pretend task will update AppDatabase in DataRepository, which will push livedata to view
    }

//    public DemoProtocol.InteractorOutput getPresenterInt() {
//        return presenterInt;
//    }
//
//    public void setPresenterInt(DemoProtocol.InteractorOutput presenterInt) {
//        this.presenterInt = presenterInt;
//    }

    public void bindData(LifecycleOwner lifecycleOwner, ListFragmentBinding listFragmentBinding, DemoProtocol.AacInteractorOutput aacInteractorOutput,
                         LiveData<List<ProductEntity>> liveProdList){
        if(listFragmentBinding== null){
            return;
        }
        mBinding= listFragmentBinding;
        if(lifecycleOwner!=null && liveProdList!=null){
            subscribeUi(liveProdList, lifecycleOwner);
        }
        mAacInteractorOutput= aacInteractorOutput;
    }

    private void subscribeUi(LiveData<List<ProductEntity>> liveProdList, LifecycleOwner lifecycleOwner) {
        // Update the list when the data changes
        liveProdList.observe(lifecycleOwner, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> myProducts) {
                if (myProducts != null) {
                    mBinding.setIsLoading(false); //xml data var
                    if(mAacInteractorOutput != null){
                        mAacInteractorOutput.setProductList(myProducts);
                    }
                } else {
                    mBinding.setIsLoading(true); //xml data var
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

}
