package com.core.dto

import com.core.R


enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState(
    val status: Status,
    val tag: String? = null,
    val event: Int   = R.string.forbidden,
    val msg: String? = null
) {

    companion object {
        fun loaded(tag: String? = null) = NetworkState(Status.SUCCESS, tag = tag)
        fun loading(tag: String? = null) = NetworkState(Status.RUNNING, tag = tag)
        public fun error(event: Int = R.string.forbidden, tag: String? = null, msg: String? = null) =
            NetworkState(status = Status.FAILED, event = event, msg = msg, tag = tag)
    }
}