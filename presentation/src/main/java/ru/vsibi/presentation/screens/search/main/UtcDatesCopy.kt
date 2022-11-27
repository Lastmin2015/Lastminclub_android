package ru.vsibi.presentation.screens.search.main

import android.annotation.TargetApi
import android.content.res.Resources
import android.icu.text.DateFormat
import android.icu.util.TimeZone
import android.os.Build.VERSION_CODES
import com.google.android.material.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicReference


/**
 * Utility class for common operations on timezones, calendars, dateformats, and longs representing
 * time in milliseconds.
 */
internal object UtcDatesCopy {
    const val UTC = "UTC"
    var timeSourceRef = AtomicReference<TimeSourceCopy?>()
    var timeSource: TimeSourceCopy?
        get() {
            val timeSource = timeSourceRef.get()
            return timeSource ?: TimeSourceCopy.system()
        }
        set(timeSource) {
            timeSourceRef.set(timeSource)
        }
    private val timeZone: java.util.TimeZone
        private get() = java.util.TimeZone.getTimeZone(UTC)

    @get:TargetApi(VERSION_CODES.N)
    private val utcAndroidTimeZone: TimeZone
        private get() = TimeZone.getTimeZone(UTC)

    /**
     * Returns a Calendar object in UTC time zone representing the first moment of current date.
     */
    val todayCalendar: Calendar
        get() {
            val today = timeSource!!.now()
            today[Calendar.HOUR_OF_DAY] = 0
            today[Calendar.MINUTE] = 0
            today[Calendar.SECOND] = 0
            today[Calendar.MILLISECOND] = 0
            today.timeZone = timeZone
            return today
        }

    /**
     * Returns an empty Calendar in UTC time zone.
     *
     * @return An empty Calendar in UTC time zone.
     * @see {@link .getUtcCalendarOf
     * @see Calendar.clear
     */
    val utcCalendar: Calendar
        get() = getUtcCalendarOf(null)

    /**
     * Returns a Calendar object in UTC time zone representing the moment in input Calendar object. An
     * empty Calendar object in UTC will be return if input is null.
     *
     * @param rawCalendar the Calendar object representing the moment to process.
     * @return A Calendar object in UTC time zone.
     * @see @see Calendar.clear
     */
    fun getUtcCalendarOf(rawCalendar: Calendar?): Calendar {
        val utc = Calendar.getInstance(timeZone)
        if (rawCalendar == null) {
            utc.clear()
        } else {
            utc.timeInMillis = rawCalendar.timeInMillis
        }
        return utc
    }

    /**
     * Returns a Calendar object in UTC time zone representing the start of day in UTC represented in
     * the input Calendar object, i.e., the time (fields smaller than a day) is stripped based on the
     * UTC time zone.
     *
     * @param rawCalendar the Calendar object representing the moment to process.
     * @return A Calendar object representing the start of day in UTC time zone.
     */
    fun getDayCopy(rawCalendar: Calendar?): Calendar {
        val rawCalendarInUtc = getUtcCalendarOf(rawCalendar)
        val utcCalendar = utcCalendar
        utcCalendar[rawCalendarInUtc[Calendar.YEAR], rawCalendarInUtc[Calendar.MONTH]] =
            rawCalendarInUtc[Calendar.DAY_OF_MONTH]
        return utcCalendar
    }

    /**
     * Strips all information from the time in milliseconds at granularities more specific than day of
     * the month.
     *
     * @param rawDate A long representing the time as UTC milliseconds from the epoch
     * @return A canonical long representing the time as UTC milliseconds for the represented day.
     */
    fun canonicalYearMonthDay(rawDate: Long): Long {
        val rawCalendar = utcCalendar
        rawCalendar.timeInMillis = rawDate
        val sanitizedStartItem = getDayCopy(rawCalendar)
        return sanitizedStartItem.timeInMillis
    }

    @TargetApi(VERSION_CODES.N)
    private fun getAndroidFormat(pattern: String, locale: Locale): DateFormat {
        val format = DateFormat.getInstanceForSkeleton(pattern, locale)
        format.timeZone = utcAndroidTimeZone
        return format
    }

    private fun getFormat(style: Int, locale: Locale): java.text.DateFormat {
        val format = java.text.DateFormat.getDateInstance(style, locale)
        format.timeZone = timeZone
        return format
    }

    val textInputFormat: SimpleDateFormat
        get() {
            val pattern = (java.text.DateFormat.getDateInstance(
                java.text.DateFormat.SHORT,
                Locale.getDefault()
            ) as SimpleDateFormat)
                .toLocalizedPattern()
                .replace("\\s+".toRegex(), "")
            val format = SimpleDateFormat(pattern, Locale.getDefault())
            format.timeZone = timeZone
            format.isLenient = false
            return format
        }

    fun getTextInputHint(res: Resources, format: SimpleDateFormat): String {
        val formatHint = format.toLocalizedPattern()
        val yearChar = res.getString(R.string.mtrl_picker_text_input_year_abbr)
        val monthChar = res.getString(R.string.mtrl_picker_text_input_month_abbr)
        val dayChar = res.getString(R.string.mtrl_picker_text_input_day_abbr)
        return formatHint.replace("d".toRegex(), dayChar).replace("M".toRegex(), monthChar)
            .replace("y".toRegex(), yearChar)
    }

    fun getSimpleFormat(pattern: String): SimpleDateFormat {
        return getSimpleFormat(pattern, Locale.getDefault())
    }

    private fun getSimpleFormat(pattern: String, locale: Locale): SimpleDateFormat {
        val format = SimpleDateFormat(pattern, locale)
        format.timeZone = timeZone
        return format
    }

    @TargetApi(VERSION_CODES.N)
    fun getYearAbbrMonthDayFormat(locale: Locale): DateFormat {
        return getAndroidFormat(DateFormat.YEAR_ABBR_MONTH_DAY, locale)
    }

    @TargetApi(VERSION_CODES.N)
    fun getAbbrMonthDayFormat(locale: Locale): DateFormat {
        return getAndroidFormat(DateFormat.ABBR_MONTH_DAY, locale)
    }

    @TargetApi(VERSION_CODES.N)
    fun getAbbrMonthWeekdayDayFormat(locale: Locale): DateFormat {
        return getAndroidFormat(DateFormat.ABBR_MONTH_WEEKDAY_DAY, locale)
    }

    @TargetApi(VERSION_CODES.N)
    fun getYearAbbrMonthWeekdayDayFormat(locale: Locale): DateFormat {
        return getAndroidFormat(DateFormat.YEAR_ABBR_MONTH_WEEKDAY_DAY, locale)
    }

    val mediumFormat: java.text.DateFormat
        get() = getMediumFormat(Locale.getDefault())

    fun getMediumFormat(locale: Locale): java.text.DateFormat {
        return getFormat(java.text.DateFormat.MEDIUM, locale)
    }

    val mediumNoYear: java.text.DateFormat
        get() = getMediumNoYear(Locale.getDefault())

    fun getMediumNoYear(locale: Locale): java.text.DateFormat {
        val format = getMediumFormat(locale) as SimpleDateFormat
        format.applyPattern(removeYearFromDateFormatPattern(format.toPattern()))
        return format
    }

    val fullFormat: java.text.DateFormat
        get() = getFullFormat(Locale.getDefault())

    fun getFullFormat(locale: Locale): java.text.DateFormat {
        return getFormat(java.text.DateFormat.FULL, locale)
    }

    private fun removeYearFromDateFormatPattern(pattern: String): String {
        val yearCharacters = "yY"
        val yearPosition = findCharactersInDateFormatPattern(pattern, yearCharacters, 1, 0)
        if (yearPosition >= pattern.length) {
            // No year character was found in this pattern, return as-is
            return pattern
        }
        var monthDayCharacters = "EMd"
        val yearEndPosition = findCharactersInDateFormatPattern(pattern, monthDayCharacters, 1, yearPosition)
        if (yearEndPosition < pattern.length) {
            monthDayCharacters += ","
        }
        var yearStartPosition = findCharactersInDateFormatPattern(pattern, monthDayCharacters, -1, yearPosition)
        yearStartPosition++
        val yearPattern = pattern.substring(yearStartPosition, yearEndPosition)
        return pattern.replace(yearPattern, " ").trim { it <= ' ' }
    }

    private fun findCharactersInDateFormatPattern(
        pattern: String,
        characterSequence: String,
        increment: Int,
        initialPosition: Int
    ): Int {
        var position = initialPosition

        // Increment while we haven't found the characters we're looking for in the date pattern
        while (position >= 0 && position < pattern.length
            && characterSequence.indexOf(pattern[position]) == -1
        ) {

            // If an open string is found, increment until we close the string
            if (pattern[position] == '\'') {
                position += increment
                while (position >= 0 && position < pattern.length && pattern[position] != '\'') {
                    position += increment
                }
            }
            position += increment
        }
        return position
    }
}
