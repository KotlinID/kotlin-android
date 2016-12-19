package com.baculsoft.kotlin.android.extensions

import rx.Subscription

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
fun Subscription?.safeUnsubscribe() {
    if (null != this && !this.isUnsubscribed) {
        this.unsubscribe()
    }
}