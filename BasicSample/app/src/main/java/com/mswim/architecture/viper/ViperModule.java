package com.mswim.architecture.viper;

/**
 * Created by marcogalicia on 12/8/17.
 */

public class ViperModule<P extends BasePresenter, I> {

    private P presenter;
    private I interactor;

    public P getPresenter() {
        return presenter;
    }

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public I getInteractor() {
        return interactor;
    }

    public void setInteractor(I interactor) {
        this.interactor = interactor;
    }
}
