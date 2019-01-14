package com.example.kaushiknsanji.xploremysuru.data.local.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

/**
 * Parcelable Model class for storing the details of a Restaurant.
 *
 * @author Kaushik N Sanji
 */
public class Restaurant implements Parcelable {

    /**
     * Implementation of {@link android.os.Parcelable.Creator} interface
     * to generate instances of this Parcelable class {@link Restaurant} from a {@link Parcel}
     */
    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        /**
         * Creates an instance of this Parcelable class {@link Restaurant} from
         * a given Parcel whose data had been previously written by #writeToParcel() method
         *
         * @param in The Parcel to read the object's data from.
         * @return Returns a new instance of this Parcelable class {@link Restaurant}
         */
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        /**
         * Creates a new array of this Parcelable class {@link Restaurant}
         *
         * @param size Size of the array
         * @return Returns an array of this Parcelable class {@link Restaurant}, with every
         * entry initialized to null
         */
        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
    //The Unique Id of the Restaurant
    private final int mId;
    //The Name of the Restaurant
    private final String mName;
    //Foodie's Rating for the Restaurant
    private final float mRating;
    //Cuisine Types served by the Restaurant
    private final String mCuisineTypes;
    //Photo Resource Id of the Restaurant
    private final int mPhotoResId;
    //Timings of the Restaurant
    private final String mTimings;
    //Average Cost of dining at the Restaurant
    private final String mAverageCost;
    //Location address of the Restaurant
    private final String mLocation;
    //Contact Number of the Restaurant
    private final String mContactNumber;
    //Website of the Restaurant
    private final String mWebsite;
    //Boolean that indicates if a Palette Swatch was generated for the Restaurant Photo
    private final boolean mSwatchGenerated;
    //The Swatch Text Color to be used for the Body Text
    private final int mSwatchBodyTextColor;
    //The Swatch Text Color to be used for the Title Text
    private final int mSwatchTitleTextColor;
    //The Swatch Color
    private final int mSwatchRgbColor;

    /**
     * Private Constructor of {@link Restaurant}
     *
     * @param id                   The Unique Id of the Restaurant
     * @param name                 The Name of the Restaurant
     * @param rating               Foodie's Rating for the Restaurant
     * @param cuisineTypes         Cuisine Types served by the Restaurant
     * @param photoResId           Photo Resource Id of the Restaurant
     * @param timings              Timings of the Restaurant
     * @param averageCost          Average Cost of dining at the Restaurant in words
     * @param location             Location address of the Restaurant
     * @param contactNumber        Contact Number of the Restaurant
     * @param website              Website of the Restaurant
     * @param swatchGenerated      Boolean that indicates if a Palette Swatch was generated for the Restaurant Photo
     * @param swatchBodyTextColor  The Swatch Text Color to be used for the Body Text
     * @param swatchTitleTextColor The Swatch Text Color to be used for the Title Text
     * @param swatchRgbColor       The Swatch Color
     */
    private Restaurant(int id, String name, float rating, String cuisineTypes,
                       @DrawableRes int photoResId, String timings, String averageCost,
                       String location, String contactNumber, String website,
                       boolean swatchGenerated, int swatchBodyTextColor,
                       int swatchTitleTextColor, int swatchRgbColor) {
        mId = id;
        mName = name;
        mRating = rating;
        mCuisineTypes = cuisineTypes;
        mPhotoResId = photoResId;
        mTimings = timings;
        mAverageCost = averageCost;
        mLocation = location;
        mContactNumber = contactNumber;
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
    protected Restaurant(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mRating = in.readFloat();
        mCuisineTypes = in.readString();
        mPhotoResId = in.readInt();
        mTimings = in.readString();
        mAverageCost = in.readString();
        mLocation = in.readString();
        mContactNumber = in.readString();
        mWebsite = in.readString();
        mSwatchGenerated = in.readByte() != 0;
        mSwatchBodyTextColor = in.readInt();
        mSwatchTitleTextColor = in.readInt();
        mSwatchRgbColor = in.readInt();
    }

    /**
     * Flattens/Serializes the object of {@link Restaurant} into a Parcel
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
        dest.writeString(mCuisineTypes);
        dest.writeInt(mPhotoResId);
        dest.writeString(mTimings);
        dest.writeString(mAverageCost);
        dest.writeString(mLocation);
        dest.writeString(mContactNumber);
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
     * Getter Method for the Unique Id of the Restaurant
     *
     * @return The Unique Integer Id of the Restaurant
     */
    public int getId() {
        return mId;
    }

    /**
     * Getter Method for the Name of the Restaurant
     *
     * @return The Name of the Restaurant
     */
    public String getName() {
        return mName;
    }

    /**
     * Getter Method for the Foodie's Rating for the Restaurant
     *
     * @return Float representing the Foodie's Rating for the Restaurant
     */
    public float getRating() {
        return mRating;
    }

    /**
     * Getter Method for the Cuisine Types served by the Restaurant
     *
     * @return The Cuisine Types served by the Restaurant
     */
    public String getCuisineTypes() {
        return mCuisineTypes;
    }

    /**
     * Getter Method for the Photo Resource Id of the Restaurant
     *
     * @return An Integer representing the Photo Resource Id of the Restaurant
     */
    public int getPhotoResId() {
        return mPhotoResId;
    }

    /**
     * Getter Method for the Timings of the Restaurant
     *
     * @return The Timings of the Restaurant
     */
    public String getTimings() {
        return mTimings;
    }

    /**
     * Getter Method for the Average Cost of dining at the Restaurant
     *
     * @return The Average Cost of dining at the Restaurant in words
     */
    public String getAverageCost() {
        return mAverageCost;
    }

    /**
     * Getter Method for the Location address of the Restaurant
     *
     * @return The Location address of the Restaurant
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Getter Method for the Contact Number of the Restaurant
     *
     * @return The Contact Number of the Restaurant
     */
    public String getContactNumber() {
        return mContactNumber;
    }

    /**
     * Getter Method for the Website of the Restaurant
     *
     * @return The Website of the Restaurant
     */
    public String getWebsite() {
        return mWebsite;
    }

    /**
     * Getter Method for the Boolean that indicates if a Palette Swatch was generated for the Restaurant Photo
     *
     * @return Boolean that indicates if a Palette Swatch was generated for the Restaurant Photo
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
     * Contract Interface for the data index of the Restaurant arrays stored in resources
     */
    public interface Contract {
        int NAME_INDEX = 0;
        int CUISINE_TYPES_INDEX = 1;
        int RATING_INDEX = 2;
        int PHOTO_INDEX = 3;
        int TIMINGS_INDEX = 4;
        int COST_INDEX = 5;
        int LOCATION_INDEX = 6;
        int CONTACT_INDEX = 7;
        int WEBSITE_INDEX = 8;
    }

    /**
     * Static Builder class that constructs {@link Restaurant}
     */
    public static class Builder {

        private int mId;
        private String mName;
        private float mRating;
        private String mCuisineTypes;
        private int mPhotoResId;
        private String mTimings;
        private String mAverageCost;
        private String mLocation;
        private String mContactNumber;
        private String mWebsite;
        private boolean mSwatchGenerated;
        private int mSwatchBodyTextColor;
        private int mSwatchTitleTextColor;
        private int mSwatchRgbColor;

        /**
         * Setter for the Integer Unique Id of the Restaurant
         *
         * @param id Integer Unique Id of the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setId(int id) {
            mId = id;
            return this;
        }

        /**
         * Setter for the Name of the Restaurant
         *
         * @param name The Name of the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Setter for the Foodie's Rating for the Restaurant
         *
         * @param rating Float representing the Foodie's Rating for the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setRating(float rating) {
            mRating = rating;
            return this;
        }

        /**
         * Setter for the Cuisine Types served by the Restaurant
         *
         * @param cuisineTypes The Cuisine Types served by the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setCuisineTypes(String cuisineTypes) {
            mCuisineTypes = cuisineTypes;
            return this;
        }

        /**
         * Setter for the Photo Resource Id of the Restaurant
         *
         * @param photoResId Integer representing the Photo Resource Id of the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setPhotoResId(@DrawableRes int photoResId) {
            mPhotoResId = photoResId;
            return this;
        }

        /**
         * Setter for the Timings of the Restaurant
         *
         * @param timings The Timings of the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setTimings(String timings) {
            mTimings = timings;
            return this;
        }

        /**
         * Setter for the Average Cost of dining at the Restaurant
         *
         * @param averageCost The Average Cost of dining at the Restaurant in words
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setAverageCost(String averageCost) {
            mAverageCost = averageCost;
            return this;
        }

        /**
         * Setter for the Location Address of the Restaurant
         *
         * @param location The Location Address of the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setLocation(String location) {
            mLocation = location;
            return this;
        }

        /**
         * Setter for the Contact Number of the Restaurant
         *
         * @param contactNumber The Contact Number of the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setContactNumber(String contactNumber) {
            mContactNumber = contactNumber;
            return this;
        }

        /**
         * Setter for the Website of the Restaurant
         *
         * @param website The Website of the Restaurant
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setWebsite(String website) {
            mWebsite = website;
            return this;
        }

        /**
         * Setter for the Boolean that indicates if a Palette Swatch was generated for the Restaurant Photo
         *
         * @param swatchGenerated Boolean that indicates if a Palette Swatch was generated for the Restaurant Photo
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
         * Terminal Method that constructs the {@link Restaurant}
         *
         * @return Instance of {@link Restaurant} built
         */
        public Restaurant createRestaurant() {
            return new Restaurant(mId, mName, mRating, mCuisineTypes, mPhotoResId,
                    mTimings, mAverageCost, mLocation, mContactNumber, mWebsite,
                    mSwatchGenerated, mSwatchBodyTextColor, mSwatchTitleTextColor, mSwatchRgbColor);
        }
    }
}
