package com.mswim.architecture.viper;

import android.support.annotation.NonNull;

/**
 * Created by marcogalicia on 25/10/16.
 */

public interface ViperDelegateCallback<V extends ViperView,R extends Router, M extends ViperModule> {
    @NonNull
    public M createViperModule();
    public M getViperModule();
    public R getRouter();
    public V getViperView();
}
