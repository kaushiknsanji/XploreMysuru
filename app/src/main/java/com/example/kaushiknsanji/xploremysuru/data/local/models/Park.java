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
 * Parcelable Model class for storing the details of a Park.
 *
 * @author Kaushik N Sanji
 */
public class Park implements Parcelable {

    /**
     * Implementation of {@link android.os.Parcelable.Creator} interface
     * to generate instances of this Parcelable class {@link Park} from a {@link Parcel}
     */
    public static final Creator<Park> CREATOR = new Creator<Park>() {
        /**
         * Creates an instance of this Parcelable class {@link Park} from
         * a given Parcel whose data had been previously written by #writeToParcel() method
         *
         * @param in The Parcel to read the object's data from.
         * @return Returns a new instance of this Parcelable class {@link Park}
         */
        @Override
        public Park createFromParcel(Parcel in) {
            return new Park(in);
        }

        /**
         * Creates a new array of this Parcelable class {@link Park}
         *
         * @param size Size of the array
         * @return Returns an array of this Parcelable class {@link Park}, with every
         * entry initialized to null
         */
        @Override
        public Park[] newArray(int size) {
            return new Park[size];
        }
    };
    //The Unique Id of the Park
    private final int mId;
    //The Name of the Park
    private final String mName;
    //User Rating of the Park
    private final float mRating;
    //Photo Resource Id of the Park
    private final int mPhotoResId;
    //Access Timings of the Park
    private final String mAccessTimeInfo;
    //The Entry Fee for the Park
    private final String mEntryFeeInfo;
    //Description of the Park
    private final String mDescription;
    //The Location Address of the Park
    private final String mLocation;
    //Website of the Park
    private final String mWebsite;
    //Boolean that indicates if a Palette Swatch was generated for the Park Photo
    private final boolean mSwatchGenerated;
    //The Swatch Text Color to be used for the Body Text
    private final int mSwatchBodyTextColor;
    //The Swatch Text Color to be used for the Title Text
    private final int mSwatchTitleTextColor;
    //The Swatch Color
    private final int mSwatchRgbColor;

    /**
     * Private Constructor of {@link Park}
     *
     * @param id                   The Unique Id of the Park
     * @param name                 The Name of the Park
     * @param rating               User Rating of the Park
     * @param photoResId           Photo Resource Id of the Park
     * @param accessTimeInfo       Access Timings of the Park
     * @param entryFeeInfo         The Entry Fee for the Park
     * @param description          Description of the Park
     * @param location             The Location Address of the Park
     * @param website              Website of the Park
     * @param swatchGenerated      Boolean that indicates if a Palette Swatch was generated for the Park Photo
     * @param swatchBodyTextColor  The Swatch Text Color to be used for the Body Text
     * @param swatchTitleTextColor The Swatch Text Color to be used for the Title Text
     * @param swatchRgbColor       The Swatch Color
     */
    private Park(int id, String name, float rating, @DrawableRes int photoResId,
                 String accessTimeInfo, String entryFeeInfo, String description,
                 String location, String website, boolean swatchGenerated,
                 int swatchBodyTextColor, int swatchTitleTextColor, int swatchRgbColor) {
        mId = id;
        mName = name;
        mRating = rating;
        mPhotoResId = photoResId;
        mAccessTimeInfo = accessTimeInfo;
        mEntryFeeInfo = entryFeeInfo;
        mDescription = description;
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
    protected Park(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mRating = in.readFloat();
        mPhotoResId = in.readInt();
        mAccessTimeInfo = in.readString();
        mEntryFeeInfo = in.readString();
        mDescription = in.readString();
        mLocation = in.readString();
        mWebsite = in.readString();
        mSwatchGenerated = in.readByte() != 0;
        mSwatchBodyTextColor = in.readInt();
        mSwatchTitleTextColor = in.readInt();
        mSwatchRgbColor = in.readInt();
    }

    /**
     * Flattens/Serializes the object of {@link Park} into a Parcel
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
        dest.writeString(mAccessTimeInfo);
        dest.writeString(mEntryFeeInfo);
        dest.writeString(mDescription);
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
     * Getter Method for the Unique Id of the Park
     *
     * @return The Unique Integer Id of the Park
     */
    public int getId() {
        return mId;
    }

    /**
     * Getter Method for the Name of the Park
     *
     * @return The Name of the Park
     */
    public String getName() {
        return mName;
    }

    /**
     * Getter Method for the User Rating of the Park
     *
     * @return A Float representing the User Rating of the Park
     */
    public float getRating() {
        return mRating;
    }

    /**
     * Getter Method for the Photo Resource Id of the Park
     *
     * @return An Integer representing the Photo Resource Id of the Park
     */
    public int getPhotoResId() {
        return mPhotoResId;
    }

    /**
     * Getter Method for the Access Timings of the Park
     *
     * @return The Access Timings of the Park
     */
    public String getAccessTimeInfo() {
        return mAccessTimeInfo;
    }

    /**
     * Getter Method for the Entry Fee for the Park
     *
     * @return The Entry Fee for the Park
     */
    public String getEntryFeeInfo() {
        return mEntryFeeInfo;
    }

    /**
     * Getter Method for the Description of the Park
     *
     * @return The Description of the Park
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Getter Method for the Location Address of the Park
     *
     * @return The Location Address of the Park
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Getter Method for the Website of the Park
     *
     * @return The Website of the Park
     */
    public String getWebsite() {
        return mWebsite;
    }

    /**
     * Getter Method for the Boolean that indicates if a Palette Swatch was generated for the Park Photo
     *
     * @return Boolean that indicates if a Palette Swatch was generated for the Park Photo
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
     * Contract Interface for the data index of the Park arrays stored in resources
     */
    public interface Contract {
        int NAME_INDEX = 0;
        int RATING_INDEX = 1;
        int PHOTO_INDEX = 2;
        int ACCESS_TIME_INDEX = 3;
        int ENTRY_FEE_INDEX = 4;
        int LOCATION_INDEX = 5;
        int DESCRIPTION_INDEX = 6;
        int WEBSITE_INDEX = 7;
    }

    /**
     * Static Builder class that constructs {@link Park}
     */
    public static class Builder {

        private int mId;
        private String mName;
        private float mRating;
        private int mPhotoResId;
        private String mAccessTimeInfo;
        private String mEntryFeeInfo;
        private String mDescription;
        private String mLocation;
        private String mWebsite;
        private boolean mSwatchGenerated;
        private int mSwatchBodyTextColor;
        private int mSwatchTitleTextColor;
        private int mSwatchRgbColor;

        /**
         * Setter for the Integer Unique Id of the Park
         *
         * @param id Integer Unique Id of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setId(int id) {
            mId = id;
            return this;
        }

        /**
         * Setter for the Name of the Park
         *
         * @param name The Name of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Setter for the User Rating of the Park
         *
         * @param rating Float value of the User Rating of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setRating(float rating) {
            mRating = rating;
            return this;
        }

        /**
         * Setter for the Photo Resource Id of the Park
         *
         * @param photoResId Integer representing the Photo Resource Id of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setPhotoResId(@DrawableRes int photoResId) {
            mPhotoResId = photoResId;
            return this;
        }

        /**
         * Setter for the Access Timings of the Park
         *
         * @param accessTimeInfo The Access Timings of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setAccessTimeInfo(String accessTimeInfo) {
            mAccessTimeInfo = accessTimeInfo;
            return this;
        }

        /**
         * Setter for the Entry Fee for the Park
         *
         * @param entryFeeInfo The Entry Fee for the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setEntryFeeInfo(String entryFeeInfo) {
            mEntryFeeInfo = entryFeeInfo;
            return this;
        }

        /**
         * Setter for the Description of the Park
         *
         * @param description The Description of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setDescription(String description) {
            mDescription = description;
            return this;
        }

        /**
         * Setter for the Location Address of the Park
         *
         * @param location The Location Address of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setLocation(String location) {
            mLocation = location;
            return this;
        }

        /**
         * Setter for the Website of the Park
         *
         * @param website The Website of the Park
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setWebsite(String website) {
            mWebsite = website;
            return this;
        }

        /**
         * Setter for the Boolean that indicates if a Palette Swatch was generated for the Park Photo
         *
         * @param swatchGenerated Boolean that indicates if a Palette Swatch was generated for the Park Photo
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
         * Terminal Method that constructs the {@link Park}
         *
         * @return Instance of {@link Park} built
         */
        public Park createPark() {
            return new Park(mId, mName, mRating, mPhotoResId, mAccessTimeInfo, mEntryFeeInfo,
                    mDescription, mLocation, mWebsite, mSwatchGenerated,
                    mSwatchBodyTextColor, mSwatchTitleTextColor, mSwatchRgbColor);
        }
    }
}
