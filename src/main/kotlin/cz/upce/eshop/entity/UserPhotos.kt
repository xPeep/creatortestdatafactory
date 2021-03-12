package cz.upce.eshop.entity

import cz.upce.eshop.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "UserPhotos")
class UserPhotos(
    @ManyToOne var user: User,
    @ManyToOne var photo: Photo,
) : AbstractJpaPersistable<Long>()