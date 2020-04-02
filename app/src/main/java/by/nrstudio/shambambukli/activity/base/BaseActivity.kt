package by.nrstudio.shambambukli.activity.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import by.nrstudio.shambambukli.ui.fragment.base.BaseFragment

abstract class BaseActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.fragments.forEach { nav ->
            nav.childFragmentManager.fragments.forEach {
                it?.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun reloadData() {
        supportFragmentManager.fragments.forEach { nav ->
            nav.childFragmentManager.fragments.forEach {
                if (it is BaseFragment?) {
                    it?.onReloadData()
                }
            }
        }
    }

    //////////////////////////////////// StrictMode //////////////////////////////////////////////////////

    abstract fun getLayout(): Int

}