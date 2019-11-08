package com.alexandrpershin.spacexlaunches.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexandrpershin.spacexlaunches.R
import com.alexandrpershin.spacexlaunches.di.Injector
import com.alexandrpershin.spacexlaunches.ui.fragment.RocketLaunchesFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModel: RocketLaunchesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Injector.instance.initMainActivityComponent(this)

        showRocketLaunchesFragment()
    }

    private fun showRocketLaunchesFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, RocketLaunchesFragment())
            .addToBackStack(RocketLaunchesFragment.TAG)
            .commit()
    }

    override fun onBackPressed() {
        when (supportFragmentManager.backStackEntryCount) {
            1 -> finish()
            else -> supportFragmentManager.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.instance.releaseMainActivityComponent()
    }
}
