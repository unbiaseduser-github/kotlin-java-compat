@file:Suppress("unused")
package com.sixtyninefourtwenty.kotlinjavacompat

import com.sixtyninefourtwenty.kotlinjavacompat.Function0Compat.Companion.shorten
import com.sixtyninefourtwenty.kotlinjavacompat.Function1Compat.Companion.shorten
import kotlin.jvm.Throws

/**
 * A functional interface that can be used in place of Kotlin's `() -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function0Compat : () -> Unit {
    override fun invoke() {
        invokeCustom()
    }

    fun invokeCustom()

    companion object {
        /**
         * Convenience method that allows to avoid casting your lambda expression.
         *
         * Usage: Just replace occurrences of
         *
         * ```
         * method((Function0Compat) () -> doStuff());
         * ```
         *
         * with
         *
         * ```
         * // shorten() is static imported
         * method(shorten(() -> doStuff()));
         * ```
         */
        @JvmStatic
        fun shorten(block: Function0Compat): () -> Unit = block

        /**
         * Same as [shorten], but allows the [block] to propagate exceptions.
         */
        @JvmStatic
        fun shortenThrowing(block: ThrowingFunction0Compat): () -> Unit = block
    }
}

fun interface ThrowingFunction0Compat : Function0Compat {

    override fun invokeCustom() {
        invokeCustomThrowing()
    }

    @Throws(Throwable::class)
    fun invokeCustomThrowing()

}

/**
 * A functional interface that can be used in place of Kotlin's `(T) -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function1Compat<in T> : Function1<T, Unit> {
    override fun invoke(t: T) {
        invokeCustom(t)
    }

    fun invokeCustom(t: T)

    companion object {
        /**
         * Convenience method that allows to avoid casting your lambda expression.
         *
         * Usage: Just replace occurrences of
         *
         * ```
         * method((Function1Compat<MyClass>) myObj -> doStuff(myObj));
         * ```
         *
         * with
         *
         * ```
         * // shorten() is static imported
         * method(shorten(myObj -> doStuff(myObj)));
         * ```
         */
        @JvmStatic
        fun <T> shorten(block: Function1Compat<T>): (T) -> Unit = block

        /**
         * Same as [shorten], but allows the [block] to propagate exceptions.
         */
        @JvmStatic
        fun <T> shortenThrowing(block: ThrowingFunction1Compat<T>): (T) -> Unit = block
    }
}

fun interface ThrowingFunction1Compat<in T> : Function1Compat<T> {

    override fun invokeCustom(t: T) {
        invokeCustomThrowing(t)
    }

    @Throws(Throwable::class)
    fun invokeCustomThrowing(t: T)

}

/**
 * A functional interface that can be used in place of Kotlin's `(T, U) -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function2Compat<in T, in U> : Function2<T, U, Unit> {
    override fun invoke(t: T, u: U) {
        invokeCustom(t, u)
    }

    fun invokeCustom(t: T, u: U)

    companion object {
        /**
         * Convenience method that allows to avoid casting your lambda expression.
         *
         * Usage: Just replace occurrences of
         *
         * ```
         * method((Function2Compat<MyClassOne, MyClassTwo>) (myObjOne, myObjTwo) -> doStuff(myObjOne, myObjTwo));
         * ```
         *
         * with
         *
         * ```
         * // shorten() is static imported
         * method(shorten((myObjOne, myObjTwo) -> doStuff(myObjOne, myObjTwo)));
         * ```
         */
        @JvmStatic
        fun <T, U> shorten(block: Function2Compat<T, U>): (T, U) -> Unit = block

        /**
         * Same as [shorten], but allows the [block] to propagate exceptions.
         */
        @JvmStatic
        fun <T, U> shortenThrowing(block: ThrowingFunction2Compat<T, U>): (T, U) -> Unit = block
    }
}

fun interface ThrowingFunction2Compat<in T, in U> : Function2Compat<T, U> {

    override fun invokeCustom(t: T, u: U) {
        invokeCustomThrowing(t, u)
    }

    @Throws(Throwable::class)
    fun invokeCustomThrowing(t: T, u: U)

}
