package cz.upce.eshop.dto

import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserInformation

data class AddOrEditUserDto(
    val id: Long? = null,
    val username: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = ""
) {
    companion object {
        fun createUserDto(user: User?): AddOrEditUserDto {
            return if (user == null) {
                AddOrEditUserDto()
            } else {
                AddOrEditUserDto(
                    user.id,
                    user.username,
                    user.password,
                    user.userInformation?.firstName ?: "",
                    user.userInformation?.lastName ?: "",
                    user.userInformation?.phoneNumber ?: ""
                )
            }
        }

        fun createUser(findUser: User?, addOrEditUserDto: AddOrEditUserDto): User {
            if (findUser == null) {
                return createUser(User(), addOrEditUserDto)
            }
            findUser.username = addOrEditUserDto.username
            findUser.password = addOrEditUserDto.password
            findUser.userInformation =
                UserInformation(addOrEditUserDto.firstName, addOrEditUserDto.lastName, addOrEditUserDto.phoneNumber)
            return findUser
        }
    }
}