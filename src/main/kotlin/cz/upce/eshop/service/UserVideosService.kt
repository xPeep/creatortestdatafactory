package cz.upce.eshop.service

import cz.upce.eshop.entity.UserVideo

interface UserVideosService {
    fun add(userVideo: UserVideo): Long
    fun remove(id: Long)
}
