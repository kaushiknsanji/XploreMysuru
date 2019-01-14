package com.example.kaushiknsanji.xploremysuru.utils;

import android.content.Context;

import com.example.kaushiknsanji.xploremysuru.data.AppRepository;
import com.example.kaushiknsanji.xploremysuru.data.local.ResourceRepository;

/**
 * Utility class that injects required dependencies into the Model-View-Presenter framework.
 *
 * @author Kaushik N Sanji
 */
public class InjectorUtility {

    /**
     * Private Constructor to avoid direct instantiation of {@link InjectorUtility}
     */
    private InjectorUtility() {
        //Suppressing with an error to enforce noninstantiability
        throw new AssertionError("No " + this.getClass().getCanonicalName() + " instances for you!");
    }

    /**
     * Method that provides/injects the {@link ResourceRepository} instance which
     * deals with the {@link android.content.res.Resources}
     *
     * @param context A {@link Context} to derive the {@link android.content.res.Resources} instance
     * @return Instance of {@link ResourceRepository}
     */
    private static ResourceRepository provideResourceRepository(Context context) {
        return ResourceRepository.getInstance(context.getPackageName(), context.getResources(), AppExecutors.getInstance());
    }

    /**
     * Method that provides/injects the {@link AppRepository} instance which
     * interfaces with {@link ResourceRepository}
     *
     * @param context A {@link Context} to derive the {@link android.content.res.Resources} instance
     * @return Instance of {@link AppRepository}
     */
    public static AppRepository provideAppRepository(Context context) {
        return AppRepository.getInstance(provideResourceRepository(context));
    }
}
