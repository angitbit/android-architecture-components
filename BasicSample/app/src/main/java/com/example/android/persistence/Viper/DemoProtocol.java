package com.example.android.persistence.Viper;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.support.v4.app.Fragment;

import com.example.android.persistence.databinding.ListFragmentBinding;
import com.example.android.persistence.db.entity.ProductEntity;
import com.example.android.persistence.model.Product;
import com.mswim.architecture.viper.Router;

import java.util.List;

/**
 * Created by marcogalicia on 09/12/17.
 */

public class DemoProtocol {
    public interface View{
        Fragment getViewModelFragment();
    }

    /* View -> Presenter */
    public interface Presenter {
        void getDatas(); //  Ask interactor to retrieve documents and tell view to show
    }

    public interface InteractorInput {
        /* Presenter -> Interactor -- ask */
        void executeTask();
        void removeSubscription();

        void bindData(LifecycleOwner lifecycleOwner, ListFragmentBinding listFragmentBinding, DemoProtocol.AacInteractorOutput aacInteractorOutput,
                      LiveData<List<ProductEntity>> liveProdList);
    }

//    public interface InteractorOutput {
//        /* Interactor -> Presenter -- receive */
//        void didRetrieveDatas(String datas);
//    }
    public interface AacInteractorOutput {
        /* Interactor -> Presenter -- receive */
        void setProductList(final List<? extends Product> productList);
    }

    public interface RouterInt extends Router {

        // Don't need to create the first activity like in iOS?
        // void createDocumentViewController() -> UIViewController

        /* Presenter -> Router */
        void goNextScreen();
    }

    interface DataManagerInput {

        /* Interator -> DataManager */
        void retrieveDocumets();
    }

    interface DataManagerOutput {
        /* DataManager -> Interactor */  /* Interactor must implement this protocol */
        void didRetrieveDocuments();

        void onError(String errorMsg);
    }
}
