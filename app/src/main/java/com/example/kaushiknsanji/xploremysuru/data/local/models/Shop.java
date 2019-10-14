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

package com.example.kaushiknsanji.xploremysuru.data.local.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

/**
 * Parcelable Model class for storing the details of a Shop.
 *
 * @author Kaushik N Sanji
 */
public class Shop implements Parcelable {

    /**
     * Implementation of {@link android.os.Parcelable.Creator} interface
     * to generate instances of this Parcelable class {@link Shop} from a {@link Parcel}
     */
    public static final Creator<Shop> CREATOR = new Creator<Shop>() {
        /**
         * Creates an instance of this Parcelable class {@link Shop} from
         * a given Parcel whose data had been previously written by #writeToParcel() method
         *
         * @param in The Parcel to read the object's data from.
         * @return Returns a new instance of this Parcelable class {@link Shop}
         */
        @Override
        public Shop createFromParcel(Parcel in) {
            return new Shop(in);
        }

        /**
         * Creates a new array of this Parcelable class {@link Shop}
         *
         * @param size Size of the array
         * @return Returns an array of this Parcelable class {@link Shop}, with every
         * entry initialized to null
         */
        @Override
        public Shop[] newArray(int size) {
            return new Shop[size];
        }
    };
    //The Unique Id of the Shop
    private final int mId;
    //The Name of the Shop
    private final String mName;
    //User Rating of the Shop
    private final float mRating;
    //Photo Resource Id of the Shop
    private final int mPhotoResId;
    //Types/keywords describing the Shop
    private final String mShopTypes;
    //Timings of the Shop
    private final String mTimings;
    //Location address of the Shop
    private final String mLocation;
    //Website of the Shop
    private final String mWebsite;
    //Boolean that indicates if a Palette Swatch was generated for the Shop Photo
    private final boolean mSwatchGenerated;
    //The Swatch Text Color to be used for the Body Text
    private final int mSwatchBodyTextColor;
    //The Swatch Text Color to be used for the Title Text
    private final int mSwatchTitleTextColor;
    //The Swatch Color
    private final int mSwatchRgbColor;

    /**
     * Private Constructor of {@link Shop}
     *
     * @param id                   The Unique Id of the Shop
     * @param name                 The Name of the Shop
     * @param rating               User Rating of the Shop
     * @param photoResId           Photo Resource Id of the Shop
     * @param shopTypes            Types/keywords describing the Shop
     * @param timings              Timings of the Shop
     * @param location             Location address of the Shop
     * @param website              Website of the Shop
     * @param swatchGenerated      Boolean that indicates if a Palette Swatch was generated for the Shop Photo
     * @param swatchBodyTextColor  The Swatch Text Color to be used for the Body Text
     * @param swatchTitleTextColor The Swatch Text Color to be used for the Title Text
     * @param swatchRgbColor       The Swatch Color
     */
    private Shop(int id, String name, float rating, @DrawableRes int photoResId,
                 String shopTypes, String timings, String location, String website,
                 boolean swatchGenerated, int swatchBodyTextColor,
                 int swatchTitleTextColor, int swatchRgbColor) {
        mId = id;
        mName = name;
        mRating = rating;
        mPhotoResId = photoResId;
        mShopTypes = shopTypes;
        mTimings = timings;
        mLocation = location;
        mWebsite = website;
        mSwatchGenerated = swatchGenerated;
        mSwatchBodyTextColor = swatchBodyTextColor;
        mSwatchTitleTextColor = swatchTitleTextColor;
        mSwatchRgbColor = swatchRgbColor;
    }

    /**
     * Parcelable constructor that de-serializes the data from a Parcel {@code in} passed
     *
     * @param in The Instance of the Parcel class containing the serialized data
     */
    protected Shop(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mRating = in.readFloat();
        mPhotoResId = in.readInt();
        mShopTypes = in.readString();
        mTimings = in.readString();
        mLocation = in.readString();
        mWebsite = in.readString();
        mSwatchGenerated = in.readByte() != 0;
        mSwatchBodyTextColor = in.readInt();
        mSwatchTitleTextColor = in.readInt();
        mSwatchRgbColor = in.readInt();
    }

    /**
     * Flattens/Serializes the object of {@link Shop} into a Parcel
     *
     * @param dest  The Parcel in which the object should be written
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeFloat(mRating);
        dest.writeInt(mPhotoResId);
        dest.writeString(mShopTypes);
        dest.writeString(mTimings);
        dest.writeString(mLocation);
        dest.writeString(mWebsite);
        dest.writeByte((byte) (mSwatchGenerated ? 1 : 0));
        dest.writeInt(mSwatchBodyTextColor);
        dest.writeInt(mSwatchTitleTextColor);
        dest.writeInt(mSwatchRgbColor);
    }

    /**
     * Describes the kinds of special objects contained in this Parcelable
     * instance's marshaled representation.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0; //Indicating with no mask
    }

    /**
     * Getter Method for the Unique Id of the Shop
     *
     * @return The Unique Integer Id of the Shop
     */
    public int getId() {
        return mId;
    }

    /**
     * Getter Method for the Name of the Shop
     *
     * @return The Name of the Shop
     */
    public String getName() {
        return mName;
    }

    /**
     * Getter Method for the User Rating of the Shop
     *
     * @return A Float representing the User Rating of the Shop
     */
    public float getRating() {
        return mRating;
    }

    /**
     * Getter Method for the Photo Resource Id of the Shop
     *
     * @return An Integer representing the Photo Resource Id of the Shop
     */
    public int getPhotoResId() {
        return mPhotoResId;
    }

    /**
     * Getter Method for the Types/keywords describing the Shop
     *
     * @return The Types/keywords describing the Shop
     */
    public String getShopTypes() {
        return mShopTypes;
    }

    /**
     * Getter Method for the Timings of the Shop
     *
     * @return The Timings of the Shop
     */
    public String getTimings() {
        return mTimings;
    }

    /**
     * Getter Method for the Location address of the Shop
     *
     * @return The Location address of the Shop
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Getter Method for the Website of the Shop
     *
     * @return The Website of the Shop
     */
    public String getWebsite() {
        return mWebsite;
    }

    /**
     * Getter Method for the Boolean that indicates if a Palette Swatch was generated for the Shop Photo
     *
     * @return Boolean that indicates if a Palette Swatch was generated for the Shop Photo
     */
    public boolean isSwatchGenerated() {
        return mSwatchGenerated;
    }

    /**
     * Getter Method for the Swatch Text Color to be used for the Body Text
     *
     * @return Integer representing the Swatch Text Color to be used for the Body Text
     */
    public int getSwatchBodyTextColor() {
        return mSwatchBodyTextColor;
    }

    /**
     * Getter Method for the Swatch Text Color to be used for the Title Text
     *
     * @return Integer representing the Swatch Text Color to be used for the Title Text
     */
    public int getSwatchTitleTextColor() {
        return mSwatchTitleTextColor;
    }

    /**
     * Getter Method for the Swatch Color
     *
     * @return Integer representing the Swatch Color
     */
    public int getSwatchRgbColor() {
        return mSwatchRgbColor;
    }

    /**
     * Contract Interface for the data index of the Shop arrays stored in resources
     */
    public interface Contract {
        int NAME_INDEX = 0;
        int SHOP_TYPES_INDEX = 1;
        int RATING_INDEX = 2;
        int PHOTO_INDEX = 3;
        int TIMINGS_INDEX = 4;
        int LOCATION_INDEX = 5;
        int WEBSITE_INDEX = 6;
    }

    /**
     * Static Builder class that constructs {@link Shop}
     */
    public static class Builder {

        private int mId;
        private String mName;
        private float mRating;
        private int mPhotoResId;
        private String mShopTypes;
        private String mTimings;
        private String mLocation;
        private String mWebsite;
        private boolean mSwatchGenerated;
        private int mSwatchBodyTextColor;
        private int mSwatchTitleTextColor;
        private int mSwatchRgbColor;

        /**
         * Setter for the Integer Unique Id of the Shop
         *
         * @param id Integer Unique Id of the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setId(int id) {
            mId = id;
            return this;
        }

        /**
         * Setter for the Name of the Shop
         *
         * @param name The Name of the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Setter for the User Rating of the Shop
         *
         * @param rating Float value of the User Rating of the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setRating(float rating) {
            mRating = rating;
            return this;
        }

        /**
         * Setter for the Photo Resource Id of the Shop
         *
         * @param photoResId Integer representing the Photo Resource Id of the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setPhotoResId(@DrawableRes int photoResId) {
            mPhotoResId = photoResId;
            return this;
        }

        /**
         * Setter for the Types/keywords describing the Shop
         *
         * @param shopTypes The Types/keywords describing the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setShopTypes(String shopTypes) {
            mShopTypes = shopTypes;
            return this;
        }

        /**
         * Setter for the Timings of the Shop
         *
         * @param timings The Timings of the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setTimings(String timings) {
            mTimings = timings;
            return this;
        }

        /**
         * Setter for the Location Address of the Shop
         *
         * @param location The Location Address of the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setLocation(String location) {
            mLocation = location;
            return this;
        }

        /**
         * Setter for the Website of the Shop
         *
         * @param website The Website of the Shop
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setWebsite(String website) {
            mWebsite = website;
            return this;
        }

        /**
         * Setter for the Boolean that indicates if a Palette Swatch was generated for the Shop Photo
         *
         * @param swatchGenerated Boolean that indicates if a Palette Swatch was generated for the Shop Photo
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setSwatchGenerated(boolean swatchGenerated) {
            mSwatchGenerated = swatchGenerated;
            return this;
        }

        /**
         * Setter for the Swatch Text Color to be used for the Body Text
         *
         * @param swatchBodyTextColor The Swatch Text Color to be used for the Body Text
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setSwatchBodyTextColor(int swatchBodyTextColor) {
            mSwatchBodyTextColor = swatchBodyTextColor;
            return this;
        }

        /**
         * Setter for the Swatch Text Color to be used for the Title Text
         *
         * @param swatchTitleTextColor The Swatch Text Color to be used for the Title Text
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setSwatchTitleTextColor(int swatchTitleTextColor) {
            mSwatchTitleTextColor = swatchTitleTextColor;
            return this;
        }

        /**
         * Setter for the Swatch Color
         *
         * @param swatchRgbColor The Swatch Color
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setSwatchRgbColor(int swatchRgbColor) {
            mSwatchRgbColor = swatchRgbColor;
            return this;
        }

        /**
         * Terminal Method that constructs the {@link Shop}
         *
         * @return Instance of {@link Shop} built
         */
        public Shop createShop() {
            return new Shop(mId, mName, mRating, mPhotoResId, mShopTypes, mTimings,
                    mLocation, mWebsite, mSwatchGenerated, mSwatchBodyTextColor,
                    mSwatchTitleTextColor, mSwatchRgbColor);
        }
    }
}
