package com.example.android.persistence.domain.interactor;

import com.example.android.persistence.Viper.DemoProtocol;

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

//    private final CompositeDisposable disposables = new CompositeDisposable();
    private DemoProtocol.InteractorOutput presenterInt;

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

    public DemoProtocol.InteractorOutput getPresenterInt() {
        return presenterInt;
    }

    public void setPresenterInt(DemoProtocol.InteractorOutput presenterInt) {
        this.presenterInt = presenterInt;
    }
}
