package ru.vsibi.presentation.screens.search.main

import java.util.*

internal class TimeSourceCopy private constructor(private val fixedTimeMs: Long?, private val fixedTimeZone: TimeZone?) {
    /**
     * Returns a `Calendar` according to this time source in the specified timezone.
     *
     * @param timeZone the timezone to use to convert the date-time. If this value is null, the host
     * device's timezone will be used.
     */
    /** Returns a `Calendar` according to this time source.  */
    @JvmOverloads
    fun now(timeZone: TimeZone? = fixedTimeZone): Calendar {
        val calendar = if (timeZone == null) Calendar.getInstance() else Calendar.getInstance(timeZone)
        if (fixedTimeMs != null) {
            calendar.timeInMillis = fixedTimeMs
        }
        return calendar
    }

    companion object {
        private val SYSTEM_TIME_SOURCE = TimeSourceCopy(null, null)

        /**
         * A time source that returns the current time using the best available system clock.
         *
         *
         * For testability, rather than calling this method directly, most classes should have an
         * instance of `TimeSource` *provided* to them, for example by dependency injection.
         */
        fun system(): TimeSourceCopy {
            return SYSTEM_TIME_SOURCE
        }

        /**
         * Obtains a `TimeSource` that always returns the same time in the specified timezone.
         *
         *
         * This clock simply returns the specified instant. As such, it is not a clock in the
         * conventional sense. The main use case for this is in testing, where the fixed clock ensures
         * tests are not dependent on the current clock.
         *
         *
         * The returned implementation is immutable, thread-safe and `Serializable`.
         *
         * @param epochMs the time in UTC milliseconds from the epoch.
         * @param timeZone the timezone to use to convert the date-time. If this value is null, the host
         * device's timezone will be used.
         */
        fun fixed(epochMs: Long, timeZone: TimeZone?): TimeSourceCopy {
            return TimeSourceCopy(epochMs, timeZone)
        }

        /**
         * Obtains a `TimeSource` that always returns the same time in the system timezone.
         *
         *
         * This clock simply returns the specified instant. As such, it is not a clock in the
         * conventional sense. The main use case for this is in testing, where the fixed clock ensures
         * tests are not dependent on the current clock.
         *
         *
         * The returned implementation is immutable, thread-safe and `Serializable`.
         *
         * @param epochMs the time in UTC milliseconds from the epoch.
         */
        fun fixed(epochMs: Long): TimeSourceCopy {
            return TimeSourceCopy(epochMs, null)
        }
    }
}
