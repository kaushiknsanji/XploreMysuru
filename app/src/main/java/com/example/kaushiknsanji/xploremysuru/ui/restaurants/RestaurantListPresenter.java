package com.example.kaushiknsanji.xploremysuru.ui.restaurants;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.kaushiknsanji.xploremysuru.R;
import com.example.kaushiknsanji.xploremysuru.data.AppRepository;
import com.example.kaushiknsanji.xploremysuru.data.IResourceRepository;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Restaurant;
import com.example.kaushiknsanji.xploremysuru.ui.BaseView;
import com.example.kaushiknsanji.xploremysuru.ui.MainActivity;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The Presenter Class that implements the {@link RestaurantListContract.Presenter} on the lines of
 * Model-View-Presenter architecture. This Presenter interfaces with the App repository {@link AppRepository}
 * and provides a list of Restaurants configured to the View {@link RestaurantListFragment} to load
 * and display the same.
 *
 * @author Kaushik N Sanji
 */
public class RestaurantListPresenter implements RestaurantListContract.Presenter {

    //Constant used for logs
    private static final String LOG_TAG = RestaurantListPresenter.class.getSimpleName();

    //Instance of the App Repository
    @NonNull
    private final AppRepository mAppRepository;

    //The View Interface of this Presenter
    @NonNull
    private final RestaurantListContract.View mRestaurantListView;

    //Boolean to avoid unnecessary downloads of the Restaurants data
    private AtomicBoolean mRestaurantsLoadedBoolean = new AtomicBoolean(false);

    /**
     * Constructor of {@link RestaurantListPresenter}
     *
     * @param appRepository      Instance of {@link AppRepository} for accessing the data
     * @param restaurantListView The View Instance {@link RestaurantListContract.View} of this Presenter
     */
    public RestaurantListPresenter(@NonNull AppRepository appRepository, @NonNull RestaurantListContract.View restaurantListView) {
        mAppRepository = appRepository;
        mRestaurantListView = restaurantListView;

        //Registering the View with the Presenter
        mRestaurantListView.setPresenter(this);
    }

    /**
     * Method that initiates the work of a Presenter which is invoked by the View
     * that implements the {@link BaseView}
     */
    @Override
    public void start() {

        //Download the list of Restaurants to be shown when not downloaded previously
        if (mRestaurantsLoadedBoolean.compareAndSet(false, true)) {
            loadRestaurants();
        }
    }

    /**
     * Method that downloads the list of Restaurants to be updated to the View
     */
    private void loadRestaurants() {
        //Display progress indicator
        mRestaurantListView.showProgressIndicator();

        //Retrieving the Restaurants from the Repository
        mAppRepository.getRestaurantListEntries(R.array.restaurant_list_entries, new IResourceRepository.GetResourceCallback<List<Restaurant>>() {
            /**
             * Method invoked when the {@code results} are obtained for the data requested.
             *
             * @param results The {@code results} in the generic type passed which
             *                is a List of {@link Restaurant} data downloaded
             */
            @Override
            public void onResults(List<Restaurant> results) {
                //Update the Restaurants to be shown
                updateRestaurants(results);

                //Hide progress indicator
                mRestaurantListView.hideProgressIndicator();
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
                mRestaurantListView.hideProgressIndicator();

                //Show the error message
                mRestaurantListView.showError(messageId, args);
            }
        });
    }

    /**
     * Method that updates the list of {@link Restaurant} data to be shown, to the View.
     *
     * @param restaurants The List of {@link Restaurant} data to be shown by the View.
     */
    @Override
    public void updateRestaurants(List<Restaurant> restaurants) {
        if (restaurants != null && restaurants.size() > 0) {
            //When we have the list of Restaurants

            //Submitting the list of Restaurants to the View
            mRestaurantListView.loadRestaurants(restaurants);

            //Updating the boolean to TRUE, to prevent further downloads of the same data
            mRestaurantsLoadedBoolean.set(true);
        }
    }

    /**
     * Method invoked when the User clicks on the Refresh Menu button
     * shown by the {@link MainActivity}
     */
    @Override
    public void onRefreshMenuClicked() {
        loadRestaurants();
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
            mRestaurantListView.showNoLinkError();
        } else {
            //When we have the URL, delegate to the View to launch the Web page
            mRestaurantListView.launchWebLink(webUrl);
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
        mRestaurantListView.launchMapLocation(location);
    }

    /**
     * Method invoked when the user clicks on the Call ImageButton or the Contact Info
     * TextView of the Item View. This should launch any Dialer application
     * passing in the Contact Number {@code contactNumber}.
     *
     * @param contactNumber String containing the Contact Number information to be sent
     *                      to a Dialer application.
     */
    @Override
    public void openContact(String contactNumber) {
        //Delegating to the View to launch the Phone Dialer
        mRestaurantListView.dialNumber(contactNumber);
    }

    /**
     * Method invoked when the user clicks and holds on to the Contact Info TextView
     * of the Item View. This should launch a Share Intent passing
     * in the Contact Number.
     *
     * @param contactNumber String containing the Contact Number information to be
     *                      shared via an Intent.
     */
    @Override
    public void shareContact(String contactNumber) {
        if (!TextUtils.isEmpty(contactNumber)) {
            //Delegating to the View to share the Contact information when available
            mRestaurantListView.launchShareContact(contactNumber);
        }
    }

    /**
     * Method invoked when the user clicks and holds on to the Location Info
     * TextView of the Item View. This should launch a Share Intent passing
     * in the location information.
     *
     * @param location String containing the Location information to be shared via an Intent.
     */
    @Override
    public void shareLocation(String location) {
        //Delegating to the View to share the location information
        mRestaurantListView.launchShareLocation(location);
    }

}
