package by.nrstudio.shambambukli.ui.model.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.nrstudio.shambambukli.ui.model.base.BaseViewModel
import by.nrstudio.shambambukli.ui.model.base.ViewStateStore

data class SplashViewState(
    val startAnim: Boolean = true,
    val message: String? = null
)

class SplashViewModel(private val useCase: SplashUseCase) : BaseViewModel() {

    init {
        store = ViewStateStore(SplashViewState())
        loadData()
    }

    override fun loadData() {
        getStore<ViewStateStore<SplashViewState>>().dispatchActions(useCase.startWork())
    }

    override fun getState() = getStore<ViewStateStore<SplashViewState>>().state()
}

object SplashViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SplashViewModel(SplashUseCase()) as T
}
