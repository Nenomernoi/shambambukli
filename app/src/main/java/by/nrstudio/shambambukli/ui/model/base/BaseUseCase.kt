package by.nrstudio.shambambukli.ui.model.base

import by.nrstudio.shambambukli.App
import by.nrstudio.shambambukli.db.Db
import by.nrstudio.shambambukli.util.SettingPrefs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import org.kodein.di.generic.instance

abstract class BaseUseCase {

    fun <T> produceActions(f: suspend ProducerScope<Action<T>>.() -> Unit): ReceiveChannel<Action<T>> = GlobalScope.produce(block = f)

    suspend fun <T> ProducerScope<Action<T>>.send(f: T.() -> T) = send(Action(f))
}

abstract class BaseDbUseCase : BaseUseCase() {

    protected val setting: SettingPrefs by App.kodein.instance<SettingPrefs>()
    protected val db: Db by App.kodein.instance<Db>()
}
