package com.alexandrpershin.spacexlaunches.ui.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alexandrpershin.spacexlaunches.R
import com.alexandrpershin.spacexlaunches.ui.state.ScreenState
import com.alexandrpershin.spacexlaunches.utils.makeGone
import com.alexandrpershin.spacexlaunches.utils.makeVisible
import com.google.android.material.snackbar.Snackbar

/**
 * Base fragment for instances of [Fragment] class in this project
 * */

abstract class BaseFragment : Fragment() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    private var progressBar: ProgressBar? = null
    private var container: View? = null
    protected lateinit var activity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity) {
            activity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = requireActivity().findViewById(R.id.progressBar)
        container = requireActivity().findViewById(R.id.container)

        setUpBackArrow()
    }

    private fun setUpBackArrow() {
        if (activity.supportFragmentManager.backStackEntryCount > 1) {
            showBackArrow()
        } else {
            hideBackArrow()
        }
    }

    private fun showBackArrow() {
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    private fun hideBackArrow() {
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.setDefaultDisplayHomeAsUpEnabled(false)
    }

    protected fun setTitle(resId: Int) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(resId)
    }

    fun showSnackBar(message: String) {
        val view = requireActivity().findViewById<View>(android.R.id.content)
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackBar.show()
    }

    fun handleError(errorStatus: ScreenState.Error) {
        val errorMessage = errorStatus.throwable?.message ?: getString(errorStatus.resId)
        showSnackBar(errorMessage)
    }

    fun hideProgressBar() {
        container?.alpha = 1.0f
        progressBar?.makeGone()
    }

    fun showProgressBar() {
        container?.alpha = 0.5f
        progressBar?.makeVisible()
    }

}
