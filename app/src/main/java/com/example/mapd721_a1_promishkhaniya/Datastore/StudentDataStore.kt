
package com.example.mapd721_a1_promishkhaniya.Datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnotherDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("InstanceAnother")
        // Keys to uniquely identify user attributes in DataStore
        val USERNAME_Another = stringPreferencesKey("username")
        val EMAIL_Another = stringPreferencesKey("email")
        val ID_Another = stringPreferencesKey("id")
    }

    // Flow representing the another entity's stored username
    val getUsername: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // Retrieve the stored username value or return an empty string if not present
            preferences[USERNAME_Another] ?: ""
        }

    // Flow representing the another entity's stored email
    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // Retrieve the stored email value or return an empty string if not present
            preferences[EMAIL_Another] ?: ""
        }

    // Flow representing the another entity's stored ID
    val getID: Flow<String?> = context.dataStore.data
        .map { preferences: Preferences ->
            // Retrieve the stored ID value or return an 0 if not present
            preferences[ID_Another] ?: ""
        }

    // Function to save another entity's attributes in DataStore
    suspend fun infoAnotherEntity(username: String, email: String, id: String) {
        // Use the DataStore's edit function to make changes to the stored preferences
        context.dataStore.edit { preferences ->
            // Update the attributes values in the preferences
            preferences[USERNAME_Another] = username
            preferences[EMAIL_Another] = email
            preferences[ID_Another] = id
        }
    }

    // Function to clear all stored another entity data
    suspend fun removeAnotherEntityInfo() {
        context.dataStore.edit { preferences ->
            preferences.remove(USERNAME_Another)
            preferences.remove(EMAIL_Another)
            preferences.remove(ID_Another)
        }
    }
}
