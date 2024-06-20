package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserEmail(private val context: Context) { // el contexto nos permite entrar a
// recursos del sistema operativo
    companion object {
        // caracterica que nos permite definir miembros dentro de una clase que se pueden acceder
        // sin la necesidad de crear una instancia de la clase
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_EMAIL = stringPreferencesKey("user_email") // id del registro
    }

    val getEmail: Flow<String> = context.dataStore.data // opcional pq puede que este vacio
        .map { preferences ->
            preferences[USER_EMAIL] ?: ""
        }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }
}