package com.vvz.pure.test.domain.models

sealed class PureError(cause: Throwable? = null) : Throwable(cause = cause) {

    sealed class Network(cause: Throwable? = null) : PureError(cause = cause) {
        object Connection : Network()
        class Coding(cause: Throwable?) : Network(cause = cause)
    }

    class Storage(cause: Throwable? = null) : PureError(cause = cause)

    class Unknown(cause: Throwable) : PureError(cause = cause)

}
