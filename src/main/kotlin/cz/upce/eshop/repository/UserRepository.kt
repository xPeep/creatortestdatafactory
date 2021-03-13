package cz.upce.eshop.repository

import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository<T> : JpaRepository<User, T> {
    fun findByUserType(vip: UserType): MutableList<User>
    fun findByUsername(string: String): User?

    @Query("SELECT u FROM USER u WHERE u.userType = 'REGULAR'")
    fun findAllUsersByUserType(): User?
}

