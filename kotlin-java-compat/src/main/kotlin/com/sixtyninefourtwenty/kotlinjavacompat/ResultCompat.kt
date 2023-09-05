package com.sixtyninefourtwenty.kotlinjavacompat

import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import kotlin.coroutines.Continuation

class ResultCompat<out T> private constructor(private val result: Result<T>) {

    fun getOrThrow() = result.getOrThrow()

    fun <R> map(transform: Function<in T, out R>) = ResultCompat(result.map(transform::apply))

    fun <R> mapCatching(transform: Function<in T, out R>) = ResultCompat(result.mapCatching(transform::apply))

    fun onSuccess(action: Consumer<in T>) = apply {
        result.onSuccess(action::accept)
    }

    fun onFailure(action: Consumer<in Throwable>) = apply {
        result.onFailure(action::accept)
    }

    fun resume(continuation: Continuation<T>) = continuation.resumeWith(result)

    companion object {
        @JvmStatic
        fun <T> success(value: T) = ResultCompat(Result.success(value))
        @JvmStatic
        fun <T> failure(exception: Throwable) = ResultCompat(Result.failure<T>(exception))
        @JvmStatic
        fun <R> runCatching(block: Supplier<out R>) = ResultCompat(kotlin.runCatching(block::get))
        @JvmStatic
        fun <T, R> runCatching(input: T, block: Function<in T, out R>) = ResultCompat(input.runCatching(block::apply))
        @JvmStatic
        fun <R, T : R> getOrElse(existing: ResultCompat<T>, onFailure: Function<in Throwable, out R>) = existing.result.getOrElse(onFailure::apply)
        @JvmStatic
        fun <R, T : R> getOrDefault(existing: ResultCompat<T>, defaultValue: R) = existing.result.getOrDefault(defaultValue)
        @JvmStatic
        fun <R, T> fold(existing: ResultCompat<T>, onSuccess: Function<in T, out R>,
                        onFailure: Function<in Throwable, out R>) = existing.result.fold(onSuccess::apply, onFailure::apply)
        @JvmStatic
        fun <R, T : R> recover(existing: ResultCompat<T>, transform: Function<in Throwable, out R>) = ResultCompat(existing.result.recover(transform::apply))
        @JvmStatic
        fun <R, T : R> recoverCatching(existing: ResultCompat<T>, transform: Function<in Throwable, out R>) = ResultCompat(existing.result.recoverCatching(transform::apply))
    }

}