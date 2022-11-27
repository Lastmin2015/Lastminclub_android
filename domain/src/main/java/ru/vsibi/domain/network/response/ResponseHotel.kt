package ru.vsibi.domain.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ResponseHotel(
    val result: Result,
    val success: Boolean
) : Parcelable{
    @Parcelize
    data class Result(
        val beach_line_num: Int,
        val beach_type: BeachType,
        val boarding_type: @RawValue List<Any>,
        val created_at: Int,
        val distance_to_beach: Int,
        val distance_to_city: Int,
        val distance_to_slope: Int,
        val google_rating: Int,
        val google_rating_id: String,
        val hotel_chain: HotelChain,
        val hotel_class: HotelClass,
        val hotel_descriptions: List<HotelDescription>,
        val hotel_format: HotelFormat,
        val hotel_images: List<ResponseSearch.HotelImage>,
        val hotel_type: HotelType,
        val id: String,
        val is_active: Boolean,
        val is_beach_private: Boolean,
        val is_bestseller: Boolean,
        val is_recommended: Boolean,
        val is_visited: Boolean,
        val keywords: String,
        val latitude: Int,
        val longitude: Int,
        val name: String,
        val place: ResponseSearch.Place,
        val room_type: List<RoomType>,
        val stars: Int,
        val trip_advisor_id: String,
        val trip_advisor_rating: Int,
        val trip_advisor_url: String,
        val updated_at: Int,
        val url_pretty_name: String
    ) : Parcelable{

        @Parcelize
        class BeachType : Parcelable
        @Parcelize
        class HotelChain : Parcelable

        @Parcelize
        data class HotelClass(
            val created_at: Int,
            val id: String,
            val name: String,
            val show_in_filter: Boolean,
            val updated_at: Int
        ) : Parcelable

        @Parcelize
        data class HotelDescription(
            val description: String,
            val id: String,
            val locale: String
        ) : Parcelable

        @Parcelize
        data class HotelFormat(
            val hotel_format_name: HotelFormatName,
            val id: String,
            val order: Int,
            val show_in_filter: Boolean
        ) : Parcelable{
            @Parcelize
            data class HotelFormatName(
                val id: String,
                val locale: String,
                val name: String
            ) : Parcelable
        }

        @Parcelize
        data class HotelType(
            val hotel_type_name: HotelTypeName
        ) : Parcelable{
            @Parcelize
            data class HotelTypeName(
                val id: String,
                val locale: String,
                val name: String
            ): Parcelable
        }

        @Parcelize
        data class RoomType(
            val locale: String,
            val name: String,
            val room_type_id: String
        ) : Parcelable
    }
}