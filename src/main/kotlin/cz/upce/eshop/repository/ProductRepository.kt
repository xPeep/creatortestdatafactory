package cz.upce.eshop.repository

import cz.upce.eshop.entity.Photo
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface PhotoRepository : JpaRepository<Photo, Long> {
    @EntityGraph(attributePaths = ["photos"])
    fun findPhotoByNameContains(contains: String): Photo
    fun<T> findPhotoByIdBetween(firstId: T, secondId: T): MutableList<Photo>
}