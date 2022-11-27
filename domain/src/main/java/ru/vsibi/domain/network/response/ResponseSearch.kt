package ru.vsibi.domain.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class ResponseSearch (
    val success : Boolean,
    val result : List<Result>
        ) : Parcelable{

    @Parcelize
    class Result (
        val id : String,
        val tour : Tour,
        val price : Int,
        val num_adults : Int,
        val num_children : Int,
            ): Parcelable{}
    @Parcelize
    class Tour (
        val id : String,
        val tour_type : TourType,
        val operator : Operator,
        val hotel : Hotel,
        val outbound_flight : OutboundFlight,
        val return_flight : ReturnFlight
        ): Parcelable
    @Parcelize
    class TourType(
        val id : String,
        val name : String,
        val created_at : Long,
        val updated_at : Long
    ): Parcelable
    @Parcelize
    class Operator(
        val id : String,
        val email : String,
        val first_name : String,
        val last_name : String,
        val password : String,
        val phone : String,
        val picture_file_name : String,
        val locale : String,
        val role : Int,
        val token_reset : String,
        val middle_name : String,
        val birthday : String,
        val passport : ResponseProfile.Result.Passport,
        val created_at : String,
        val updated_at : String
    ): Parcelable
    @Parcelize
    class Hotel (
        val id : String,
        val hotel_class : @RawValue Any,
        val hotel_type : @RawValue Any,
        val hotel_format : HotelFormat,
        val place : Place,
        val name : String,
        val distance_to_beach : Int,
        val beach_line_num : Int,
        val beach_type : @RawValue Any,
        val is_beach_private : Boolean,
        val distance_to_slope : Int,
        val distance_to_city : Int,
        val stars : Int,
        val is_recommended : Boolean,
        val is_visited : Boolean,
        val is_active : Boolean,
        val is_bestseller : Boolean,
        val trip_advisor_id : String,
        val trip_advisor_rating : Int,
        val trip_advisor_url : String,
        val url_pretty_name : String,
        val latitude : Long,
        val longitude : Long,
        val google_rating_id : String,
        val google_rating : Int,
        val hotel_chain : @RawValue Any,
        val keywords : String,
        val room_type : @RawValue Array<Any>,
        val boarding_type : @RawValue Array<Any>,
        val hotel_images : Array<HotelImage>,
        val hotel_descriptions : List<ResponseHotel.Result.HotelDescription>,
        val created_at : Long,
        val updated_at : Long
            ): Parcelable
    @Parcelize
    class HotelFormat (
        val id : String,
        val show_in_filter : Boolean,
        val order : Int,
        val hotel_format_name : HotelFormatName
            ): Parcelable
    @Parcelize
    class HotelFormatName(
        val id : String,
        val hotel_format_id : String,
        val locale : String,
        val name : String
    ): Parcelable
    @Parcelize
    class Place (
        val city : City,
        val resort : Resort
            ): Parcelable
    @Parcelize
    class City (
        val id : String,
        val show_in_filter : Boolean,
        val order : Int,
        val country : Country,
        val iata : String,
        val city_name : @RawValue Any
            ): Parcelable
    @Parcelize
    class Country (
        val id : String,
        val show_in_filter : Boolean,
        val order : Int,
        val country_name : CountryName?
            ): Parcelable
    @Parcelize
    class Resort(
        val region : Region,
        val city : City,
        val resort_name : ResortName,
        val name : String

    ): Parcelable
    @Parcelize
    class Region (
        val id : String,
        val country : Country,
        val show_in_filter : Boolean,
        val order : Int,
        val region_name : RegionName?
            ): Parcelable
    @Parcelize
    class HotelImage(
        val id : String,
        val hotel_id : String,
        val file_name : String,
        val order : Int,
        val created_at : Long,
        val updated_at : Long
    ): Parcelable
    @Parcelize
    class OutboundFlight (
        val id : String,
        val fly_from : String,
        val fly_to : String,
        val date_from : Long,
        val date_to : Long,
        val city_from : String,
        val city_code_from : String,
        val city_to : String,
        val city_code_to : String,
        val quality : Int,
        val distance : Int,
        val duration : Duration,
        val price : Int,
        val conversion : @RawValue Any?,
        val bags_price : @RawValue Any?,
        val baglimit : Baglimit,
        val seats : Int,
        val route :  @RawValue Any?,
        val booking_token : String,
        val local_arrival : String,
        val utc_arrival : String,
        val local_departure : String,
        val utc_departure : String
            ): Parcelable
    @Parcelize
    class ReturnFlight (
        val id : String,
        val fly_from : String,
        val fly_to : String,
        val date_from : Long,
        val date_to : Long
            ): Parcelable
    @Parcelize
    class Duration(
        val departure : Int,
        val `return` : Int,
        val total : Int
    ): Parcelable
    @Parcelize
    class Baglimit(
        val hand_height : Int,
        val hand_weight : Int,
        val hand_width : Int,
        val hold_dimensions_sum : Int,
        val hold_height : Int,
        val hold_length : Int,
        val hold_weight : Int,
        val hold_width : Int,
    ): Parcelable

    @Parcelize
    class RegionName (
        val id : String,
        val region_id : String,
        val name : String,
        val locale : String,
        val created_at : Long,
        val updated_at : Long
            ): Parcelable{}

    @Parcelize
    class CountryName (
        val id : String,
        val country_id : String,
        val name : String,
        val locale : String,
        val created_at : Long,
        val updated_at : Long
            ): Parcelable{}

    @Parcelize
    class ResortName (
        val id : String,
        val resort_id : String,
        val name : String?,
        val locale : String,
        val created_at : Long,
        val updated_at : Long
            ): Parcelable{}
}