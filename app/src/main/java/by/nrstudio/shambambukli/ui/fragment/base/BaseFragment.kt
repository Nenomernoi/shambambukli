package by.nrstudio.shambambukli.ui.fragment.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.nrstudio.shambambukli.ui.model.base.BaseViewModel
import by.nrstudio.shambambukli.ui.model.base.ViewStateStore
import kotlinx.android.synthetic.main.fragment_main.*

abstract class BaseFragment : Fragment() {

    protected open lateinit var viewModel: BaseViewModel
    protected lateinit var origin: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(getLayout(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListeners()
        onRestore(savedInstanceState, arguments)
    }

    protected open fun onRestore(savedInstanceState: Bundle?, arguments: Bundle?) {
        //
    }

    // ////////////////////////////////////////////////////////////////////////////////////////

    override fun onDestroy() {
        super.onDestroy()
        if (::viewModel.isInitialized) {
            val st = getViewModel<BaseViewModel>().getStore<ViewStateStore<*>>()
            st.unsubscribe(this)
            st.cancel()
        }
    }

    // ////////////////////////////////////////////////////////////////////////////////////////

    protected open fun showHideProgress(isShowHide: Boolean) {
        activity?.runOnUiThread {
            pbLoad?.isVisible = isShowHide
        }
    }

    open fun showHideNoData(isEmpty: Boolean) {
        txtNoData?.post {
            txtNoData?.isVisible = isEmpty
        }
    }

    // ////////////////////////////////////////////////////////////////////////////////////////

    open fun showMessageError(message: String?, logOrToast: Boolean? = false) {
        if (message.isNullOrEmpty()) {
            return
        }

        if (logOrToast == true) {
            Log.e(javaClass.simpleName, message)
            return
        }

        activity?.runOnUiThread {
            Toast.makeText(activity ?: return@runOnUiThread, message, Toast.LENGTH_SHORT).show()
        }
    }

    // ////////////////////////////////////////////////////////////////////////////////////////

    open fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        //
    }

    // ////////////////////////////////////////////////////////////////////////////////////////

    protected open fun initListeners() {
        //
    }

    open fun onReloadData() {
        //
    }

    abstract fun getLayout(): Int
    abstract fun initData()

    fun <V : Any> getViewModel(): V = viewModel as V
}
