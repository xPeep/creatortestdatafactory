package cz.upce.eshop.repository

import cz.upce.eshop.entity.Product
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    @EntityGraph(attributePaths = ["productInOrders"])
    fun findProductByProductNameContains(contains: String): Product
    fun findProductByIdBetween(start: Long, finish: Long): MutableList<Product>
}