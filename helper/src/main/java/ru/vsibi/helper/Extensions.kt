package ru.vsibi.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val dateFormatter = SimpleDateFormat("dd.MM.yyyy");
val dayMonthFormatter = SimpleDateFormat("dd.MM");
val timeFormatter = SimpleDateFormat("HH:mm")


fun getTimestampFromDate(dateString : String) : Long?{
    return try {
        val date: Date = dateFormatter.parse(dateString)
        date.time
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

fun getTimestampFromTime(dateString : String) : Long?{
    return try {
        val date: Date = timeFormatter.parse(dateString)
        date.time
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

fun getDateFromTimestamp(dateLong : Long?) : String?{
    var factor = 1
    if(dateLong == null) return null
    if(dateLong.toString().length == 10){
        factor = 1000
    }
    return dateFormatter.format(dateLong * factor)
}

fun getDateDayMonth(dateLong : Long?) : String?{
    var factor = 1
    if(dateLong == null) return null
    if(dateLong.toString().length == 10){
        factor = 1000
    }
    return dayMonthFormatter.format(dateLong * factor)
}

fun getTimeFromTimestamp(timeLong : Long?) : String?{
    if(timeLong == null) return null
    return timeFormatter.format(timeLong)
}

fun getTodayDayOfWeek() : String{
    val sdf = SimpleDateFormat("EEEE")
    val d = Date()
    return sdf.format(d)
}

fun getToday(): String{
    val dateFormat =
        SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val d = Date()
    return dateFormat.format(d)
}

fun getPlaceText(regionName: String?, countryName: String?, resortName: String?): String {
    return listOfNotNull(countryName, regionName, resortName)
        .joinToString(", ")
}