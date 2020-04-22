package by.nrstudio.shambambukli.ui.fragment.cells

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.nrstudio.shambambukli.R
import by.nrstudio.shambambukli.adapter.CellAdapter
import by.nrstudio.shambambukli.model.Cell
import by.nrstudio.shambambukli.ui.fragment.base.BaseListFragment
import by.nrstudio.shambambukli.ui.model.base.ViewStateStore
import by.nrstudio.shambambukli.ui.model.cells.CellsViewModel
import by.nrstudio.shambambukli.ui.model.cells.CellsViewModelFactory
import by.nrstudio.shambambukli.ui.model.cells.CellsViewState
import kotlinx.android.synthetic.main.fragment_main.*

class CellsFragment : BaseListFragment<Cell>() {

    override fun getLayout(): Int = R.layout.fragment_main

    override fun initData() {
        viewModel = ViewModelProvider(this, CellsViewModelFactory).get(CellsViewModel::class.java)
        super.initData()
    }

    override fun initListeners() {
        getViewModel<CellsViewModel>()
            .getStore<ViewStateStore<CellsViewState>>()
            .observe(this) {
                showHideProgress(it.loading)

                showHideNoData(it.data.isNullOrEmpty())
                showMessageError(it.error?.message)

                setData(it.data)
            }

        btnAdd?.setOnClickListener {
            getViewModel<CellsViewModel>().addItem()
        }
        btnRemove?.setOnClickListener {
            getViewModel<CellsViewModel>().clearData()
        }
    }

    override fun showHideNoData(isEmpty: Boolean) {
        super.showHideNoData(isEmpty)
        btnRemove?.isVisible = !isEmpty
    }

    override fun initAdapter() {
        adapter = CellAdapter(getViewModel())
    }

    override fun loadData() {
        getViewModel<CellsViewModel>().loadData()
    }
}
