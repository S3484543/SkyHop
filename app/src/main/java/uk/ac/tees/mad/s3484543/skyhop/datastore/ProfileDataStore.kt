package uk.ac.tees.mad.s3484543.skyhop.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "profile_prefs")

class ProfileDataStore(private val context: Context) {

    companion object {
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val PHOTO = stringPreferencesKey("photo_base64")
    }

    suspend fun saveProfile(name: String, email: String, photoBase64: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME] = name
            prefs[EMAIL] = email
            prefs[PHOTO] = photoBase64
        }
    }

    val profileFlow: Flow<ProfileData> = context.dataStore.data.map { prefs ->
        ProfileData(
            name = prefs[NAME] ?: "",
            email = prefs[EMAIL] ?: "",
            photoBase64 = prefs[PHOTO] ?: ""
        )
    }
}

data class ProfileData(
    val name: String,
    val email: String,
    val photoBase64: String
)
