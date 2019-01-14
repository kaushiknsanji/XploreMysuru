package com.example.kaushiknsanji.xploremysuru.data;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Hotel;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Park;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Place;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Restaurant;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Shop;

import java.util.List;

/**
 * Contract Interface for the App's Resource management. Resource communication is
 * implemented by {@link AppRepository}
 * and {@link com.example.kaushiknsanji.xploremysuru.data.local.ResourceRepository}
 *
 * @author Kaushik N Sanji
 */
public interface IResourceRepository {

    /**
     * Method that retrieves the List of {@link Place} data for the Place list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Place list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    void getPlaceListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Place>> resourceCallback);

    /**
     * Method that retrieves the List of {@link Park} data for the Park list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Park list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    void getParkListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Park>> resourceCallback);

    /**
     * Method that retrieves the List of {@link Hotel} data for the Hotel list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Hotel list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    void getHotelListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Hotel>> resourceCallback);

    /**
     * Method that retrieves the List of {@link Restaurant} data for the Restaurant list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Restaurant list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    void getRestaurantListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Restaurant>> resourceCallback);

    /**
     * Method that retrieves the List of {@link Shop} data for the Shop list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Shop list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    void getShopListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Shop>> resourceCallback);

    /**
     * Callback interface for Resource data requests.
     *
     * @param <T> The type of the results expected for the data requested.
     */
    interface GetResourceCallback<T> {
        /**
         * Method invoked when the {@code results} are obtained for the data requested.
         *
         * @param results The {@code results} in the generic type passed.
         */
        void onResults(T results);

        /**
         * Method invoked when the results could not be obtained for the data requested
         * due to some error.
         *
         * @param messageId The String resource of the error message
         * @param args      Variable number of arguments to replace the format specifiers
         *                  in the String resource if any
         */
        void onFailure(@StringRes int messageId, @Nullable Object... args);
    }
}
