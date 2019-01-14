package com.example.kaushiknsanji.xploremysuru.ui.parks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.kaushiknsanji.xploremysuru.R;
import com.example.kaushiknsanji.xploremysuru.data.AppRepository;
import com.example.kaushiknsanji.xploremysuru.data.IResourceRepository;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Park;
import com.example.kaushiknsanji.xploremysuru.ui.BaseView;
import com.example.kaushiknsanji.xploremysuru.ui.MainActivity;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The Presenter Class that implements the {@link ParkListContract.Presenter} on the lines of
 * Model-View-Presenter architecture. This Presenter interfaces with the App repository {@link AppRepository}
 * and provides a list of Parks configured to the View {@link ParkListFragment} to load
 * and display the same.
 *
 * @author Kaushik N Sanji
 */
public class ParkListPresenter implements ParkListContract.Presenter {

    //Constant used for Logs
    private static final String LOG_TAG = ParkListPresenter.class.getSimpleName();

    //Instance of the App Repository
    @NonNull
    private final AppRepository mAppRepository;

    //The View Interface of this Presenter
    @NonNull
    private final ParkListContract.View mParkListView;

    //Boolean to avoid unnecessary downloads of the Parks data
    private AtomicBoolean mParksLoadedBoolean = new AtomicBoolean(false);

    /**
     * Constructor of {@link ParkListPresenter}
     *
     * @param appRepository Instance of {@link AppRepository} for accessing the data
     * @param parkListView  The View Instance {@link ParkListContract.View} of this Presenter
     */
    public ParkListPresenter(@NonNull AppRepository appRepository, @NonNull ParkListContract.View parkListView) {
        mAppRepository = appRepository;
        mParkListView = parkListView;

        //Registering the View with the Presenter
        mParkListView.setPresenter(this);
    }

    /**
     * Method that initiates the work of a Presenter which is invoked by the View
     * that implements the {@link BaseView}
     */
    @Override
    public void start() {

        //Download the list of Parks to be shown when not downloaded previously
        if (mParksLoadedBoolean.compareAndSet(false, true)) {
            loadParks();
        }
    }

    /**
     * Method that downloads the list of Parks to be updated to the View
     */
    private void loadParks() {
        //Display progress indicator
        mParkListView.showProgressIndicator();

        //Retrieving the Parks from the Repository
        mAppRepository.getParkListEntries(R.array.park_list_entries, new IResourceRepository.GetResourceCallback<List<Park>>() {
            /**
             * Method invoked when the {@code results} are obtained for the data requested.
             *
             * @param results The {@code results} in the generic type passed
             *                which is a List of {@link Park} data downloaded
             */
            @Override
            public void onResults(List<Park> results) {
                //Update the Parks to be shown
                updateParks(results);

                //Hide progress indicator
                mParkListView.hideProgressIndicator();
            }

            /**
             * Method invoked when the results could not be obtained for the data requested
             * due to some error.
             *
             * @param messageId The String resource of the error message
             * @param args      Variable number of arguments to replace the format specifiers
             */
            @Override
            public void onFailure(int messageId, @Nullable Object... args) {
                //Hide progress indicator
                mParkListView.hideProgressIndicator();

                //Show the error message
                mParkListView.showError(messageId, args);
            }
        });
    }

    /**
     * Method that updates the list of {@link Park} data to be shown, to the View.
     *
     * @param parks The List of {@link Park} data to be shown by the View.
     */
    @Override
    public void updateParks(List<Park> parks) {
        if (parks != null && parks.size() > 0) {
            //When we have the list of Parks

            //Submitting the list of Parks to the View
            mParkListView.loadParks(parks);

            //Updating the boolean to TRUE, to prevent further downloads of the same data
            mParksLoadedBoolean.set(true);
        }
    }

    /**
     * Method invoked when the User clicks on the Refresh Menu button
     * shown by the {@link MainActivity}
     */
    @Override
    public void onRefreshMenuClicked() {
        loadParks();
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
            mParkListView.showNoLinkError();
        } else {
            //When we have the URL, delegate to the View to launch the Web page
            mParkListView.launchWebLink(webUrl);
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
        mParkListView.launchMapLocation(location);
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
        mParkListView.launchShareLocation(location);
    }

}
