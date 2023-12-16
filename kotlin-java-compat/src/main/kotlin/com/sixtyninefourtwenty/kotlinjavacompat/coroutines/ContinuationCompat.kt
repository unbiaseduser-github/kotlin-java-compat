package com.sixtyninefourtwenty.kotlinjavacompat.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.internal.resumeCancellableWith
import java.util.function.Consumer
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Suppress("unused")
class ContinuationCompat<in T> @JvmOverloads constructor(
    private val coroutineContext: CoroutineContext,
    private val onSuccess: Consumer<in T>? = null,
    private val onFailure: Consumer<in Exception>? = null
) : Continuation<T> {

    @JvmOverloads constructor(
        scope: CoroutineScope,
        onSuccess: Consumer<in T>? = null,
        onFailure: Consumer<in Exception>? = null
    ) : this(
        scope.coroutineContext,
        onSuccess,
        onFailure
    )

    override val context: CoroutineContext
        get() = coroutineContext

    override fun resumeWith(result: Result<T>) {
        result.onSuccess {
            onSuccess?.accept(it)
        }.onFailure {
            if (it is Exception && onFailure != null) {
                onFailure.accept(it)
            } else throw it
        }
    }

    fun resumes(value: T) {
        resume(value)
    }

    fun resumesWithException(exception: Throwable) {
        resumeWithException(exception)
    }

}