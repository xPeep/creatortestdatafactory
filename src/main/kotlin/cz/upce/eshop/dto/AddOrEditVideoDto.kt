package cz.upce.eshop.dto

import java.time.LocalDateTime

data class AddOrEditVideoDto(
    val id: Long? = null,
    val link: String = "",
    val name: String = "",
    val dateTime: LocalDateTime
)