package com.example.kaushiknsanji.xploremysuru.data.local.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

/**
 * Parcelable Model class for storing the details of a Place.
 *
 * @author Kaushik N Sanji
 */
public class Place implements Parcelable {
    /**
     * Implementation of {@link android.os.Parcelable.Creator} interface
     * to generate instances of this Parcelable class {@link Place} from a {@link Parcel}
     */
    public static final Creator<Place> CREATOR = new Creator<Place>() {
        /**
         * Creates an instance of this Parcelable class {@link Place} from
         * a given Parcel whose data had been previously written by #writeToParcel() method
         *
         * @param in The Parcel to read the object's data from.
         * @return Returns a new instance of this Parcelable class {@link Place}
         */
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        /**
         * Creates a new array of this Parcelable class {@link Place}
         *
         * @param size Size of the array
         * @return Returns an array of this Parcelable class {@link Place}, with every
         * entry initialized to null
         */
        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
    //The Unique Id of the Place
    private final int mId;
    //The Name of the Place
    private final String mName;
    //User Rating of the Place
    private final float mRating;
    //The Types/Keywords describing the Place
    private final String mTypes;
    //Icon Resource Id for the Place Type
    private final int mTypeImageResId;
    //Photo Resource Id of the Place
    private final int mPhotoResId;
    //Access Timings of the Place
    private final String mAccessTimeInfo;
    //The Entry Fee for the Place
    private final String mEntryFeeInfo;
    //Description of the Place
    private final String mDescription;
    //The Location Address of the Place
    private final String mLocation;
    //Website of the Place
    private final String mWebsite;
    //Boolean that indicates if a Palette Swatch was generated for the Place Photo
    private final boolean mSwatchGenerated;
    //The Swatch Text Color to be used for the Body Text
    private final int mSwatchBodyTextColor;
    //The Swatch Text Color to be used for the Title Text
    private final int mSwatchTitleTextColor;
    //The Swatch Color
    private final int mSwatchRgbColor;

    /**
     * Private Constructor of {@link Place}
     *
     * @param id                   The Unique Id of the Place
     * @param name                 The Name of the Place
     * @param rating               User Rating of the Place
     * @param types                The Types/Keywords describing the Place
     * @param typeImageResId       Icon Resource Id for the Place Type
     * @param photoResId           Photo Resource Id of the Place
     * @param accessTimeInfo       Access Timings of the Place
     * @param entryFeeInfo         The Entry Fee for the Place
     * @param description          Description of the Place
     * @param location             The Location Address of the Place
     * @param website              Website of the Place
     * @param swatchGenerated      Boolean that indicates if a Palette Swatch was generated for the Place Photo
     * @param swatchBodyTextColor  The Swatch Text Color to be used for the Body Text
     * @param swatchTitleTextColor The Swatch Text Color to be used for the Title Text
     * @param swatchRgbColor       The Swatch Color
     */
    private Place(int id, String name, float rating, String types,
                  @DrawableRes int typeImageResId, @DrawableRes int photoResId,
                  String accessTimeInfo, String entryFeeInfo, String description, String location,
                  String website, boolean swatchGenerated,
                  int swatchBodyTextColor, int swatchTitleTextColor, int swatchRgbColor) {
        mId = id;
        mName = name;
        mRating = rating;
        mTypes = types;
        mTypeImageResId = typeImageResId;
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
    protected Place(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mRating = in.readFloat();
        mTypes = in.readString();
        mTypeImageResId = in.readInt();
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
     * Flattens/Serializes the object of {@link Place} into a Parcel
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
        dest.writeString(mTypes);
        dest.writeInt(mTypeImageResId);
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
     * Getter Method for the Unique Id of the Place
     *
     * @return The Unique Integer Id of the Place
     */
    public int getId() {
        return mId;
    }

    /**
     * Getter Method for the Name of the Place
     *
     * @return The Name of the Place
     */
    public String getName() {
        return mName;
    }

    /**
     * Getter Method for the User Rating of the Place
     *
     * @return A Float representing the User Rating of the Place
     */
    public float getRating() {
        return mRating;
    }

    /**
     * Getter Method for the Types/Keywords describing the Place
     *
     * @return The Types/Keywords describing the Place
     */
    public String getTypes() {
        return mTypes;
    }

    /**
     * Getter Method for the Icon Resource Id for the Place Type
     *
     * @return An Integer representing the Icon Resource Id for the Place Type
     */
    public int getTypeImageResId() {
        return mTypeImageResId;
    }

    /**
     * Getter Method for the Photo Resource Id of the Place
     *
     * @return An Integer representing the Photo Resource Id of the Place
     */
    public int getPhotoResId() {
        return mPhotoResId;
    }

    /**
     * Getter Method for the Access Timings of the Place
     *
     * @return The Access Timings of the Place
     */
    public String getAccessTimeInfo() {
        return mAccessTimeInfo;
    }

    /**
     * Getter Method for the Entry Fee for the Place
     *
     * @return The Entry Fee for the Place
     */
    public String getEntryFeeInfo() {
        return mEntryFeeInfo;
    }

    /**
     * Getter Method for the Description of the Place
     *
     * @return The Description of the Place
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Getter Method for the Location Address of the Place
     *
     * @return The Location Address of the Place
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Getter Method for the Website of the Place
     *
     * @return The Website of the Place
     */
    public String getWebsite() {
        return mWebsite;
    }

    /**
     * Getter Method for the Boolean that indicates if a Palette Swatch was generated for the Place Photo
     *
     * @return Boolean that indicates if a Palette Swatch was generated for the Place Photo
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
     * Contract Interface for the data index of the Place arrays stored in resources
     */
    public interface Contract {
        int NAME_INDEX = 0;
        int RATING_INDEX = 1;
        int TYPES_INDEX = 2;
        int TYPE_ICON_INDEX = 3;
        int PHOTO_INDEX = 4;
        int ACCESS_TIME_INDEX = 5;
        int ENTRY_FEE_INDEX = 6;
        int LOCATION_INDEX = 7;
        int DESCRIPTION_INDEX = 8;
        int WEBSITE_INDEX = 9;
    }

    /**
     * Static Builder class that constructs {@link Place}
     */
    public static class Builder {

        private int mId;
        private String mName;
        private float mRating;
        private String mTypes;
        private int mTypeImageResId;
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
         * Setter for the Integer Unique Id of the Place
         *
         * @param id Integer Unique Id of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setId(int id) {
            mId = id;
            return this;
        }

        /**
         * Setter for the Name of the Place
         *
         * @param name The Name of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setName(String name) {
            mName = name;
            return this;
        }

        /**
         * Setter for the User Rating of the Place
         *
         * @param rating Float value of the User Rating of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setRating(float rating) {
            mRating = rating;
            return this;
        }

        /**
         * Setter for the Types/Keywords describing the Place
         *
         * @param types The Types/Keywords describing the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setTypes(String types) {
            mTypes = types;
            return this;
        }

        /**
         * Setter for the Icon Resource Id for the Place Type
         *
         * @param typeImageResId Integer representing the Icon Resource Id for the Place Type
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setTypeImageResId(@DrawableRes int typeImageResId) {
            mTypeImageResId = typeImageResId;
            return this;
        }

        /**
         * Setter for the Photo Resource Id of the Place
         *
         * @param photoResId Integer representing the Photo Resource Id of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setPhotoResId(@DrawableRes int photoResId) {
            mPhotoResId = photoResId;
            return this;
        }

        /**
         * Setter for the Access Timings of the Place
         *
         * @param accessTimeInfo The Access Timings of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setAccessTimeInfo(String accessTimeInfo) {
            mAccessTimeInfo = accessTimeInfo;
            return this;
        }

        /**
         * Setter for the Entry Fee for the Place
         *
         * @param entryFeeInfo The Entry Fee for the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setEntryFeeInfo(String entryFeeInfo) {
            mEntryFeeInfo = entryFeeInfo;
            return this;
        }

        /**
         * Setter for the Description of the Place
         *
         * @param description The Description of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setDescription(String description) {
            mDescription = description;
            return this;
        }

        /**
         * Setter for the Location Address of the Place
         *
         * @param location The Location Address of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setLocation(String location) {
            mLocation = location;
            return this;
        }

        /**
         * Setter for the Website of the Place
         *
         * @param website The Website of the Place
         * @return Instance of {@link Builder} for chaining method calls.
         */
        public Builder setWebsite(String website) {
            mWebsite = website;
            return this;
        }

        /**
         * Setter for the Boolean that indicates if a Palette Swatch was generated for the Place Photo
         *
         * @param swatchGenerated Boolean that indicates if a Palette Swatch was generated for the Place Photo
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
         * Terminal Method that constructs the {@link Place}
         *
         * @return Instance of {@link Place} built
         */
        public Place createPlace() {
            return new Place(mId, mName, mRating, mTypes, mTypeImageResId, mPhotoResId, mAccessTimeInfo,
                    mEntryFeeInfo, mDescription, mLocation, mWebsite, mSwatchGenerated,
                    mSwatchBodyTextColor, mSwatchTitleTextColor, mSwatchRgbColor);
        }
    }
}
