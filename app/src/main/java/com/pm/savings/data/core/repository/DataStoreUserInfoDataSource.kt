package com.pm.savings.data.core.repository

import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pm.savings.domain.core.repository.UserInfoDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DataStoreUserInfoDataSource(
    private val userPrefs: DataStore<Preferences>
) : UserInfoDataSource {

    override fun getUserName(): Flow<String> {
        return userPrefs.data.map { prefs ->
            prefs[USER_NAME_KEY] ?: "my_fav_user"
        }
    }

    override suspend fun saveUserName(userName: String) {
        userPrefs.edit { prefs ->
            prefs[USER_NAME_KEY] = userName
        }
    }

    override fun getMainCurrency(): Flow<String> {
        return userPrefs.data.map { prefs ->
            prefs[MAIN_CURRENCY_KEY] ?: "PLN"
        }
    }

    override suspend fun updateMainCurrency(currency: String) {
        userPrefs.edit { prefs ->
            prefs[MAIN_CURRENCY_KEY] = currency
        }
    }

    override fun getCurrencies(): Flow<List<String>> = flow {
        emit(listOf("PLN", "EUR", "USD"))
    }

    override fun getProfilePictureUri(): Flow<Uri?> {
        return userPrefs.data.map { prefs ->
            try {
                val stringUri = prefs[AVATAR_URI_KEY]
                Uri.parse(stringUri)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun setProfilePictureUri(uri: Uri) {
        userPrefs.edit { prefs ->
            prefs[AVATAR_URI_KEY] = uri.toString()
        }
    }

    private companion object {
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val MAIN_CURRENCY_KEY = stringPreferencesKey("main_currency")
        val AVATAR_URI_KEY = stringPreferencesKey("avatar_uri")
    }
}