package cz.upce.eshop.repository

import cz.upce.eshop.entity.Order
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderRepository : JpaRepository<Order, Long>{
    @EntityGraph(attributePaths = ["orderHasProduct"])
    override fun findById(id:Long): Optional<Order>
}