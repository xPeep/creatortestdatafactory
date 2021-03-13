package cz.upce.eshop.entity

import cz.upce.eshop.repository.AbstractJpaPersistable
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "USER_PHOTO")
class UserPhoto(
    @Column var name: String,
    @Column var link: String,
    @Column(columnDefinition = "TIMESTAMP") var dateTime: LocalDateTime
) : AbstractJpaPersistable<Long>()