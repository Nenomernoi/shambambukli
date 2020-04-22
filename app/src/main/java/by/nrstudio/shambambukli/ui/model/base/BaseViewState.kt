package by.nrstudio.shambambukli.ui.model.base

abstract class BaseViewState<T> {
    abstract val data: T?
    abstract val loading: Boolean
    abstract val error: Throwable?
}

abstract class BaseListState<T>(
    override val data: MutableList<T> = mutableListOf(),
    override val loading: Boolean = false,
    override val error: Throwable? = null
) : BaseViewState<MutableList<T>>()
