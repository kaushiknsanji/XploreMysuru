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
