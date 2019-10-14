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

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Place;
import com.example.kaushiknsanji.xploremysuru.ui.BaseView;
import com.example.kaushiknsanji.xploremysuru.ui.NavigationPresenter;

import java.util.List;

/**
 * Contract Interface for the View {@link PlaceListFragment} and its Presenter {@link PlaceListPresenter}
 *
 * @author Kaushik N Sanji
 */
public interface PlaceListContract {

    /**
     * View Interface implemented by {@link PlaceListFragment}
     */
    interface View extends BaseView<Presenter> {
        /**
         * Method that displays the Progress indicator
         */
        void showProgressIndicator();

        /**
         * Method that hides the Progress indicator
         */
        void hideProgressIndicator();

        /**
         * Method that updates the RecyclerView's Adapter with new {@code placeList} data.
         *
         * @param placeList List of {@link Place}s loaded from the Resources.
         */
        void loadPlaces(List<Place> placeList);

        /**
         * Method invoked when an error is encountered during information
         * retrieval process.
         *
         * @param messageId String Resource of the error Message to be displayed
         * @param args      Variable number of arguments to replace the format specifiers
         *                  in the String resource if any
         */
        void showError(@StringRes int messageId, @Nullable Object... args);

        /**
         * Method invoked when there is no Web Page URL of the {@link Place} item being clicked.
         */
        void showNoLinkError();

        /**
         * Method invoked when the user clicks on the Item View itself. This should launch
         * a browser application for the URL {@code webUrl} of the Web Page passed.
         *
         * @param webUrl String containing the URL of the Web Page
         */
        void launchWebLink(String webUrl);

        /**
         * Method invoked when the user clicks on the Map ImageButton or the Location Info
         * TextView of the Item View. This should launch any Map application
         * passing in the {@code location} information.
         *
         * @param location String containing the Location information to be sent to a Map application.
         */
        void launchMapLocation(String location);

        /**
         * Method invoked when the user clicks and holds on to the Location Info TextView of the Item View.
         * This should launch a Share Intent passing in the location information.
         *
         * @param location String containing the Location information to be shared via an Intent.
         */
        void launchShareLocation(String location);
    }

    /**
     * Presenter Interface implemented by {@link PlaceListPresenter}
     */
    interface Presenter extends NavigationPresenter {

        /**
         * Method that updates the list of {@link Place} data to be shown, to the View.
         *
         * @param places The List of {@link Place} data to be shown by the View.
         */
        void updatePlaces(@Nullable List<Place> places);

        /**
         * Method invoked when the user clicks on the Item View itself. This should launch
         * the {@code webUrl} link if any.
         *
         * @param webUrl String containing the URL of the Web Page to be
         *               launched in a browser application
         */
        void openLink(String webUrl);

        /**
         * Method invoked when the user clicks on the Map ImageButton or the Location Info
         * TextView of the Item View. This should launch any Map application
         * passing in the {@code location} information.
         *
         * @param location String containing the Location information to be sent to a Map application.
         */
        void openLocation(String location);

        /**
         * Method invoked when the user clicks and holds on to the Location Info TextView of the Item View.
         * This should launch a Share Intent passing in the location information.
         *
         * @param location String containing the Location information to be shared via an Intent.
         */
        void shareLocation(String location);
    }
}
