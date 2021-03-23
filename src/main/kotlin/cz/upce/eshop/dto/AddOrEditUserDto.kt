package cz.upce.eshop.dto

import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserInformation
import cz.upce.eshop.entity.UserType

data class AddOrEditUserDto(
    val id: Long? = null,
    val username: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = ""
) {

    fun transformToUser(): User {
        val newUser =
            User(username, password, userType = UserType.REGULAR, UserInformation(firstName, lastName, phoneNumber))
        newUser.id = id
        return newUser
    }
}