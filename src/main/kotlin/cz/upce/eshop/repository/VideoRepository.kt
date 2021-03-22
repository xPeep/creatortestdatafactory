package cz.upce.eshop.repository

import cz.upce.eshop.entity.UserVideo
import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository<T> : JpaRepository<UserVideo, T> {
}