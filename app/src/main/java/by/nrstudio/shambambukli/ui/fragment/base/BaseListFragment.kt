package by.nrstudio.shambambukli.ui.fragment.base

import androidx.recyclerview.widget.LinearLayoutManager
import by.nrstudio.shambambukli.adapter.base.BaseSupportAdapter
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseListFragment<T : Any> : BaseFragment() {

    protected var adapter: BaseSupportAdapter<T>? = null

    override fun initData() {
        initRclView()
    }

    protected open fun initRclView() {
        rvMain?.layoutManager = LinearLayoutManager(activity)
    }

    //////////////////////////////////////////////////////////////////////////////////////

    protected open fun setData(data: MutableList<T>) {
        if (rvMain?.adapter == null) {
            baseInitAdapter()
        } else {
            setItems(data)
        }
        rvMain?.scrollToPosition(data.size - 1)
    }

    protected open fun baseInitAdapter() {
        initAdapter()
        rvMain?.adapter = adapter
    }

    protected open fun setItems(data: MutableList<T>) {
        adapter?.list = data
    }

    protected open fun addItems(data: MutableList<T>) {
        adapter?.list?.addAll(data)
    }

    ///////////////////////////////////////////////////////////////////////////////////

    protected open fun clearItems() {
        adapter?.list?.clear()
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
        rvMain?.layoutManager = null
    }

    //////////////////////////////////////////////////////////////////////////////////////

    protected abstract fun loadData()
    protected abstract fun initAdapter()
}