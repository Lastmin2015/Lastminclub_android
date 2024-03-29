/*
 * Copyright (C) 2019 The Android Open Source Project
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

package com.google.android.material.internal;

import com.google.android.material.R;

import android.os.Parcel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.internal.DoNotInstrument;

/** test for {@link ParcelableSparseIntArray} */
@RunWith(RobolectricTestRunner.class)
@DoNotInstrument
public class ParcelableSparseIntArrayTest {

  private static final int TEST_KEY = 4;
  private static final int TEST_VAL = 88;

  @Test
  public void testArray() {
    ParcelableSparseIntArray array = new ParcelableSparseIntArray();
    array.append(TEST_KEY, TEST_VAL);

    Parcel parcel = Parcel.obtain();
    array.writeToParcel(parcel, array.describeContents());
    parcel.setDataPosition(0);

    ParcelableSparseIntArray createdFromParcel =
        ParcelableSparseIntArray.CREATOR.createFromParcel(parcel);
    int val = createdFromParcel.get(TEST_KEY);
    Assert.assertEquals(TEST_VAL, val);
  }
}
