package by.nrstudio.shambambukli.activity

import android.os.Bundle
import androidx.navigation.findNavController
import by.nrstudio.shambambukli.R
import by.nrstudio.shambambukli.activity.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_base_host_fragment).navigateUp()
}
