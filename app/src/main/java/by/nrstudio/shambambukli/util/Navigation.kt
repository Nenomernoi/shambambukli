package by.nrstudio.shambambukli.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import by.nrstudio.shambambukli.R

// //////////////////////////////////////////////////////////////////////////////////////////////////

fun NavOptions.Builder.setAnim(withAnim: Boolean? = true): NavOptions.Builder {
    return this.setEnterAnim(if (withAnim == true) R.anim.slide_out_right else R.anim.empty_anim)
        .setExitAnim(if (withAnim == true) R.anim.slide_in_right_slow else R.anim.empty_anim)
        .setPopEnterAnim(if (withAnim == true) R.anim.slide_out_left_fast else R.anim.empty_anim)
        .setPopExitAnim(if (withAnim == true) R.anim.slide_in_left else R.anim.empty_anim)
}

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fun Fragment.navigateSplash(
    actionId: Int,
    popup: Int = R.id.navigation_splash,
    bundle: Bundle? = null
) {
    this.findNavController().navigate(
        actionId, bundle,
        NavOptions.Builder()
            .setPopUpTo(
                popup,
                true
            ).setAnim()
            .build()
    )
}
