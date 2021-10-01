package ba.grbo.core.domain

interface Logger {
    fun init()

    fun d(message: String)

    fun e(throwable: Throwable)
}