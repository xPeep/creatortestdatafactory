package cz.upce.eshop.dto

import cz.upce.eshop.entity.UserVideo
import java.time.LocalDateTime

data class AddOrEditVideoDto(
    val id: Long? = null,
    val link: String = "",
    val name: String = "",
    val dateTime: LocalDateTime
) {
    fun transformToUserVideo(): UserVideo {
        val newUserVideo = UserVideo(link, name, dateTime)
        newUserVideo.id = id
        return newUserVideo
    }
}
