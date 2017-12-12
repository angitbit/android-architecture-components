package com.mswim.architecture.viper;

import android.support.annotation.UiThread;

/**
 * Created by marcogalicia on 25/10/16.
 */

public interface BaseViperView<M> extends ViperView {

    @UiThread
    void showDatas(M data);

}
