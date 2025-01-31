package com.example.presentation.utils

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

suspend fun saveConfirmationStatus(context: Context, isConfirmed: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[booleanPreferencesKey("isConfirmed")] = isConfirmed
    }
}

suspend fun getConfirmationStatus(context: Context): Boolean {
    val preferences = context.dataStore.data.first()
    return preferences[booleanPreferencesKey("isConfirmed")] ?: false
}