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

package com.example.kaushiknsanji.xploremysuru.data;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;

import com.example.kaushiknsanji.xploremysuru.data.local.models.Hotel;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Park;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Place;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Restaurant;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Shop;

import java.util.List;

/**
 * The App Repository class that interfaces with {@link IResourceRepository}
 * to communicate with the Resources.
 *
 * @author Kaushik N Sanji
 */
public class AppRepository implements IResourceRepository {

    //Constant used for logs
    private static final String LOG_TAG = AppRepository.class.getSimpleName();

    //Singleton instance of AppRepository
    private static volatile AppRepository INSTANCE;

    //Instance of IResourceRepository to communicate with Resources
    private final IResourceRepository mResourceRepository;

    /**
     * Private Constructor of {@link AppRepository}
     *
     * @param resourceRepository Instance of {@link IResourceRepository} to communicate with {@link android.content.res.Resources}
     */
    private AppRepository(@NonNull IResourceRepository resourceRepository) {
        mResourceRepository = resourceRepository;
    }

    /**
     * Singleton Constructor that creates a single instance of {@link AppRepository}
     *
     * @param resourceRepository Instance of {@link IResourceRepository} to communicate with {@link android.content.res.Resources}
     * @return New or existing instance of {@link AppRepository}
     */
    public static AppRepository getInstance(@NonNull IResourceRepository resourceRepository) {
        if (INSTANCE == null) {
            //When instance is not available
            synchronized (AppRepository.class) {
                //Apply lock and check for the instance again
                if (INSTANCE == null) {
                    //When there is no instance, create a new one
                    INSTANCE = new AppRepository(resourceRepository);
                }
            }
        }
        //Returning the instance of AppRepository
        return INSTANCE;
    }

    /**
     * Method that retrieves the List of {@link Place} data for the Place list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Place list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    @Override
    public void getPlaceListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Place>> resourceCallback) {
        mResourceRepository.getPlaceListEntries(arrayResId, resourceCallback);
    }

    /**
     * Method that retrieves the List of {@link Park} data for the Park list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Park list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    @Override
    public void getParkListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Park>> resourceCallback) {
        mResourceRepository.getParkListEntries(arrayResId, resourceCallback);
    }

    /**
     * Method that retrieves the List of {@link Hotel} data for the Hotel list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Hotel list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    @Override
    public void getHotelListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Hotel>> resourceCallback) {
        mResourceRepository.getHotelListEntries(arrayResId, resourceCallback);
    }

    /**
     * Method that retrieves the List of {@link Restaurant} data for the Restaurant list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Restaurant list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    @Override
    public void getRestaurantListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Restaurant>> resourceCallback) {
        mResourceRepository.getRestaurantListEntries(arrayResId, resourceCallback);
    }

    /**
     * Method that retrieves the List of {@link Shop} data for the Shop list entries
     * tracked by the {@code arrayResId}.
     *
     * @param arrayResId       The Integer Id of the Array resource that tracks the
     *                         Shop list entries to be shown.
     * @param resourceCallback The Callback to be implemented by the caller to receive the result.
     */
    @Override
    public void getShopListEntries(@ArrayRes int arrayResId, @NonNull GetResourceCallback<List<Shop>> resourceCallback) {
        mResourceRepository.getShopListEntries(arrayResId, resourceCallback);
    }
}
