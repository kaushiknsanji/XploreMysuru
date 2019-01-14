package com.example.kaushiknsanji.xploremysuru.ui.shops;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.kaushiknsanji.xploremysuru.R;
import com.example.kaushiknsanji.xploremysuru.data.AppRepository;
import com.example.kaushiknsanji.xploremysuru.data.IResourceRepository;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Shop;
import com.example.kaushiknsanji.xploremysuru.ui.BaseView;
import com.example.kaushiknsanji.xploremysuru.ui.MainActivity;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The Presenter Class that implements the {@link ShopListContract.Presenter} on the lines of
 * Model-View-Presenter architecture. This Presenter interfaces with the App repository {@link AppRepository}
 * and provides a list of Shops configured to the View {@link ShopListFragment} to load
 * and display the same.
 *
 * @author Kaushik N Sanji
 */
public class ShopListPresenter implements ShopListContract.Presenter {

    //Constant used for Logs
    private static final String LOG_TAG = ShopListPresenter.class.getSimpleName();

    //Instance of the App Repository
    @NonNull
    private final AppRepository mAppRepository;

    //The View Interface of this Presenter
    @NonNull
    private final ShopListContract.View mShopListView;

    //Boolean to avoid unnecessary downloads of the Shops data
    private AtomicBoolean mShopsLoadedBoolean = new AtomicBoolean(false);

    /**
     * Constructor of {@link ShopListPresenter}
     *
     * @param appRepository Instance of {@link AppRepository} for accessing the data
     * @param shopListView  The View Instance {@link ShopListContract.View} of this Presenter
     */
    public ShopListPresenter(@NonNull AppRepository appRepository, @NonNull ShopListContract.View shopListView) {
        mAppRepository = appRepository;
        mShopListView = shopListView;

        //Registering the View with the Presenter
        mShopListView.setPresenter(this);
    }

    /**
     * Method that initiates the work of a Presenter which is invoked by the View
     * that implements the {@link BaseView}
     */
    @Override
    public void start() {

        //Download the list of Shops to be shown when not downloaded previously
        if (mShopsLoadedBoolean.compareAndSet(false, true)) {
            loadShops();
        }
    }

    /**
     * Method that downloads the list of Shops to be updated to the View
     */
    private void loadShops() {
        //Display progress indicator
        mShopListView.showProgressIndicator();

        //Retrieving the Shops from the Repository
        mAppRepository.getShopListEntries(R.array.shop_list_entries, new IResourceRepository.GetResourceCallback<List<Shop>>() {
            /**
             * Method invoked when the {@code results} are obtained for the data requested.
             *
             * @param results The {@code results} in the generic type passed which
             *                is a List of {@link Shop} data downloaded
             */
            @Override
            public void onResults(List<Shop> results) {
                //Update the Shops to be shown
                updateShops(results);

                //Hide progress indicator
                mShopListView.hideProgressIndicator();
            }

            /**
             * Method invoked when the results could not be obtained for the data requested
             * due to some error.
             *
             * @param messageId The String resource of the error message
             * @param args Variable number of arguments to replace the format specifiers
             *             in the String resource if any
             */
            @Override
            public void onFailure(int messageId, @Nullable Object... args) {
                //Hide progress indicator
                mShopListView.hideProgressIndicator();

                //Show the error message
                mShopListView.showError(messageId, args);
            }
        });
    }

    /**
     * Method that updates the list of {@link Shop} data to be shown, to the View.
     *
     * @param shops The List of {@link Shop} data to be shown by the View.
     */
    @Override
    public void updateShops(List<Shop> shops) {
        if (shops != null && shops.size() > 0) {
            //When we have the list of Shops

            //Submitting the list of Shops to the View
            mShopListView.loadShops(shops);

            //Updating the boolean to TRUE, to prevent further downloads of the same data
            mShopsLoadedBoolean.set(true);
        }
    }

    /**
     * Method invoked when the User clicks on the Refresh Menu button
     * shown by the {@link MainActivity}
     */
    @Override
    public void onRefreshMenuClicked() {
        loadShops();
    }

    /**
     * Method invoked when the user clicks on the Item View itself. This should launch
     * the {@code webUrl} link if any.
     *
     * @param webUrl String containing the URL of the Web Page to be
     *               launched in a browser application
     */
    @Override
    public void openLink(String webUrl) {
        if (TextUtils.isEmpty(webUrl)) {
            //When we do not have the URL, show an error message
            mShopListView.showNoLinkError();
        } else {
            //When we have the URL, delegate to the View to launch the Web page
            mShopListView.launchWebLink(webUrl);
        }
    }

    /**
     * Method invoked when the user clicks on the Map ImageButton or the Location Info
     * TextView of the Item View. This should launch any Map application
     * passing in the {@code location} information.
     *
     * @param location String containing the Location information to be sent to a Map application.
     */
    @Override
    public void openLocation(String location) {
        //Delegating to the View to launch the Map application for the location address
        mShopListView.launchMapLocation(location);
    }

    /**
     * Method invoked when the user clicks and holds on to the Location Info TextView of the Item View.
     * This should launch a Share Intent passing in the location information.
     *
     * @param location String containing the Location information to be shared via an Intent.
     */
    @Override
    public void shareLocation(String location) {
        //Delegating to the View to share the location information
        mShopListView.launchShareLocation(location);
    }


}
