package com.mswim.architecture.viper;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * Created by marcogalicia on 25/10/16.
 */

public class BasePresenter<V extends ViperView, R extends Router> implements ViperPresenter<V, R> {
    private WeakReference<R> viewRefRouter;

    @Override
    public void attachRouter(R view) {
        viewRefRouter = new WeakReference<>(view);
    }

    @Override
    public void detachRouter() {
        if (viewRefRouter != null) {
            viewRefRouter.clear();
            viewRefRouter = null;
        }
    }

    @UiThread
    @Nullable
    public R getRouter() {
        return viewRefRouter == null ? null : viewRefRouter.get();
    }

    @UiThread
    public boolean isRouterAttached() {
        return viewRefRouter != null && viewRefRouter.get() != null;
    }

    @Override
    public void onFinish() {

    }
}
