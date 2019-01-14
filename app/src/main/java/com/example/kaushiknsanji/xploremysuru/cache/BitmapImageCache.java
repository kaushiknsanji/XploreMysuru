package com.example.kaushiknsanji.xploremysuru.cache;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.v4.util.LruCache;

/**
 * Class that saves the Bitmaps decoded, in a Memory Cache {@link LruCache}
 *
 * @author Kaushik N Sanji
 */
public class BitmapImageCache {
    //Constant for Cache size of the Memory Cache
    private static final int DEFAULT_CACHE_SIZE = 25 * 1024 * 1024; //25MB in bytes

    //For the Singleton instance of this
    private static volatile BitmapImageCache INSTANCE;

    //Memory Cache to save the Bitmaps decoded
    private LruCache<Integer, Bitmap> mMemoryCache;

    /**
     * Private Constructor of {@link BitmapImageCache}
     */
    private BitmapImageCache() {
        //Retrieving the current Max Memory available (in bytes)
        final int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //Calculating the safe usable Max Memory which is 1/8th of the current Max Memory available
        final int maxMemoryThreshold = maxMemory / 8;
        //Selecting the cache size based on the current availability
        final int cacheSizeSelected = DEFAULT_CACHE_SIZE > maxMemoryThreshold ? maxMemoryThreshold : DEFAULT_CACHE_SIZE;

        //Initializing the Memory Cache
        mMemoryCache = new LruCache<Integer, Bitmap>(cacheSizeSelected) {
            /**
             * Returns the size of the entry for {@code key} and {@code value} in
             * terms of bytes rather than the number of entries
             */
            @Override
            protected int sizeOf(Integer key, Bitmap bitmap) {
                //Size of the cache now returned will be the size of the entries
                //measured in bytes rather than the number of entries
                return bitmap.getByteCount();
            }

        };
    }

    /**
     * Singleton Constructor of {@link BitmapImageCache}
     *
     * @return Instance of {@link BitmapImageCache}
     */
    private static BitmapImageCache getInstance() {
        if (INSTANCE == null) {
            synchronized (BitmapImageCache.class) {
                if (INSTANCE == null) {
                    //Creating the instance when not available
                    INSTANCE = new BitmapImageCache();
                }
            }
        }
        //Using the previously created instance
        return INSTANCE;
    }

    /**
     * Method that retrieves the Bitmap Image from Memory Cache
     * for the given Image Resource Id {@code imageResId}
     *
     * @param imageResId Integer of the Image Resource whose Bitmap needs to be retrieved from Memory Cache
     * @return Bitmap of the Image for the Image Resource mentioned
     */
    public static Bitmap getBitmapFromCache(@DrawableRes Integer imageResId) {
        return getInstance().mMemoryCache.get(imageResId);
    }

    /**
     * Method that adds the Bitmap Image to Memory Cache with the Image Resource Id as the Key
     *
     * @param imageResId Integer of the Image Resource used as the Key to store in Memory Cache
     * @param bitmap     Bitmap Image decoded for the Image Resource mentioned
     */
    public static void addBitmapToCache(@DrawableRes Integer imageResId, Bitmap bitmap) {
        if (getBitmapFromCache(imageResId) == null
                && bitmap != null) {
            getInstance().mMemoryCache.put(imageResId, bitmap);
        }
    }

    /**
     * Method that clears the entire Memory Cache
     */
    public static void clearCache() {
        getInstance().mMemoryCache.evictAll();
    }
}
