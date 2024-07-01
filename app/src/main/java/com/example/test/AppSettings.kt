import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

object AppSettings {
    private const val KEY_URL = "url"
    private const val KEY_NICKNAME = "nickname"
    private const val KEY_PASSWORD = "password"

    fun saveCredentials(context: Context, url: String, nickname: String, password: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit {
            putString(KEY_URL, url)
            putString(KEY_NICKNAME, nickname)
            putString(KEY_PASSWORD, password)
        }
    }

    fun getUrl(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_URL, null)
    }

    fun getNickname(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_NICKNAME, null)
    }

    fun getPassword(context: Context): String? {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_PASSWORD, null)
    }
}
