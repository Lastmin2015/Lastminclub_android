package ru.vsibi.presentation.screens.tours.main

object HotelsMockFactory {

    fun getMockHotels(): List<TourModel> {
        val hotels = mutableListOf<TourModel>()
        hotels.add(
            TourModel(
                "http://www.harbor.digital/test/assets/lastmin/transatlantik/transatlantik-listing-cover-1.jpg",
                "http://www.harbor.digital/test/assets/lastmin/transatlantik/transatlantik_full_1.jpg",
                "Movenpick Resort & Spa El Gouna",
                "Perfectly located right on the pedestrian avenue with taverns and shops, this hotel offers a high standard of service and quality service. Ideal for lovers of beautiful landscapes, the hotel's terrace offers breathtaking ocean views. \n" +
                        "\n" +
                        "Comfort is provided by a modern, elegant interior and neat rooms. There is a large swimming pool, several bars and restaurants where guests can order meals from around the world. The hotel offers a popular all-inclusive service and caters to its guests throughout the day.",
                "Gia de Izora, Tenerife, Canary Islands",
                1020,
                "€",
                "21.05",
                "27.05",
                true
            )
        )
        hotels.add(
            TourModel(
                "http://www.harbor.digital/test/assets/lastmin/sherwood/cover_list.jpg",
                "http://www.harbor.digital/test/assets/lastmin/sherwood/sherwood_full_1.jpg",
                "Dorisol Estrelicia",
                "Perfectly located right on the pedestrian avenue with taverns and shops, this hotel offers a high standard of service and quality service. Ideal for lovers of beautiful landscapes, the hotel's terrace offers breathtaking ocean views. \n" +
                        "\n" +
                        "Comfort is provided by a modern, elegant interior and neat rooms. There is a large swimming pool, several bars and restaurants where guests can order meals from around the world. The hotel offers a popular all-inclusive service and caters to its guests throughout the day.",
                "Madeira, Madeira, Portugal",
                1350,
                "€",
                "1.05",
                "7.05",
                false
            )
        )
        return hotels
    }

    fun getMockFavoriteHotels(): List<TourModel> {
        val hotels = mutableListOf<TourModel>()
        hotels.add(
            TourModel(
                "http://www.harbor.digital/test/assets/lastmin/transatlantik/transatlantik-listing-cover-1.jpg",
                "http://www.harbor.digital/test/assets/lastmin/transatlantik/transatlantik_full_1.jpg",
                "Movenpick Resort & Spa El Gouna",
                "Perfectly located right on the pedestrian avenue with taverns and shops, this hotel offers a high standard of service and quality service. Ideal for lovers of beautiful landscapes, the hotel's terrace offers breathtaking ocean views. \n" +
                        "\n" +
                        "Comfort is provided by a modern, elegant interior and neat rooms. There is a large swimming pool, several bars and restaurants where guests can order meals from around the world. The hotel offers a popular all-inclusive service and caters to its guests throughout the day.",
                "Gia de Izora, Tenerife, Canary Islands",
                1020,
                "€",
                "21.05",
                "27.05",
                true
            )
        )
        hotels.add(
            TourModel(
                "http://www.harbor.digital/test/assets/lastmin/sherwood/cover_list.jpg",
                "http://www.harbor.digital/test/assets/lastmin/sherwood/sherwood_full_1.jpg",
                "Dorisol Estrelicia",
                "Perfectly located right on the pedestrian avenue with taverns and shops, this hotel offers a high standard of service and quality service. Ideal for lovers of beautiful landscapes, the hotel's terrace offers breathtaking ocean views. \n" +
                        "\n" +
                        "Comfort is provided by a modern, elegant interior and neat rooms. There is a large swimming pool, several bars and restaurants where guests can order meals from around the world. The hotel offers a popular all-inclusive service and caters to its guests throughout the day.",
                "Madeira, Madeira, Portugal",
                1350,
                "€",
                "1.05",
                "7.05",
                true
            )
        )
        return hotels
    }
}