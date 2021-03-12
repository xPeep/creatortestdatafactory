package cz.upce.eshop.repository


import cz.upce.eshop.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface OrderHasProductRepository : JpaRepository<User, Long>