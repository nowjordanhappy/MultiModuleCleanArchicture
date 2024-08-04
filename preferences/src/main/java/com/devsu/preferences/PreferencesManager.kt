package com.devsu.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


data class PreferencesManager (
    private val dataStore: DataStore<Preferences>
){
    companion object{
        const val DATA = "Data"
    }

    private object PreferencesKeys {
        val USER_ID = intPreferencesKey("user_id")
    }

    suspend fun setUserId(userId: Int) {
        dataStore.edit { preference ->
            preference[PreferencesKeys.USER_ID] = userId
        }
    }

    fun getUserId(): Flow<Int?> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[PreferencesKeys.USER_ID]
        }
    }
}