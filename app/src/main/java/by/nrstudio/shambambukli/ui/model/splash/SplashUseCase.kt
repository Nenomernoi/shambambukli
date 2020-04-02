package  by.nrstudio.shambambukli.ui.model.splash

import by.nrstudio.shambambukli.ui.model.base.Action
import by.nrstudio.shambambukli.ui.model.base.BaseUseCase
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay

class SplashUseCase : BaseUseCase() {

    fun startWork(): ReceiveChannel<Action<SplashViewState>> = produceActions {
        send { copy(startAnim = true) }
        delay(2500L)
        send { copy(startAnim = false) }
    }

}
