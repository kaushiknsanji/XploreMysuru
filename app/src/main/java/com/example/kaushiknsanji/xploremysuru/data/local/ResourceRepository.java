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

package com.example.kaushiknsanji.xploremysuru.data.local;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

import com.example.kaushiknsanji.xploremysuru.R;
import com.example.kaushiknsanji.xploremysuru.data.IResourceRepository;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Hotel;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Park;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Place;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Restaurant;
import com.example.kaushiknsanji.xploremysuru.data.local.models.Shop;
import com.example.kaushiknsanji.xploremysuru.utils.AppExecutors;
import com.example.kaushiknsanji.xploremysuru.utils.BitmapUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * The Resource Repository class that implements {@link IResourceRepository} interface
 * to manage communication with the Resources of the App.
 *
 * @author Kaushik N Sanji
 */
public class ResourceRepository implements IResourceRepository {

    //Constant used for logs
    private static final String LOG_TAG = ResourceRepository.class.getSimpleName();

    //Singleton instance of ResourceRepository
    private static volatile ResourceRepository INSTANCE;

    //The name of this application's package
    private final String mAppPackageName;

    //Resources Instance
    private final Resources mResources;

    //AppExecutors instance for threading requests
    private final AppExecutors mAppExecutors;

    /**
     * Private Constructor of {@link ResourceRepository}
     *
     * @param packageName  The name of this application's package.
     * @param resources    Instance of {@link Resources}
     * @param appExecutors Instance of {@link AppExecutors} for threading requests
     */
    private ResourceRepository(@NonNull String packageName, @NonNull Resources resources, @NonNull AppExecutors appExecutors) {
        mAppPackageName = packageName;
        mResources = resources;
        mAppExecutors = appExecutors;
    }

    /**
     * Singleton Constructor that creates a single instance of {@link ResourceRepository}
     *
     * @param packageName  The name of this application's package.
     * @param resources    Instance of {@link Resources}
     * @param appExecutors Instance of {@link AppExecutors} for threading requests
     * @return New or existing instance of {@link ResourceRepository}
     */
    public static ResourceRepository getInstance(@NonNull String packageName, @NonNull Resources resources, @NonNull AppExecutors appExecutors) {
        if (INSTANCE == null) {
            //When instance is not available
            synchronized (ResourceRepository.class) {
                //Apply lock and check for the instance again
                if (INSTANCE == null) {
                    //When there is no instance, create a new one
                    INSTANCE = new ResourceRepository(packageName, resources, appExecutors);
                }
            }
        }
        //Returning the instance of ResourceRepository
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
        //Executing on the Disk Thread
        mAppExecutors.getDiskIO().execute(() -> {
            //Stores an Array of Places to be read
            TypedArray typedArrayPlaces = null;
            try {
                //Obtaining the Typed Array of Places
                typedArrayPlaces = mResources.obtainTypedArray(arrayResId);
                //The Number of Places to load
                int noOfPlaces = typedArrayPlaces.length();
                if (noOfPlaces > 0) {
                    //When we have some Places to be loaded

                    //Initializing an ArrayList of Place data to store the Place information
                    //for all the Places read from the Typed Array
                    ArrayList<Place> placeList = new ArrayList<>();
                    //Iterating over the Typed Array to build the list
                    for (int index = 0; index < noOfPlaces; index++) {
                        //Getting the Resource ID of the Place Array resource
                        int placeResourceId = typedArrayPlaces.getResourceId(index, 0);
                        if (placeResourceId > 0) {
                            //When the resource read is valid

                            //Obtain the String Array for the resource which contains the data for the Place
                            String[] placeStringArray = mResources.getStringArray(placeResourceId);
                            //Read the Name
                            String name = placeStringArray[Place.Contract.NAME_INDEX].trim();
                            //Read the Rating
                            float rating = Float.parseFloat(placeStringArray[Place.Contract.RATING_INDEX]);
                            //Read the Place Types
                            String types = placeStringArray[Place.Contract.TYPES_INDEX].trim();
                            //Read the drawable resource Id for the Place Type
                            int typeImageResId = BitmapUtility.findDrawableResourceId(
                                    placeStringArray[Place.Contract.TYPE_ICON_INDEX],
                                    mAppPackageName,
                                    mResources
                            );
                            //Read the drawable resource Id for the Place Photo
                            int photoResId = BitmapUtility.findDrawableResourceId(
                                    placeStringArray[Place.Contract.PHOTO_INDEX],
                                    mAppPackageName,
                                    mResources
                            );
                            //Construct a Bitmap for the Place Photo and extract the Palette
                            Bitmap photoBitmap = BitmapFactory.decodeResource(mResources, photoResId);
                            Palette.Swatch photoSwatch = BitmapUtility.extractVibrantSwatch(photoBitmap);
                            //Reading the Access Timings
                            String accessTimeInfo = placeStringArray[Place.Contract.ACCESS_TIME_INDEX].trim();
                            //Read the Entry Fee
                            String entryFeeInfo = placeStringArray[Place.Contract.ENTRY_FEE_INDEX].trim();
                            //Read the Location
                            String location = placeStringArray[Place.Contract.LOCATION_INDEX].trim();
                            //Read the Description
                            String description = placeStringArray[Place.Contract.DESCRIPTION_INDEX];
                            //Read the Website
                            String website = placeStringArray[Place.Contract.WEBSITE_INDEX].trim();

                            //Construct the Place data with the data read
                            Place place = new Place.Builder()
                                    .setId(placeResourceId)
                                    .setName(name)
                                    .setRating(rating)
                                    .setTypes(types)
                                    .setTypeImageResId(typeImageResId)
                                    .setPhotoResId(photoResId)
                                    .setAccessTimeInfo(accessTimeInfo)
                                    .setEntryFeeInfo(entryFeeInfo)
                                    .setLocation(location)
                                    .setDescription(description)
                                    .setWebsite(website)
                                    .setSwatchGenerated(photoSwatch != null)
                                    .setSwatchBodyTextColor(photoSwatch != null ? photoSwatch.getBodyTextColor() : 0)
                                    .setSwatchTitleTextColor(photoSwatch != null ? photoSwatch.getTitleTextColor() : 0)
                                    .setSwatchRgbColor(photoSwatch != null ? photoSwatch.getRgb() : 0)
                                    .createPlace();

                            //Add to the list of Place data constructed
                            placeList.add(place);
                        }
                    }

                    if (placeList.size() > 0) {
                        //When we have places data to be loaded, pass the result to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the list of Places to the callback
                            resourceCallback.onResults(placeList);
                        });
                    } else {
                        //When we have no places to be loaded, pass an error message to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the error message to the callback
                            resourceCallback.onFailure(R.string.place_list_load_error);
                        });
                    }
                } else {
                    //When we have no places to be loaded, pass an error message to the callback
                    //Executing on the Main Thread
                    mAppExecutors.getMainThread().execute(() -> {
                        //Pass the error message to the callback
                        resourceCallback.onFailure(R.string.place_list_load_error);
                    });
                }
            } finally {
                //Finally, release the TypedArray resource if held
                if (typedArrayPlaces != null) {
                    typedArrayPlaces.recycle();
                }
            }
        });
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
        //Executing on the Disk Thread
        mAppExecutors.getDiskIO().execute(() -> {
            //Stores an Array of Parks to be read
            TypedArray typedArrayParks = null;
            try {
                //Obtaining the Typed Array of Parks
                typedArrayParks = mResources.obtainTypedArray(arrayResId);
                //The Number of Parks to load
                int noOfParks = typedArrayParks.length();
                if (noOfParks > 0) {
                    //When we have some Parks to be loaded

                    //Initializing an ArrayList of Park data to store the Park information
                    //for all the Parks read from the Typed Array
                    ArrayList<Park> parkList = new ArrayList<>();
                    //Iterating over the Typed Array to build the list
                    for (int index = 0; index < noOfParks; index++) {
                        //Getting the Resource ID of the Park Array resource
                        int parkResourceId = typedArrayParks.getResourceId(index, 0);
                        if (parkResourceId > 0) {
                            //When the resource read is valid

                            //Obtain the String Array for the resource which contains the data for the Park
                            String[] parkStringArray = mResources.getStringArray(parkResourceId);
                            //Read the Name
                            String name = parkStringArray[Park.Contract.NAME_INDEX].trim();
                            //Read the Rating
                            float rating = Float.parseFloat(parkStringArray[Park.Contract.RATING_INDEX]);
                            //Read the drawable resource Id for the Park Photo
                            int photoResId = BitmapUtility.findDrawableResourceId(
                                    parkStringArray[Park.Contract.PHOTO_INDEX],
                                    mAppPackageName,
                                    mResources
                            );
                            //Construct a Bitmap for the Park Photo and extract the Palette
                            Bitmap photoBitmap = BitmapFactory.decodeResource(mResources, photoResId);
                            Palette.Swatch photoSwatch = BitmapUtility.extractVibrantSwatch(photoBitmap);
                            //Read the Access Timings
                            String accessTimeInfo = parkStringArray[Park.Contract.ACCESS_TIME_INDEX].trim();
                            //Read the Entry Fee
                            String entryFeeInfo = parkStringArray[Park.Contract.ENTRY_FEE_INDEX].trim();
                            //Read the Location
                            String location = parkStringArray[Park.Contract.LOCATION_INDEX].trim();
                            //Read the Description
                            String description = parkStringArray[Park.Contract.DESCRIPTION_INDEX];
                            //Read the Website
                            String website = parkStringArray[Park.Contract.WEBSITE_INDEX].trim();

                            //Construct the Park data with the data read
                            Park park = new Park.Builder()
                                    .setId(parkResourceId)
                                    .setName(name)
                                    .setRating(rating)
                                    .setPhotoResId(photoResId)
                                    .setAccessTimeInfo(accessTimeInfo)
                                    .setEntryFeeInfo(entryFeeInfo)
                                    .setLocation(location)
                                    .setDescription(description)
                                    .setWebsite(website)
                                    .setSwatchGenerated(photoSwatch != null)
                                    .setSwatchBodyTextColor(photoSwatch != null ? photoSwatch.getBodyTextColor() : 0)
                                    .setSwatchTitleTextColor(photoSwatch != null ? photoSwatch.getTitleTextColor() : 0)
                                    .setSwatchRgbColor(photoSwatch != null ? photoSwatch.getRgb() : 0)
                                    .createPark();

                            //Add to the list of Park data constructed
                            parkList.add(park);
                        }
                    }

                    if (parkList.size() > 0) {
                        //When we have parks data to be loaded, pass the result to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the list of Parks to the callback
                            resourceCallback.onResults(parkList);
                        });
                    } else {
                        //When we have no parks to be loaded, pass an error message to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the error message to the callback
                            resourceCallback.onFailure(R.string.park_list_load_error);
                        });
                    }
                } else {
                    //When we have no parks to be loaded, pass an error message to the callback

                    //Executing on the Main Thread
                    mAppExecutors.getMainThread().execute(() -> {
                        //Pass the error message to the callback
                        resourceCallback.onFailure(R.string.park_list_load_error);
                    });
                }
            } finally {
                //Finally, release the TypedArray resource if held
                if (typedArrayParks != null) {
                    typedArrayParks.recycle();
                }
            }
        });
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
        //Executing on the Disk Thread
        mAppExecutors.getDiskIO().execute(() -> {
            //Stores an Array of Hotels to be read
            TypedArray typedArrayHotels = null;
            try {
                //Obtaining the Typed Array of Hotels
                typedArrayHotels = mResources.obtainTypedArray(arrayResId);
                //The Number of Hotels to load
                int noOfHotels = typedArrayHotels.length();
                if (noOfHotels > 0) {
                    //When we have some Hotels to be loaded

                    //Initializing an ArrayList of Hotel data to store the Hotel information
                    //for all the Hotels read from the Typed Array
                    ArrayList<Hotel> hotelList = new ArrayList<>();
                    //Iterating over the Typed Array to build the list
                    for (int index = 0; index < noOfHotels; index++) {
                        //Getting the Resource ID of the Hotel Array resource
                        int hotelResourceId = typedArrayHotels.getResourceId(index, 0);
                        if (hotelResourceId > 0) {
                            //When the resource read is valid

                            //Obtain the String Array for the resource which contains the data for the Hotel
                            String[] hotelStringArray = mResources.getStringArray(hotelResourceId);
                            //Read the Name
                            String name = hotelStringArray[Hotel.Contract.NAME_INDEX].trim();
                            //Read the Drawable Resource Id for the Star Type Rating
                            int starTypeResId = BitmapUtility.findDrawableResourceId(
                                    hotelStringArray[Hotel.Contract.STAR_TYPE_ICON_INDEX],
                                    mAppPackageName,
                                    mResources
                            );
                            //Read the Rating
                            float rating = Float.parseFloat(hotelStringArray[Hotel.Contract.RATING_INDEX]);
                            //Read the Drawable Resource Id for the Hotel Photo
                            int photoResId = BitmapUtility.findDrawableResourceId(
                                    hotelStringArray[Hotel.Contract.PHOTO_INDEX],
                                    mAppPackageName,
                                    mResources
                            );
                            //Construct a Bitmap for the Hotel Photo and extract the Palette
                            Bitmap photoBitmap = BitmapFactory.decodeResource(mResources, photoResId);
                            Palette.Swatch photoSwatch = BitmapUtility.extractVibrantSwatch(photoBitmap);
                            //Read the Cost per Night
                            String costOfStay = hotelStringArray[Hotel.Contract.COST_INDEX].trim();
                            //Read the Location
                            String location = hotelStringArray[Hotel.Contract.LOCATION_INDEX].trim();
                            //Read the Contact Number
                            String contactNumber = hotelStringArray[Hotel.Contract.CONTACT_INDEX].trim();
                            //Read the Website
                            String website = hotelStringArray[Hotel.Contract.WEBSITE_INDEX].trim();

                            //Construct the Hotel data with the data read
                            Hotel hotel = new Hotel.Builder()
                                    .setId(hotelResourceId)
                                    .setName(name)
                                    .setStarTypeResId(starTypeResId)
                                    .setRating(rating)
                                    .setPhotoResId(photoResId)
                                    .setCostOfStay(costOfStay)
                                    .setLocation(location)
                                    .setContactNumber(contactNumber)
                                    .setWebsite(website)
                                    .setSwatchGenerated(photoSwatch != null)
                                    .setSwatchBodyTextColor(photoSwatch != null ? photoSwatch.getBodyTextColor() : 0)
                                    .setSwatchTitleTextColor(photoSwatch != null ? photoSwatch.getTitleTextColor() : 0)
                                    .setSwatchRgbColor(photoSwatch != null ? photoSwatch.getRgb() : 0)
                                    .createHotel();

                            //Add to the list of Hotel data constructed
                            hotelList.add(hotel);
                        }
                    }

                    if (hotelList.size() > 0) {
                        //When we have hotels data to be loaded, pass the result to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the list of Hotels to the callback
                            resourceCallback.onResults(hotelList);
                        });
                    } else {
                        //When we have no hotels to be loaded, pass an error message to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the error message to the callback
                            resourceCallback.onFailure(R.string.hotel_list_load_error);
                        });
                    }

                } else {
                    //When we have no hotels to be loaded, pass an error message to the callback

                    //Executing on the Main Thread
                    mAppExecutors.getMainThread().execute(() -> {
                        //Pass the error message to the callback
                        resourceCallback.onFailure(R.string.hotel_list_load_error);
                    });
                }
            } finally {
                //Finally, release the TypedArray resource if held
                if (typedArrayHotels != null) {
                    typedArrayHotels.recycle();
                }
            }
        });
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
        //Executing on the Disk Thread
        mAppExecutors.getDiskIO().execute(() -> {
            //Stores an Array of Restaurants to be read
            TypedArray typedArrayRestaurants = null;
            try {
                //Obtaining the Typed Array of Restaurants
                typedArrayRestaurants = mResources.obtainTypedArray(arrayResId);
                //The Number of Restaurants to load
                int noOfRestaurants = typedArrayRestaurants.length();
                if (noOfRestaurants > 0) {
                    //When we have some Restaurants to be loaded

                    //Initializing an ArrayList of Restaurant data to store the Restaurant information
                    //for all the Restaurants read from the Typed Array
                    ArrayList<Restaurant> restaurantList = new ArrayList<>();
                    //Iterating over the Typed Array to build the list
                    for (int index = 0; index < noOfRestaurants; index++) {
                        //Getting the Resource ID of the Restaurant Array resource
                        int restaurantResourceId = typedArrayRestaurants.getResourceId(index, 0);
                        if (restaurantResourceId > 0) {
                            //When the resource read is valid

                            //Obtain the String Array for the resource which contains the data for the Restaurant
                            String[] restaurantStringArray = mResources.getStringArray(restaurantResourceId);
                            //Read the Name
                            String name = restaurantStringArray[Restaurant.Contract.NAME_INDEX].trim();
                            //Read the Restaurant Rating
                            float rating = Float.parseFloat(restaurantStringArray[Restaurant.Contract.RATING_INDEX]);
                            //Read the Cuisine Types
                            String cuisineTypes = restaurantStringArray[Restaurant.Contract.CUISINE_TYPES_INDEX].trim();
                            //Read the drawable resource Id for the Restaurant Photo
                            int photoResId = BitmapUtility.findDrawableResourceId(
                                    restaurantStringArray[Restaurant.Contract.PHOTO_INDEX],
                                    mAppPackageName,
                                    mResources
                            );
                            //Construct a Bitmap for the Restaurant Photo and extract the Palette
                            Bitmap photoBitmap = BitmapFactory.decodeResource(mResources, photoResId);
                            Palette.Swatch photoSwatch = BitmapUtility.extractVibrantSwatch(photoBitmap);
                            //Read the Timings
                            String timings = restaurantStringArray[Restaurant.Contract.TIMINGS_INDEX].trim();
                            //Read the Average Cost
                            String averageCost = restaurantStringArray[Restaurant.Contract.COST_INDEX];
                            //Read the Location
                            String location = restaurantStringArray[Restaurant.Contract.LOCATION_INDEX].trim();
                            //Read the Contact Number
                            String contactNumber = restaurantStringArray[Restaurant.Contract.CONTACT_INDEX].trim();
                            //Read the Website
                            String website = restaurantStringArray[Restaurant.Contract.WEBSITE_INDEX].trim();

                            //Construct the Restaurant data with the data read
                            Restaurant restaurant = new Restaurant.Builder()
                                    .setName(name)
                                    .setRating(rating)
                                    .setCuisineTypes(cuisineTypes)
                                    .setPhotoResId(photoResId)
                                    .setTimings(timings)
                                    .setAverageCost(averageCost)
                                    .setLocation(location)
                                    .setContactNumber(contactNumber)
                                    .setWebsite(website)
                                    .setSwatchGenerated(photoSwatch != null)
                                    .setSwatchBodyTextColor(photoSwatch != null ? photoSwatch.getBodyTextColor() : 0)
                                    .setSwatchTitleTextColor(photoSwatch != null ? photoSwatch.getTitleTextColor() : 0)
                                    .setSwatchRgbColor(photoSwatch != null ? photoSwatch.getRgb() : 0)
                                    .createRestaurant();

                            //Add to the list of Restaurant data constructed
                            restaurantList.add(restaurant);
                        }
                    }

                    if (restaurantList.size() > 0) {
                        //When we have restaurants data to be loaded, pass the result to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the list of Restaurants to the callback
                            resourceCallback.onResults(restaurantList);
                        });
                    } else {
                        //When we have no restaurants to be loaded, pass an error message to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the error message to the callback
                            resourceCallback.onFailure(R.string.restaurant_list_load_error);
                        });
                    }

                } else {
                    //When we have no restaurants to be loaded, pass an error message to the callback

                    //Executing on the Main Thread
                    mAppExecutors.getMainThread().execute(() -> {
                        //Pass the error message to the callback
                        resourceCallback.onFailure(R.string.restaurant_list_load_error);
                    });
                }
            } finally {
                //Finally, release the TypedArray resource if held
                if (typedArrayRestaurants != null) {
                    typedArrayRestaurants.recycle();
                }
            }
        });
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
        //Executing on the Disk Thread
        mAppExecutors.getDiskIO().execute(() -> {
            //Stores an Array of Shops to be read
            TypedArray typedArrayShops = null;
            try {
                //Obtaining the Typed Array of Shops
                typedArrayShops = mResources.obtainTypedArray(R.array.shop_list_entries);
                //The Number of Shops to load
                int noOfShops = typedArrayShops.length();
                if (noOfShops > 0) {
                    //When we have some Shops to be loaded

                    //Initializing an ArrayList of Shop data to store the Shop information
                    //for all the Shops read from the Typed Array
                    ArrayList<Shop> shopList = new ArrayList<>();
                    //Iterating over the Typed Array to build the list
                    for (int index = 0; index < noOfShops; index++) {
                        //Getting the Resource ID of the Shop Array resource
                        int shopResourceId = typedArrayShops.getResourceId(index, 0);
                        if (shopResourceId > 0) {
                            //When the resource read is valid

                            //Obtain the String Array for the resource which contains the data for the Shop
                            String[] shopStringArray = mResources.getStringArray(shopResourceId);
                            //Read the Name
                            String name = shopStringArray[Shop.Contract.NAME_INDEX].trim();
                            //Read the Rating
                            float rating = Float.parseFloat(shopStringArray[Shop.Contract.RATING_INDEX]);
                            //Read the Shop Types
                            String shopTypes = shopStringArray[Shop.Contract.SHOP_TYPES_INDEX].trim();
                            //Read the drawable resource Id for the Shop Photo
                            int photoResId = BitmapUtility.findDrawableResourceId(
                                    shopStringArray[Shop.Contract.PHOTO_INDEX],
                                    mAppPackageName,
                                    mResources
                            );
                            //Construct a Bitmap for the Shop Photo and extract the Palette
                            Bitmap photoBitmap = BitmapFactory.decodeResource(mResources, photoResId);
                            Palette.Swatch photoSwatch = BitmapUtility.extractVibrantSwatch(photoBitmap);
                            //Read the Timings
                            String timings = shopStringArray[Shop.Contract.TIMINGS_INDEX].trim();
                            //Read the Location
                            String location = shopStringArray[Shop.Contract.LOCATION_INDEX].trim();
                            //Read the Website
                            String website = shopStringArray[Shop.Contract.WEBSITE_INDEX].trim();

                            //Construct the Shop data with the data read
                            Shop shop = new Shop.Builder()
                                    .setName(name)
                                    .setRating(rating)
                                    .setShopTypes(shopTypes)
                                    .setPhotoResId(photoResId)
                                    .setTimings(timings)
                                    .setLocation(location)
                                    .setWebsite(website)
                                    .setSwatchGenerated(photoSwatch != null)
                                    .setSwatchBodyTextColor(photoSwatch != null ? photoSwatch.getBodyTextColor() : 0)
                                    .setSwatchTitleTextColor(photoSwatch != null ? photoSwatch.getTitleTextColor() : 0)
                                    .setSwatchRgbColor(photoSwatch != null ? photoSwatch.getRgb() : 0)
                                    .createShop();

                            //Add to the list of Shop data constructed
                            shopList.add(shop);
                        }
                    }

                    if (shopList.size() > 0) {
                        //When we have shops data to be loaded, pass the result to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the list of Shops to the callback
                            resourceCallback.onResults(shopList);
                        });
                    } else {
                        //When we have no shops to be loaded, pass an error message to the callback

                        //Executing on the Main Thread
                        mAppExecutors.getMainThread().execute(() -> {
                            //Pass the error message to the callback
                            resourceCallback.onFailure(R.string.shop_list_load_error);
                        });
                    }

                } else {
                    //When we have no shops to be loaded, pass an error message to the callback

                    //Executing on the Main Thread
                    mAppExecutors.getMainThread().execute(() -> {
                        //Pass the error message to the callback
                        resourceCallback.onFailure(R.string.shop_list_load_error);
                    });
                }
            } finally {
                //Finally, release the TypedArray resource if held
                if (typedArrayShops != null) {
                    typedArrayShops.recycle();
                }
            }
        });
    }

}