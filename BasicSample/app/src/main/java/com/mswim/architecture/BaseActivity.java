package com.mswim.architecture;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mswim.architecture.viper.Router;
import com.mswim.architecture.viper.ViperDelegateCallback;
import com.mswim.architecture.viper.ViperModule;
import com.mswim.architecture.viper.ViperView;

public abstract class BaseActivity<V extends ViperView, R extends Router, M extends ViperModule>
        extends AppCompatActivity implements ViperDelegateCallback<V, R, M>, ViperView {
    private final String TAG = "BaseActivity";

    protected M viperModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<M>() {
            @Override
            public final Loader<M> onCreateLoader(int id, Bundle args) {
                Log.i(TAG, "onCreateLoader");
                return new PresenterLoader<>(BaseActivity.this, createViperModule());
            }

            @Override
            public final void onLoadFinished(Loader<M> loader, M routerImp) {
                Log.i(TAG, "onLoadFinished");
                BaseActivity.this.viperModule = routerImp;
                onViperModulePrepared(routerImp);
            }

            @Override
            public final void onLoaderReset(Loader<M> loader) {
                Log.i(TAG, "onLoaderReset");
                BaseActivity.this.viperModule = null;
                onPresenterDestroyed();
            }
        });
    }

    protected void onPresenterDestroyed() {
    }

    protected abstract void onViperModulePrepared(M presenter);

    protected abstract int loaderId();

    @NonNull
    @Override
    public abstract M createViperModule();

    @Override
    public R getRouter() {
        return (R) this;
    }

    @Override
    public M getViperModule() {
        return viperModule;
    }

    @NonNull
    @Override
    public V getViperView() {
        return (V) this;
    }

    @Override
    public void finish() {
        if (getViperModule() != null)
            getViperModule().getPresenter().onFinish();
        super.finish();
    }
}
