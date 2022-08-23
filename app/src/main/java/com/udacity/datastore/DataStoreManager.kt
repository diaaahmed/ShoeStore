package com.udacity.datastore

import android.content.Context
import androidx.datastore.preferences.clear
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context:Context) {

    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object{
        val USER_NAME_EMAIL = preferencesKey<String>("USER_NAME_EMAIL")
        val USER_NAME_PASS = preferencesKey<String>("USER_NAME_PASS")
    }

    suspend fun storeUserInfo(email:String, pass:String)
    {
        dataStore.edit {
            it[USER_NAME_EMAIL] = email
            it[USER_NAME_PASS] = pass
        }
    }

    suspend fun clearDataStore()
    {
        dataStore.edit { it.clear() }
    }

    val user_email:Flow<String> = dataStore.data.map {

        it[USER_NAME_EMAIL] ?: ""
    }

    val user_pass: Flow<String> = dataStore.data.map {
        it[USER_NAME_PASS] ?: ""
    }
}