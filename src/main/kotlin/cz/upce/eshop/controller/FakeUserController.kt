package cz.upce.eshop.controller

import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserType

class FakeUserController {
    companion object {
        val fakeUsers = mutableListOf(
            User.createUser(
                "adno",
                "1234",
                "Adam",
                "Novák",
                "+420 784 585 998",
                "NaChate",
                "https://fotky.com/Nachate",
                UserType.REGULAR
            ),
            User.createUser(
                "tost",
                "5487",
                "Tomáš",
                "Stodola",
                "+420 772 959 336",
                "Studanka",
                "https://fotky.com/Studanka",
                UserType.VIP
            ),
            User.createUser(
                "moab",
                "9823",
                "Monika",
                "Absolonová",
                "+420 714 159 414",
                "RandomFotka",
                "https://fotky.com/RandomFotka",
                UserType.ADMIN
            ),
            User.createUser(
                "adno",
                "1234",
                "Debra",
                "Nejedlá",
                "+420 769 365 899",
                "Tiktokprofilovka",
                "https://fotky.com/Tiktokprofilovka",
                UserType.REGULAR
            )
        )
    }
}

