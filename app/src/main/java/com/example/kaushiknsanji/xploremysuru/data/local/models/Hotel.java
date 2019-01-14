package com.example.kaushiknsanji.xploremysuru.data.local.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

/**
 * Parcelable Model class for storing the details of a Hotel.
 *
 * @author Kaushik N Sanji
 */
public class Hotel implements Parcelable {

    /**
     * Implementation of {@link android.os.Parcelable.Creator} interface
     * to generate instances of this Parcelable class {@link Hotel} from a {@link Parcel}
     */
    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        /**
         * Creates an instance of this Parcelable class {@link Hotel} from
         * a given Parcel whose data had been previously written by #writeToParcel() method
         *
         * @param in The Parcel to read the object's data from.
         * @return Returns a new instance of this Parcelable class {@link Hotel}
         */
        @Override
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        /**
         * Creates a new array of this Parcelable class {@link Hotel}
         *
         * @param size Size of the array
         * @return Returns an array of this Parcelable class {@link Hotel}, with every
         * entry initialized to null
         */
        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };
    //The Unique Id of the Hotel
    private final int mId;
    //The Name of the Hotel
    private final String mName;
    //Icon Resource Id of the Hotel Star Rating
    private final int mStarTypeResId;
    //Traveller's Rating for the Hotel
    private final float mRating;
    //Photo Resource Id of the Hotel
    private final int mPhotoResId;
    //Cost of Stay in the Hotel
    private final String mCostOfStay;
    //Location address of the Hotel
    private final String mLocation;
    //Contact Number of the Hotel
    private final String mContactNumber;
    //Website of the Hotel
    private final String mWebsite;
    //Boolean that indicates if a Palette Swatch was generated for the Hotel Photo
    private final boolean mSwatchGenerated;
    //The Swatch Text Color to be used for the Body Text
    private final int mSwatchBodyTextColor;
    //The Swatch Text Color to be used for the Title Text
    private final int mSwatchTitleTextColor;
    //The Swatch Color
    private final int mSwatchRgbColor;

    /**
     * Private Constructor of {@link Hotel}
     *
     * @param id                   The Unique Id of the Hotel
     * @param name                 The Name of the Hotel
     * @param starTypeResId        Icon Resource Id of the Hotel Star Rating
     * @param rating               Traveller's Rating for the Hotel
     * @param photoResId           Photo Resource Id of the Hotel
     * @param costOfStay           Cost of Stay in the Hotel
     * @param location             Location address of the Hotel
     * @param contactNumber        Contact Number of the Hotel
     * @param website              Website of the Hotel
     * @param swatchGenerated      Boolean that indicates if a Palette Swatch was generated for the Hotel Photo
     * @param swatchBodyTextColor  The Swatch Text Color to be used for the Body Text
     * @param swatchTitleTextColor The Swatch Text Color to be used for the Title Text
     * @param swatchRgbColor       The Swatch Color
     */
    private Hotel(int id, String name, @DrawableRes int starTypeResId,
                  float rating, @DrawableRes int photoResId, String costOfStay,
                  String location, String contactNumber, String website,
                  boolean swatchGenerated, int swatchBodyTextColor,
                  int swatchTitleTextColor, int swatchRgbColor) {
        mId = id;
        mName = name;
        mStarTypeResId = starTypeResId;
        mRating = rating;
        mPhotoResId = photoResId;
        mCostOfStay = costOfStay;
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
    protected Hotel(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mStarTypeResId = in.readInt();
        mRating = in.readFloat();
        mPhotoResId = in.readInt();
        mCostOfStay = in.readString();
        mLocation = in.readString();
        mContactNumber = in.readString();
        mWebsite = in.readString();
        mSwatchGenerated = in.readByte() != 0;
        mSwatchBodyTextColor = in.readInt();
        mSwatchTitleTextColor = in.readInt();
        mSwatchRgbColor = in.readInt();
    }

    /**
     * Flattens/Serializes the object of {@link Hotel} into a Parcel
     *
     * @param dest  The Parcel in which the object should be written
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeInt(mStarTypeResId);
        dest.writeFloat(mRating);
        dest.writeInt(mPhotoResId);
        dest.writeString(mCostOfStay);
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
     * Getter Method for the Unique Id of the Hotel
     *
     * @return The Unique Integer Id of the Hotel
     */
    public int getId() {
        return mId;
    }

    /**
     * Getter Method for the Name of the Hotel
     *
     * @return The Name of the Hotel
     */
    public String getName() {
        return mName;
    }

    /**
     * Getter Method for the Icon Resource Id of the Hotel Star Rating
     *
     * @return An Integer representing the Icon Resource Id of the Hotel Star Rating
     */
    public int getStarTypeResId() {
        return mStarTypeResId;
    }

    /**
     * Getter Method for the Traveller's Rating for the Hotel
     *
     * @return Float representing the Traveller's Rating for the Hotel
     */
    public float getRating() {
        return mRating;
    }

    /**
     * Getter Method for the Photo Resource Id of the Hotel
     *
     * @return An Integer representing the Photo Resource Id of the Hotel
     */
    public int getPhotoResId() {
        return mPhotoResId;
    }

    /**
     * Getter Method for the Cost of Stay in the Hotel
     *
     * @return The Cost of Stay in the Hotel
     */
    public String getCostOfStay() {
        return mCostOfStay;
    }

    /**
     * Getter Method for the Location Address of the Hotel
     *
     * @return The Location Address of the Hotel
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Getter Method for the Contact Number of the Hotel
     *
     * @return The Contact Number of the Hotel
     */
    public String getContactNumber() {
        return mContactNumber;
    }

    /**
     * Getter Method for the Website of the Hotel
     *
     * @return The Website of the Hotel
     */
    public String getWebsite() {
        return mWebsite;
    }

    /**
     * Getter Method for the Boolean that indicates if a Palette Swatch was generated for the Hotel Photo
     *
     * @return Boolean that indicates if a Palette Swatch was generated for the Hotel Photo
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
     * Contract Interface for the data index of the Hotel arrays stored in resources
     */
    public interface Contract {
        int NAME_INDEX = 0;
        int STAR_TYPE_ICON_INDEX = 1;
        int RATING_INDEX = 2;
        int PHOTO_INDEX = 3;
        int COST_INDEX = 4;
        int LOCATION_INDEX = 5;
        int CONTACT_INDEX = 6;
        int WEBSITE_INDEX = 7;
    }

    /**
     * Static Builder class that constructs {@link Hotel}
     */
    public static class Builder {

        private int mId;
        private String mName;
        private int mStarTypeResId;
        private float mRating;
        private int mPhotoResId;
        private String mCostOfStay;
        private String mLocation;
        private String mContactNumber;
        private String mWebsite;
        private boolean mSwatchGenerated;
        private int mSwatchBodyTextColor;
        private int mSwatchTitleTextColor;
        private int mSwatchRgbColor;

        /**
         * Setter for the Integer Unique Id of the Hotel
         *
         * @param id Integer Unique Id of the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setId(int id) {
            mId = id;
            return this;
        }

        /**
         * Setter for the Name of the Hotel
         *
         * @param name The Name of the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Setter for Icon Resource Id of the Hotel Star Rating
         *
         * @param starTypeResId Integer representing the Icon Resource Id of the Hotel Star Rating
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setStarTypeResId(@DrawableRes int starTypeResId) {
            mStarTypeResId = starTypeResId;
            return this;
        }

        /**
         * Setter for the Traveller's Rating for the Hotel
         *
         * @param rating Float representing the Traveller's Rating for the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setRating(float rating) {
            mRating = rating;
            return this;
        }

        /**
         * Setter for the Photo Resource Id of the Hotel
         *
         * @param photoResId Integer representing the Photo Resource Id of the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setPhotoResId(@DrawableRes int photoResId) {
            mPhotoResId = photoResId;
            return this;
        }

        /**
         * Setter for the Cost of Stay in the Hotel
         *
         * @param costOfStay The Cost of Stay in the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setCostOfStay(String costOfStay) {
            mCostOfStay = costOfStay;
            return this;
        }

        /**
         * Setter for the Location Address of the Hotel
         *
         * @param location The Location Address of the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setLocation(String location) {
            mLocation = location;
            return this;
        }

        /**
         * Setter for the Contact Number of the Hotel
         *
         * @param contactNumber The Contact Number of the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setContactNumber(String contactNumber) {
            mContactNumber = contactNumber;
            return this;
        }

        /**
         * Setter for the Website of the Hotel
         *
         * @param website The Website of the Hotel
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setWebsite(String website) {
            mWebsite = website;
            return this;
        }

        /**
         * Setter for the Boolean that indicates if a Palette Swatch was generated for the Hotel Photo
         *
         * @param swatchGenerated Boolean that indicates if a Palette Swatch was generated for the Hotel Photo
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
         * Terminal Method that constructs the {@link Hotel}
         *
         * @return Instance of {@link Hotel} built
         */
        public Hotel createHotel() {
            return new Hotel(mId, mName, mStarTypeResId, mRating, mPhotoResId, mCostOfStay,
                    mLocation, mContactNumber, mWebsite, mSwatchGenerated,
                    mSwatchBodyTextColor, mSwatchTitleTextColor, mSwatchRgbColor);
        }
    }
}
