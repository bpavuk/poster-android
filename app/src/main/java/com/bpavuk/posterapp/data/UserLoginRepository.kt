package com.bpavuk.posterapp.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserLoginRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        private val USER_NAME = stringPreferencesKey("user_name")
        private val PASSWORD = stringPreferencesKey("password")
        private const val TAG = "UserLoginRepo"
    }

    val userName: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error with DataStore", it)
            } else throw it
        }
        .map { preferences ->
            preferences[USER_NAME] ?: ""
        }

    val password: Flow<String> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error with DataStore", it)
            } else throw it
        }
        .map { preferences ->
            preferences[PASSWORD] ?: ""
        }

    suspend fun editUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
        }
    }

    suspend fun editPassword(password: String) {
        dataStore.edit { preferences ->
            preferences[PASSWORD] = password
        }
    }
}