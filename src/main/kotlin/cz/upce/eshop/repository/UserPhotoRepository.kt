package cz.upce.eshop.repository

import cz.upce.eshop.entity.UserPhoto
import org.springframework.data.jpa.repository.JpaRepository

interface UserPhotoRepository<T> : JpaRepository<UserPhoto, T> {
}