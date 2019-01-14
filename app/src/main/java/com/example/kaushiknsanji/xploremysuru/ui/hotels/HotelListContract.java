package com.example.kaushiknsanji.xploremysuru.ui.hotels;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Hotel;
import com.example.kaushiknsanji.xploremysuru.ui.BaseView;
import com.example.kaushiknsanji.xploremysuru.ui.NavigationPresenter;

import java.util.List;

/**
 * Contract Interface for the View {@link HotelListFragment} and its Presenter {@link HotelListPresenter}
 *
 * @author Kaushik N Sanji
 */
public interface HotelListContract {

    /**
     * View Interface implemented by {@link HotelListFragment}
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
         * Method that updates the RecyclerView's Adapter with new {@code hotelList} data.
         *
         * @param hotelList List of {@link Hotel}s loaded from the Resources.
         */
        void loadHotels(List<Hotel> hotelList);

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
         * Method invoked when there is no Web Page URL of the {@link Hotel} item being clicked.
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
         * Method invoked when the user clicks on the Call ImageButton or the Contact Info
         * TextView of the Item View. This should launch any Dialer application
         * passing in the Contact Number {@code contactNumber}.
         *
         * @param contactNumber String containing the Contact Number information to be sent
         *                      to a Dialer application.
         */
        void dialNumber(String contactNumber);

        /**
         * Method invoked when the user clicks and holds on to the Contact Info TextView
         * of the Item View. This should launch a Share Intent passing
         * in the Contact Number.
         *
         * @param contactNumber String containing the Contact Number information to be
         *                      shared via an Intent.
         */
        void launchShareContact(String contactNumber);

        /**
         * Method invoked when the user clicks and holds on to the Location Info
         * TextView of the Item View. This should launch a Share Intent passing in
         * the location information.
         *
         * @param location String containing the Location information to be shared via an Intent.
         */
        void launchShareLocation(String location);
    }

    /**
     * Presenter Interface implemented by {@link HotelListPresenter}
     */
    interface Presenter extends NavigationPresenter {

        /**
         * Method that updates the list of {@link Hotel} data to be shown, to the View.
         *
         * @param hotels The List of {@link Hotel} data to be shown by the View.
         */
        void updateHotels(List<Hotel> hotels);

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
         * Method invoked when the user clicks on the Call ImageButton or the Contact Info
         * TextView of the Item View. This should launch any Dialer application
         * passing in the Contact Number {@code contactNumber}.
         *
         * @param contactNumber String containing the Contact Number information to be sent
         *                      to a Dialer application.
         */
        void openContact(String contactNumber);

        /**
         * Method invoked when the user clicks and holds on to the Contact Info TextView
         * of the Item View. This should launch a Share Intent passing
         * in the Contact Number.
         *
         * @param contactNumber String containing the Contact Number information to be
         *                      shared via an Intent.
         */
        void shareContact(String contactNumber);

        /**
         * Method invoked when the user clicks and holds on to the Location Info
         * TextView of the Item View. This should launch a Share Intent passing
         * in the location information.
         *
         * @param location String containing the Location information to be shared via an Intent.
         */
        void shareLocation(String location);
    }
}
