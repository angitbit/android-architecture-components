package com.mswim.architecture;

import android.content.Context;
import android.content.Loader;
import android.util.Log;

import com.mswim.architecture.viper.ViperModule;
import com.mswim.architecture.viper.ViperView;

/**
 * Created by marcogalicia on 19/03/17.
 */

public final class PresenterLoader<V extends ViperView,M extends ViperModule> extends Loader<M> {

    private M viperModule;
    private int count=0;

    public PresenterLoader(Context context,M viperModule) {
        super(context);
        this.viperModule=viperModule;
    }

    @Override
    protected void onStartLoading() {
        Log.i("loader", "onStartLoading-count: "+count++);

        // if we already own a presenter instance, simply deliver it.
        if (viperModule != null) {
            deliverResult(viperModule);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.i("loader", "onForceLoad-");
        // Deliver the result
        deliverResult(viperModule);
    }

    @Override
    public void deliverResult(M data) {
        super.deliverResult(data);
        Log.i("loader", "deliverResult-");
    }

    @Override
    protected void onStopLoading() {
        Log.i("loader", "onStopLoading-");
    }

    public M getRouter() {
        return viperModule;
    }

    @Override
    protected void onReset() {
        Log.i("loader", "onReset-" );
        if (viperModule != null) {
            viperModule = null;
        }
    }
}