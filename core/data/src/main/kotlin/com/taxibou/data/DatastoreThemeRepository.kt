package com.taxibou.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.taxibou.ThemeRepository
import com.taxibou.UiMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DatastoreThemeRepository(private val context: Context) : ThemeRepository {
    override val uiModeFlow: Flow<UiMode> =
        context.preferenceDatastore.data.catch { emit(emptyPreferences()) }.map {
            it[UI_MODE_KEY]?.let { name ->
                runCatching { UiMode.valueOf(name) }.getOrDefault(UiMode.System)
            } ?: UiMode.System
        }

    override suspend fun updateUiMode(mode: UiMode) {
        context.preferenceDatastore.edit {
            it[UI_MODE_KEY] = mode.name
        }
    }

    companion object {
        val UI_MODE_KEY = stringPreferencesKey("ui_mode")
    }
}
