package com.example.android.persistence.presentation.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.android.persistence.Viper.DemoProtocol;
import com.example.android.persistence.databinding.ListFragmentBinding;
import com.example.android.persistence.db.entity.ProductEntity;
import com.example.android.persistence.model.Product;
import com.example.android.persistence.presentation.view.MainView;
import com.example.android.persistence.viewmodel.ProductListViewModel;
import com.mswim.architecture.viper.BasePresenter;

import java.util.List;

/**
 * Created by marcogalicia on 01/05/17.
 */

public class DemoPresenter extends BasePresenter<MainView, DemoProtocol.RouterInt> implements DemoProtocol.Presenter, DemoProtocol.InteractorOutput,
        DemoProtocol.AacInteractorOutput {

    private DemoProtocol.InteractorInput interactorInt;

    private DemoProtocol.AacInteractorOutput mAacInteractorOutput;
    private ProductListViewModel mproductListViewModel;
    private ListFragmentBinding mBinding;

    public void initVm(DemoProtocol.View view){
        if(view== null){
            return;
        }
        mproductListViewModel = ViewModelProviders.of(view.getViewModelFragment()).get(ProductListViewModel.class);
    }
    public void bindData(LifecycleOwner lifecycleOwner, ListFragmentBinding listFragmentBinding, DemoProtocol.AacInteractorOutput aacInteractorOutput){
        if(listFragmentBinding== null){
            return;
        }
        mBinding= listFragmentBinding;
        if(mproductListViewModel!=null){
            subscribeUi(mproductListViewModel, lifecycleOwner);
        }
        mAacInteractorOutput= aacInteractorOutput;
    }

    private void subscribeUi(ProductListViewModel viewModel, LifecycleOwner lifecycleOwner) {
        // Update the list when the data changes
        viewModel.getProducts().observe(lifecycleOwner, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> myProducts) {
                if (myProducts != null) {
                    mBinding.setIsLoading(false); //xml data var
                    setProductList(myProducts);
                } else {
                    mBinding.setIsLoading(true); //xml data var
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    public void setProductList(final List<? extends Product> productList) {
        if(mAacInteractorOutput != null){
            mAacInteractorOutput.setProductList(productList);
        }
    }

    public DemoPresenter() {
    }

    @Override
    public void getDatas() {
        getInteractorInt().executeTask();
    }

    public void routerNextScreen() {
        if (isViewAttached())
            getRouter().goNextScreen();
    }

    //This method only is executed when the activity is completely destroyed.
    //onDestroy could be called onConfigurationChanged
    @Override
    public void onFinish() {
        if (getInteractorInt() != null)
            getInteractorInt().removeSubscription();
        super.onFinish();
    }

    @Override
    public void didRetrieveDatas(String data) {
        if (isViewAttached())
            getView().showDatas(data);
    }

    public DemoProtocol.InteractorInput getInteractorInt() {
        return interactorInt;
    }

    public void setInteractorInt(DemoProtocol.InteractorInput interactorInt) {
        this.interactorInt = interactorInt;
    }

}
