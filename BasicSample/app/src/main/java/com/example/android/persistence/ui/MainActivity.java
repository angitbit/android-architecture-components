/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.persistence.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.persistence.R;
import com.example.android.persistence.Viper.DemoProtocol;
import com.example.android.persistence.Viper.MainViperModule;
import com.example.android.persistence.domain.interactor.DemoInteractorImp;
import com.example.android.persistence.model.Product;
import com.example.android.persistence.presentation.presenter.DemoPresenter;
import com.example.android.persistence.presentation.view.MainView;
import com.mswim.architecture.BaseActivity;

public class MainActivity extends BaseActivity<MainView, DemoProtocol.RouterInt, MainViperModule> implements MainView, DemoProtocol.RouterInt {
    private ProductListFragment productListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            makeListFrag();
        } else {
            productListFragment = (ProductListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        }
    }

    private void makeListFrag(){
        ProductListFragment fragment = new ProductListFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, ProductListFragment.TAG).commit();

        productListFragment = fragment;
    }

    @NonNull
    @Override
    public MainViperModule createViperModule() {
        DemoPresenter presenter = new DemoPresenter();
        DemoInteractorImp interactor = new DemoInteractorImp();
//        interactor.setPresenterInt(presenter);
        presenter.setInteractorInt(interactor);

        MainViperModule mainViperModule = new MainViperModule();
        mainViperModule.setPresenter(presenter);
        mainViperModule.setInteractor(interactor);

        if(productListFragment==null){
            makeListFrag();
        }
        if(productListFragment!=null){
            productListFragment.initViper(mainViperModule);
        }
        return mainViperModule;
    }


    @Override
    protected void onViperModulePrepared(MainViperModule presenter) {
    }
    @Override
    protected int loaderId() {
        return 0;
    }

    /** Shows the product detail fragment */
    public void show(Product product) {

        ProductFragment productFragment = ProductFragment.forProduct(product.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container,
                        productFragment, null).commit();
    }

    @Override
    public void showDatas(String data) {

    }

    @Override
    public void goNextScreen() {

    }
}
