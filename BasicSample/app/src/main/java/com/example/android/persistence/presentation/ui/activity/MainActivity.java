package com.example.android.persistence.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.persistence.R;
import com.example.android.persistence.Viper.DemoProtocol;
import com.example.android.persistence.domain.interactor.DemoInteractorImp;
import com.example.android.persistence.Viper.MainViperModule;
import com.example.android.persistence.presentation.presenter.DemoPresenter;
import com.example.android.persistence.presentation.view.MainView;
import com.mswim.architecture.BaseActivity;

public class MainActivity extends BaseActivity<MainView, DemoProtocol.RouterInt, MainViperModule> implements MainView, DemoProtocol.RouterInt {

    private TextView txtView;
    private Button btnView;
    private Button btnRouterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.text_main);
        btnView = findViewById(R.id.btn_go);
        btnRouterView = findViewById(R.id.btn_router);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getViperModule().getPresenter().getDatas();
            }
        });

        btnRouterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getViperModule().getPresenter().routerNextScreen();
            }
        });
    }

    @Override
    protected void onViperModulePrepared(MainViperModule presenter) {

    }

    //Important!!!!!!!!!! I do not like this part of LoaderManager because
    //now in every Activity/Fragment we have to set an Id. We could do this in a static class :)
    @Override
    protected int loaderId() {
        return 1;
    }

    @NonNull
    @Override
    public MainViperModule createViperModule() {
        DemoPresenter presenter = new DemoPresenter();
        DemoInteractorImp interactor = new DemoInteractorImp();
        interactor.setPresenterInt(presenter);
        presenter.setInteractorInt(interactor);
        MainViperModule mainViperModule = new MainViperModule();
        mainViperModule.setPresenter(presenter);
        mainViperModule.setInteractor(interactor);
        return mainViperModule;
    }


    @Override
    protected void onResume() {
        super.onResume();
        getViperModule().getPresenter().attachView(this);
        getViperModule().getPresenter().attachRouter(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getViperModule().getPresenter().detachView();
        getViperModule().getPresenter().detachRouter();
    }

    @Override
    public void showDatas(String data) {
        txtView.setText(data);
    }

    @Override
    public void goNextScreen() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }

}
