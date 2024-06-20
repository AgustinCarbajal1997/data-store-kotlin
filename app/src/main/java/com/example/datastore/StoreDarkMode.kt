package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDarkMode(private val context: Context) { // el contexto nos permite entrar a
    // recursos del sistema operativo
    companion object {
        // caracterica que nos permite definir miembros dentro de una clase que se pueden acceder
        // sin la necesidad de crear una instancia de la clase
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("DarkMode")
        val DARK_MODE = booleanPreferencesKey("dark_mode") // id del registro
    }

    val getDarkMode: Flow<Boolean> = context.dataStore.data // opcional pq puede que este vacio
        .map { preferences ->
            preferences[DARK_MODE] ?: false
        }

    suspend fun saveDarkMode(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE] = value
        }
    }
}