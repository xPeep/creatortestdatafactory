package cz.upce.eshop.entity

import javax.persistence.*

@Entity(name = "orderForm")
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Enumerated(EnumType.STRING) @Column(length = 20) var state: StateEnum,
    @OneToMany(mappedBy = "id" , fetch= FetchType.LAZY) var orderHasProduct: MutableSet<OrderHasProduct>
)