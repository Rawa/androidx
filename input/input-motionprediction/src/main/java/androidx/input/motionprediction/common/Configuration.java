/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.input.motionprediction.common;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/**
 */
@RestrictTo(LIBRARY)
public class Configuration {
    private static volatile Configuration sInstance = null;
    private static final Object sLock = new Object();

    private boolean mPreferSystemPrediction;
    private int mPredictionOffset;

    /**
     * Returns the configuration for prediction in this system.
     *
     * @return the prediction configuration
     */
    public static @NonNull Configuration getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new Configuration();
                }
            }
        }
        return sInstance;
    }

    private Configuration() {
        // This class is non-instantiable externally.
        mPreferSystemPrediction = SystemProperty
                .getBoolean("debug.input.androidx_prefer_system_prediction");
        mPredictionOffset = SystemProperty.getInt("debug.input.androidx_prediction_offset");
    }

    /**
     * Returns whether or not the library should prefer the system prediction when available
     *
     * @return true if the system prediction should be used when available
     */
    public boolean preferSystemPrediction() {
        return mPreferSystemPrediction;
    }

    /**
     * Returns the number of milliseconds to add to the computed prediction target
     *
     * @return number of additional milliseconds to predict
     */
    public int predictionOffset() {
        return mPredictionOffset;
    }
}
