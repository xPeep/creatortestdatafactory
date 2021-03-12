package cz.upce.eshop.repository

import cz.upce.eshop.entity.Photo
import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserPhotos
import cz.upce.eshop.entity.UserType
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long>{
    fun findUserByUsernameContains(contains: String): User
    fun findByUserType(vip: UserType): MutableList<User>
}

