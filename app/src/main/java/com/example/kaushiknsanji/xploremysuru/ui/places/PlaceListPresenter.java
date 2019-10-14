/*
 * Copyright 2019 Kaushik N. Sanji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kaushiknsanji.xploremysuru.ui.places;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.kaushiknsanji.xploremysuru.R;
import com.example.kaushiknsanji.xploremysuru.data.AppRepository;
import com.example.kaushiknsanji.xploremysuru.data.IResourceRepository;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Place;
import com.example.kaushiknsanji.xploremysuru.ui.BaseView;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The Presenter Class that implements the {@link PlaceListContract.Presenter} on the lines of
 * Model-View-Presenter architecture. This Presenter interfaces with the App repository {@link AppRepository}
 * and provides a list of Places configured to the View {@link PlaceListFragment} to load
 * and display the same.
 *
 * @author Kaushik N Sanji
 */
public class PlaceListPresenter implements PlaceListContract.Presenter {

    //Constant used for logs
    private static final String LOG_TAG = PlaceListPresenter.class.getSimpleName();

    //Instance of the App Repository
    @NonNull
    private final AppRepository mAppRepository;

    //The View Interface of this Presenter
    @NonNull
    private final PlaceListContract.View mPlaceListView;

    //Boolean to avoid unnecessary downloads of the Places data
    private AtomicBoolean mPlacesLoadedBoolean = new AtomicBoolean(false);

    /**
     * Constructor of {@link PlaceListPresenter}
     *
     * @param appRepository Instance of {@link AppRepository} for accessing the data
     * @param placeListView The View Instance {@link PlaceListContract.View} of this Presenter
     */
    public PlaceListPresenter(@NonNull AppRepository appRepository, @NonNull PlaceListContract.View placeListView) {
        mAppRepository = appRepository;
        mPlaceListView = placeListView;

        //Registering the View with the Presenter
        mPlaceListView.setPresenter(this);
    }

    /**
     * Method that initiates the work of a Presenter which is invoked by the View
     * that implements the {@link BaseView}
     */
    @Override
    public void start() {

        //Download the list of Places to be shown when not downloaded previously
        if (mPlacesLoadedBoolean.compareAndSet(false, true)) {
            loadPlaces();
        }
    }

    /**
     * Method that downloads the list of Places to be updated to the View
     */
    private void loadPlaces() {
        //Display progress indicator
        mPlaceListView.showProgressIndicator();

        //Retrieving the Places from the Repository
        mAppRepository.getPlaceListEntries(R.array.place_list_entries, new IResourceRepository.GetResourceCallback<List<Place>>() {
            /**
             * Method invoked when the {@code results} are obtained for the data requested.
             *
             * @param results The {@code results} in the generic type passed which
             *                is a List of {@link Place} data downloaded
             */
            @Override
            public void onResults(List<Place> results) {
                //Update the Places to be shown
                updatePlaces(results);

                //Hide progress indicator
                mPlaceListView.hideProgressIndicator();
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
                mPlaceListView.hideProgressIndicator();

                //Show the error message
                mPlaceListView.showError(messageId, args);
            }
        });
    }

    /**
     * Method that updates the list of {@link Place} data to be shown, to the View.
     *
     * @param places The List of {@link Place} data to be shown by the View.
     */
    @Override
    public void updatePlaces(@Nullable List<Place> places) {
        if (places != null && places.size() > 0) {
            //When we have the list of Places

            //Submitting the list of Places to the View
            mPlaceListView.loadPlaces(places);

            //Updating the boolean to TRUE, to prevent further downloads of the same data
            mPlacesLoadedBoolean.set(true);
        }
    }

    /**
     * Method invoked when the User clicks on the Refresh Menu button
     * shown by the {@link com.example.kaushiknsanji.xploremysuru.ui.MainActivity}
     */
    @Override
    public void onRefreshMenuClicked() {
        loadPlaces();
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
            mPlaceListView.showNoLinkError();
        } else {
            //When we have the URL, delegate to the View to launch the Web page
            mPlaceListView.launchWebLink(webUrl);
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
        mPlaceListView.launchMapLocation(location);
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
        mPlaceListView.launchShareLocation(location);
    }

}
