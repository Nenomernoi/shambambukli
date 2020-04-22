package by.nrstudio.shambambukli

import android.app.Application
import by.nrstudio.shambambukli.db.Db
import by.nrstudio.shambambukli.util.SettingPrefs
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App : Application() {

    private val settingModule = Kodein.Module(name = "APP") {
        bind<SettingPrefs>() with singleton { SettingPrefs.get(this@App) }
        bind<Db>() with singleton { Db.getInstance(this@App) }
    }

    companion object {
        lateinit var kodein: Kodein
    }

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {
            import(settingModule)
        }
    }
}
