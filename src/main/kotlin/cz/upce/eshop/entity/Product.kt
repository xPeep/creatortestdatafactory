package cz.upce.eshop.entity

import javax.persistence.*

@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column var productName: String,
    @OneToMany(mappedBy = "id") var productInOrders: MutableSet<OrderHasProduct>
)
