package by.nrstudio.shambambukli.ui.model.cells

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.nrstudio.shambambukli.model.Cell
import by.nrstudio.shambambukli.ui.model.base.BaseListState
import by.nrstudio.shambambukli.ui.model.base.BaseViewModel
import by.nrstudio.shambambukli.ui.model.base.ViewStateStore

data class CellsViewState(
    override val data: MutableList<Cell> = mutableListOf(),
    override val loading: Boolean = false,
    override val error: Throwable? = null
) : BaseListState<Cell>()

class CellsViewModel(private val useCase: CellsUseCase) : BaseViewModel() {

    init {
        store = ViewStateStore(CellsViewState())
        loadData()
    }

    override fun loadData() {
        getStore<ViewStateStore<CellsViewState>>().dispatchActions(useCase.loadData())
    }

    fun clearData() {
        getStore<ViewStateStore<CellsViewState>>().dispatchActions(useCase.clearData())
    }

    fun addItem() {
        getStore<ViewStateStore<CellsViewState>>().dispatchActions(useCase.addItem(getState()))
    }

    override fun getState() = getStore<ViewStateStore<CellsViewState>>().state()

}

object CellsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CellsViewModel(CellsUseCase()) as T
}

