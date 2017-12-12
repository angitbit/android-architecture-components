package com.example.android.persistence.presentation.presenter;

import com.example.android.persistence.Viper.DemoProtocol;
import com.example.android.persistence.presentation.view.MainView;
import com.mswim.architecture.viper.BasePresenter;

/**
 * Created by marcogalicia on 01/05/17.
 */

public class DemoPresenter extends BasePresenter<MainView, DemoProtocol.RouterInt> implements DemoProtocol.Presenter, DemoProtocol.InteractorOutput {

    private DemoProtocol.InteractorInput interactorInt;

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
