import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

object AppSettings {
    private const val KEY_URL = "url"
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"

    fun saveCredentials(context: Context, url: String, username: String, password: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putString(KEY_URL, url)
            putString(KEY_USERNAME, username)
            putString(KEY_PASSWORD, password)
        }
    }

    fun getUrl(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_URL, null)
    }

    fun getNickname(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_USERNAME, null)
    }

    fun getPassword(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_PASSWORD, null)
    }
}
