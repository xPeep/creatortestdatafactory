package cz.upce.eshop.service

import cz.upce.eshop.entity.UserPhoto

interface UserPhotosService {
    fun add(userPhoto: UserPhoto): Long
    fun remove(id: Long)
}
