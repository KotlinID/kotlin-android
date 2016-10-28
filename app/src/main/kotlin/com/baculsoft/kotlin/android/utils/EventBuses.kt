package com.baculsoft.kotlin.android.utils

import org.greenrobot.eventbus.EventBus

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class EventBuses private constructor() {

    companion object {
        @Volatile private var INSTANCE: EventBuses? = null

        @Synchronized fun get(): EventBuses {
            if (INSTANCE == null) {
                INSTANCE = EventBuses()
            }

            return INSTANCE as EventBuses
        }
    }

    private val mEventBus: EventBus

    init {
        mEventBus = EventBus.getDefault()
    }

    fun register(`object`: Any) {
        mEventBus.register(`object`)
    }

    fun unregister(`object`: Any) {
        mEventBus.unregister(`object`)
    }

    fun post(`object`: Any) {
        mEventBus.post(`object`)
    }
}