package com.mswim.architecture.viper;

import android.support.annotation.UiThread;

/**
 * Created by marcogalicia on 25/10/16.
 */

public interface ViperPresenter<V extends ViperView, R extends Router> {

    @UiThread
    void attachRouter(R view);

    @UiThread
    void detachRouter();

    void onFinish();

}
