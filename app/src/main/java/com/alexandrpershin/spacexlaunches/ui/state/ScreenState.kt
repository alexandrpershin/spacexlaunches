package com.alexandrpershin.spacexlaunches.ui.state

import com.alexandrpershin.spacexlaunches.R


sealed class ScreenState {
    object EmptyResult : ScreenState()
    object Loading : ScreenState()
    object Success : ScreenState()
    object InternetError : ScreenState()
    class Error(val throwable: Throwable? = null, val resId: Int = R.string.error_message_unknown_error) : ScreenState()
}