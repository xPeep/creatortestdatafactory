package cz.upce.eshop.dto

import cz.upce.eshop.entity.UserPhoto
import java.time.LocalDateTime

data class AddOrEditPhotoDto(
    val id: Long? = null,
    val link: String = "",
    val name: String = "",
    val dateTime: LocalDateTime
) {
    fun transformToUserPhoto(): UserPhoto {
        val newUserPhoto = UserPhoto(link, name, dateTime)
        newUserPhoto.id = id
        return newUserPhoto
    }
}