package by.nrstudio.shambambukli.util

import co.windly.ktxprefs.annotation.DefaultString
import co.windly.ktxprefs.annotation.Prefs

@Prefs(value = "CachePreferences")
class Setting(
    @DefaultString(value = "")
    internal val token: String

)
