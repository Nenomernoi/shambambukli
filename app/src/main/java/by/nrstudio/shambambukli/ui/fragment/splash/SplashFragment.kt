package by.nrstudio.shambambukli.ui.fragment.splash

import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import by.nrstudio.shambambukli.R
import by.nrstudio.shambambukli.ui.fragment.base.BaseFragment
import by.nrstudio.shambambukli.ui.model.base.ViewStateStore
import by.nrstudio.shambambukli.ui.model.splash.SplashViewModel
import by.nrstudio.shambambukli.ui.model.splash.SplashViewModelFactory
import by.nrstudio.shambambukli.ui.model.splash.SplashViewState
import by.nrstudio.shambambukli.util.navigateSplash
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.fragment_splash

    override fun initData() {
        viewModel = ViewModelProvider(this, SplashViewModelFactory).get(SplashViewModel::class.java)
    }

    override fun initListeners() {
        super.initListeners()

        getViewModel<SplashViewModel>()
            .getStore<ViewStateStore<SplashViewState>>()
            .observe(this) {

                it.message?.let { text ->
                    showMessage(text)
                }

                if (it.startAnim) {
                    startAnim()
                } else {
                    openNext()
                }
            }
    }

    private fun showMessage(message: String) {
        if (message.isEmpty()) {
            return
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun startAnim() {
        imgLogo?.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.splash_anim))
        txtTitle?.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.splash_anim))
    }

    private fun openNext() {
        navigateSplash(
            R.id.action_splashFragment_to_mainFragment
        )
    }

}
