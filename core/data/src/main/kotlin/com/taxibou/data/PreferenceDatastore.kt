package com.taxibou.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore


val Context.preferenceDatastore by preferencesDataStore("preferences")