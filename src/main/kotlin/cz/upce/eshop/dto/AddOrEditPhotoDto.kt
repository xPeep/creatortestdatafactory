package cz.upce.eshop.dto

import java.time.LocalDateTime

data class AddOrEditPhotoDto(
    val id: Long? = null,
    val link: String = "",
    val name: String = "",
    val dateTime: LocalDateTime
)