package ru.vsibi.presentation.screens.tours.info.flights.main

import ru.vsibi.presentation.models.flight.*
import ru.vsibi.presentation.screens.tours.info.flights.info.models.*
import java.text.SimpleDateFormat
import java.util.*

object FlightsMockFactory {

    fun getMockFlights(): List<FlightsModel> {
        val flights = mutableListOf<FlightsModel>()
        flights.add(FlightsModel())
        flights.add(FlightsModel())
        return flights
    }

    fun getFlights(): FlightResponse {
        val resultList = mutableListOf<FlightResult>()

        resultList.add(
            FlightResult(
                id = "60d04fbde8dc0846407d00d4",
                fly_from = "BER",
                fly_to = "MSC",
                date_from = 1609891200,
                date_to = 1610236800,
                city_from = "Berlin",
                city_code_from = "BER",
                city_to = "Moscow",
                city_code_to = "MSC",
                quality = 151.66632,
                distance = 406.5,
                duration = Duration(
                    departure = 57000,
                    `return` = 0,
                    total = 57000
                ),
                price = 13,
                conversion = Conversion(13),
                bags_price = BagsPrice(
                    `1` = 69.87,
                    `2` = 139.73
                ),
                baglimit = Baglimit(
                    hand_height = 40,
                    hand_length = 55,
                    hand_weight = 10,
                    hand_width = 20,
                    hold_dimensions_sum = 319,
                    hold_height = 119,
                    hold_length = 119,
                    hold_weight = 20,
                    hold_width = 81
                ),
                seats = 3,
                route = arrayListOf("60d04fbde8dc0846407d00d4", "60d04fbde8dc0846407d00d4"),
                booking_token = "Bl_eE2L8FE9EFMlSj3m-vJkRj5lRiHnay_0uhm15CjKEMkGyKdQc172NPdnTHPRvverbVuJDHjQxB-QGEloDsoEWXy8xffDMLdKh2KmmHGhsHlbmRe7N8lWRHk96EIUTp5vRNdJkaQayCV6LmbAlWNllM6pUOfxdw0CNgezZV4yuAuQ7caLQH9Jlo3KW4K7CYw_b70XqC23goDJd7-rYYRd60ntMLoAn5j5xPKe5I_bnedGvaOWQtxohCDI7GcdLuBcnMEE-Y8rz52zl5g0_0_lWjP_D506Bum6e2votBIbSMSRzwrjQ3eObrE53A-_rM8u5ufSfmqiL_Sqmp_Fzirl3p51wRZ8NwLQUWZHyZFD9CiGakX440K1SFN_WrcDeL0szoP54l4l1w3mb6xcNHOvXSEpylXo8vTx-ni0wf-OmNmvVYoiB4pwjmbcM9JJhd70fgeYTtGBYQBbFPBklf-jV2Ke-7EAOvFGnth-1hRK-08GJi3O-EVlit07eBeqpOeTyTxCtQbzLcyV8n-cmwsD-iipxhdk0H0Gb0wJGC5NT2Bp27xKRKLcONMF-owvldWe2EW9QNB2iMofOe8iC9Gi-ANv1qOFXH7K00cGQ5frEoLKb_oL3mymHz2IskyZdf2WsOq2wlEPmJFlqQijoAGYdxfGES3GYJHtYG6QhMHUYbadzbrdFSznJGPxTnFPhHjHUe9T9jarrGjVU4MJQINpsLTS31So4zcv1h1mdhwdlTylrL08dSyk22KKmh4CdjM0v7WE8aA2N1-orT7X7Cow==",
                local_arrival = "2021-06-28T10:15:00Z",
                utc_arrival = "2021-06-28T08:15:00Z",
                local_departure = "2021-06-27T18:25:00Z",
                utc_departure = "2021-06-27T16:25:00Z",
                created_at = 1624264637,
                updated_at = 1624264637,
                stops = listOf(
                    FlightResult(
                        id = "60d04fbde8dc0846407d00d4",
                        fly_from = "PSG",
                        fly_to = "MSC",
                        date_from = 1609891200,
                        date_to = 1610236800,
                        city_from = "Paris",
                        city_code_from = "PSG",
                        city_to = "Moscow",
                        city_code_to = "MSC",
                        quality = 151.66632,
                        distance = 406.5,
                        duration = Duration(
                            departure = 57000,
                            `return` = 0,
                            total = 57000
                        ),
                        price = 13,
                        conversion = Conversion(13),
                        bags_price = BagsPrice(
                            `1` = 69.87,
                            `2` = 139.73
                        ),
                        baglimit = Baglimit(
                            hand_height = 40,
                            hand_length = 55,
                            hand_weight = 10,
                            hand_width = 20,
                            hold_dimensions_sum = 319,
                            hold_height = 119,
                            hold_length = 119,
                            hold_weight = 20,
                            hold_width = 81
                        ),
                        seats = 3,
                        route = arrayListOf("60d04fbde8dc0846407d00d4", "60d04fbde8dc0846407d00d4"),
                        booking_token = "Bl_eE2L8FE9EFMlSj3m-vJkRj5lRiHnay_0uhm15CjKEMkGyKdQc172NPdnTHPRvverbVuJDHjQxB-QGEloDsoEWXy8xffDMLdKh2KmmHGhsHlbmRe7N8lWRHk96EIUTp5vRNdJkaQayCV6LmbAlWNllM6pUOfxdw0CNgezZV4yuAuQ7caLQH9Jlo3KW4K7CYw_b70XqC23goDJd7-rYYRd60ntMLoAn5j5xPKe5I_bnedGvaOWQtxohCDI7GcdLuBcnMEE-Y8rz52zl5g0_0_lWjP_D506Bum6e2votBIbSMSRzwrjQ3eObrE53A-_rM8u5ufSfmqiL_Sqmp_Fzirl3p51wRZ8NwLQUWZHyZFD9CiGakX440K1SFN_WrcDeL0szoP54l4l1w3mb6xcNHOvXSEpylXo8vTx-ni0wf-OmNmvVYoiB4pwjmbcM9JJhd70fgeYTtGBYQBbFPBklf-jV2Ke-7EAOvFGnth-1hRK-08GJi3O-EVlit07eBeqpOeTyTxCtQbzLcyV8n-cmwsD-iipxhdk0H0Gb0wJGC5NT2Bp27xKRKLcONMF-owvldWe2EW9QNB2iMofOe8iC9Gi-ANv1qOFXH7K00cGQ5frEoLKb_oL3mymHz2IskyZdf2WsOq2wlEPmJFlqQijoAGYdxfGES3GYJHtYG6QhMHUYbadzbrdFSznJGPxTnFPhHjHUe9T9jarrGjVU4MJQINpsLTS31So4zcv1h1mdhwdlTylrL08dSyk22KKmh4CdjM0v7WE8aA2N1-orT7X7Cow==",
                        local_arrival = "2021-06-28T10:15:00Z",
                        utc_arrival = "2021-06-28T08:15:00Z",
                        local_departure = "2021-06-27T18:25:00Z",
                        utc_departure = "2021-06-27T16:25:00Z",
                        created_at = 1624264637,
                        updated_at = 1624264637,
                        stops = listOf()
                    )
                )
            )
        )
        resultList.add(
            FlightResult(
                id = "60d04fbde8dc0846407d00d4",
                fly_from = "FRA",
                fly_to = "PRG",
                date_from = 1609891200,
                date_to = 1610236800,
                city_from = "Frankfurt",
                city_code_from = "FRA",
                city_to = "Prague",
                city_code_to = "PRG",
                quality = 151.66632,
                distance = 406.5,
                duration = Duration(
                    departure = 57000,
                    `return` = 0,
                    total = 57000
                ),
                price = 13,
                conversion = Conversion(13),
                bags_price = BagsPrice(
                    `1` = 69.87,
                    `2` = 139.73
                ),
                baglimit = Baglimit(
                    hand_height = 40,
                    hand_length = 55,
                    hand_weight = 10,
                    hand_width = 20,
                    hold_dimensions_sum = 319,
                    hold_height = 119,
                    hold_length = 119,
                    hold_weight = 20,
                    hold_width = 81
                ),
                seats = 3,
                route = arrayListOf("60d04fbde8dc0846407d00d4", "60d04fbde8dc0846407d00d4"),
                booking_token = "Bl_eE2L8FE9EFMlSj3m-vJkRj5lRiHnay_0uhm15CjKEMkGyKdQc172NPdnTHPRvverbVuJDHjQxB-QGEloDsoEWXy8xffDMLdKh2KmmHGhsHlbmRe7N8lWRHk96EIUTp5vRNdJkaQayCV6LmbAlWNllM6pUOfxdw0CNgezZV4yuAuQ7caLQH9Jlo3KW4K7CYw_b70XqC23goDJd7-rYYRd60ntMLoAn5j5xPKe5I_bnedGvaOWQtxohCDI7GcdLuBcnMEE-Y8rz52zl5g0_0_lWjP_D506Bum6e2votBIbSMSRzwrjQ3eObrE53A-_rM8u5ufSfmqiL_Sqmp_Fzirl3p51wRZ8NwLQUWZHyZFD9CiGakX440K1SFN_WrcDeL0szoP54l4l1w3mb6xcNHOvXSEpylXo8vTx-ni0wf-OmNmvVYoiB4pwjmbcM9JJhd70fgeYTtGBYQBbFPBklf-jV2Ke-7EAOvFGnth-1hRK-08GJi3O-EVlit07eBeqpOeTyTxCtQbzLcyV8n-cmwsD-iipxhdk0H0Gb0wJGC5NT2Bp27xKRKLcONMF-owvldWe2EW9QNB2iMofOe8iC9Gi-ANv1qOFXH7K00cGQ5frEoLKb_oL3mymHz2IskyZdf2WsOq2wlEPmJFlqQijoAGYdxfGES3GYJHtYG6QhMHUYbadzbrdFSznJGPxTnFPhHjHUe9T9jarrGjVU4MJQINpsLTS31So4zcv1h1mdhwdlTylrL08dSyk22KKmh4CdjM0v7WE8aA2N1-orT7X7Cow==",
                local_arrival = "2021-06-28T10:15:00Z",
                utc_arrival = "2021-06-28T08:15:00Z",
                local_departure = "2021-06-27T18:25:00Z",
                utc_departure = "2021-06-27T16:25:00Z",
                created_at = 1624264637,
                updated_at = 1624264637,
                stops = listOf()
            )
        )
        resultList.add(
            FlightResult(
                id = "60d04fbde8dc0846407d00d4",
                fly_from = "BER",
                fly_to = "PSG",
                date_from = 1609891200,
                date_to = 1610236800,
                city_from = "Berlin",
                city_code_from = "BER",
                city_to = "Paris",
                city_code_to = "PSG",
                quality = 151.66632,
                distance = 406.5,
                duration = Duration(
                    departure = 57000,
                    `return` = 0,
                    total = 57000
                ),
                price = 13,
                conversion = Conversion(13),
                bags_price = BagsPrice(
                    `1` = 69.87,
                    `2` = 139.73
                ),
                baglimit = Baglimit(
                    hand_height = 40,
                    hand_length = 55,
                    hand_weight = 10,
                    hand_width = 20,
                    hold_dimensions_sum = 319,
                    hold_height = 119,
                    hold_length = 119,
                    hold_weight = 20,
                    hold_width = 81
                ),
                seats = 3,
                route = arrayListOf("60d04fbde8dc0846407d00d4", "60d04fbde8dc0846407d00d4"),
                booking_token = "Bl_eE2L8FE9EFMlSj3m-vJkRj5lRiHnay_0uhm15CjKEMkGyKdQc172NPdnTHPRvverbVuJDHjQxB-QGEloDsoEWXy8xffDMLdKh2KmmHGhsHlbmRe7N8lWRHk96EIUTp5vRNdJkaQayCV6LmbAlWNllM6pUOfxdw0CNgezZV4yuAuQ7caLQH9Jlo3KW4K7CYw_b70XqC23goDJd7-rYYRd60ntMLoAn5j5xPKe5I_bnedGvaOWQtxohCDI7GcdLuBcnMEE-Y8rz52zl5g0_0_lWjP_D506Bum6e2votBIbSMSRzwrjQ3eObrE53A-_rM8u5ufSfmqiL_Sqmp_Fzirl3p51wRZ8NwLQUWZHyZFD9CiGakX440K1SFN_WrcDeL0szoP54l4l1w3mb6xcNHOvXSEpylXo8vTx-ni0wf-OmNmvVYoiB4pwjmbcM9JJhd70fgeYTtGBYQBbFPBklf-jV2Ke-7EAOvFGnth-1hRK-08GJi3O-EVlit07eBeqpOeTyTxCtQbzLcyV8n-cmwsD-iipxhdk0H0Gb0wJGC5NT2Bp27xKRKLcONMF-owvldWe2EW9QNB2iMofOe8iC9Gi-ANv1qOFXH7K00cGQ5frEoLKb_oL3mymHz2IskyZdf2WsOq2wlEPmJFlqQijoAGYdxfGES3GYJHtYG6QhMHUYbadzbrdFSznJGPxTnFPhHjHUe9T9jarrGjVU4MJQINpsLTS31So4zcv1h1mdhwdlTylrL08dSyk22KKmh4CdjM0v7WE8aA2N1-orT7X7Cow==",
                local_arrival = "2021-06-28T10:15:00Z",
                utc_arrival = "2021-06-28T08:15:00Z",
                local_departure = "2021-06-27T18:25:00Z",
                utc_departure = "2021-06-27T16:25:00Z",
                created_at = 1624264637,
                updated_at = 1624264637,
                stops = listOf()
            )
        )
        return FlightResponse(
            resultList,
            true
        )
    }

    fun getMockFlightInfo(): List<FlightInfo> {
        val list = mutableListOf<FlightInfo>()
        list.add(FlightItem("5:45", "Frankfurt", "Frankfurt Airport"))
        list.add(FlightDescription("1h 30min", "Frankfurt Airlines", "Economy"))
        list.add(FlightItem("6:00", "Copengagen", "Castrup Airport"))
        list.add(FlightConnectionItem("3h 25min"))
        list.add(FlightItem("9:25", "Copengagen", "Castrup Airport"))
        list.add(FlightDescription("3h 45min", "Pegasus", "Economy"))
        list.add(FlightArrival("27 Jun", "Arrival on next day"))
        list.add(FlightItem("00:15", "Antalya", "Antalya Airport"))
        return list
    }

    private val timeFormatter = SimpleDateFormat("HH:mm")
    private val dateFormatter = SimpleDateFormat("dd MMM")

    fun createFlightsInfo(flight: FlightResult?): List<FlightInfo> {
        if (flight == null) return listOf()
        val list = mutableListOf<FlightInfo>()
        if (flight.stops.isEmpty()) {
            list.add(
                FlightItem(
                    timeFormatter.format(Date(flight.date_from)),
                    flight.city_from,
                    "" + flight.city_from + " Airport"
                )
            )
            list.add(FlightDescription("1h 30min", "Airlines", "Economy"))
            list.add(FlightArrival(dateFormatter.format(Date(flight.date_to)), "Arrival on current day"))
            list.add(
                FlightItem(
                    timeFormatter.format(Date(flight.date_to)),
                    flight.city_to,
                    "" + flight.city_to + " Airport"
                )
            )
            return list
        } else {
            list.add(
                FlightItem(
                    timeFormatter.format(Date(flight.date_from)),
                    flight.city_from,
                    "" + flight.city_from + " Airport"
                )
            )
            list.add(FlightDescription("1h 30min", "Airlines", "Economy"))

            list.add(FlightConnectionItem("3h"))
            list.add(
                FlightItem(
                    timeFormatter.format(Date(flight.stops[0].date_from)),
                    flight.stops[0].city_from,
                    "" + flight.stops[0].city_from + " Airport"
                )
            )
            list.add(FlightDescription("1h 30min", "Airlines", "Economy"))

            list.add(FlightArrival(dateFormatter.format(Date(flight.date_to)), "Arrival on current day"))
            list.add(
                FlightItem(
                    timeFormatter.format(Date(flight.date_to)),
                    flight.city_to,
                    "" + flight.city_to + " Airport"
                )
            )
            return list
        }
    }

}