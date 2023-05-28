package com.pm.savings.domain.core.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface UserInfoDataSource {
    fun getUserName(): Flow<String>
    suspend fun saveUserName(userName: String)

    fun getMainCurrency(): Flow<String>
    suspend fun updateMainCurrency(currency: String)

    fun getCurrencies(): Flow<List<String>>

    fun getProfilePictureUri(): Flow<Uri?>
    suspend fun setProfilePictureUri(uri: Uri)
}