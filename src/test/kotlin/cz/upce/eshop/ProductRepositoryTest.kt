package cz.upce.eshop

import cz.upce.eshop.entity.Product
import cz.upce.eshop.repository.OrderHasProductRepository
import cz.upce.eshop.repository.OrderRepository
import cz.upce.eshop.repository.ProductRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest(
    @Autowired val productRepository: ProductRepository,
    @Autowired val orderRepository: OrderRepository,
    @Autowired val orderHasProductRepository: OrderHasProductRepository
) {

    @Test
    fun saveProductTest() {
        productRepository.save(Product(null, "MyProduct", mutableSetOf()))
        Assertions.assertThat(productRepository.findAll().size == 1)
    }

 /*   @Test
    fun saveProductTest() {
        val product = Product(null, "MyProduct", mutableSetOf())
        val order = Order(null, StateEnum.NEW, mutableSetOf())
        val orderHasProduct1 = OrderHasProduct(null, order,product, 4)
        val orderHasProduct2 = OrderHasProduct(null, order,product, 1)

        productRepository.save(product)
        orderRepository.save(order)

        orderHasProductRepository.save(orderHasProduct1)
        orderHasProductRepository.save(orderHasProduct2)

        val all = orderRepository.findAll()
    }*/
    /*  @Test
      fun saveProductTest() {
          val product = Product(null, "Laptop", mutableSetOf())
          productRepository.save(product)
          val products = productRepository.findAll()
          product.productName = "Notebook"
          productRepository.save(product)
          val product2 = productRepository.findProductByProductNameContains("Laptop")
          val productsByIdRange =  productRepository.findProductByIdBetween(2,4)
          val productsByIdRangeSorted =  productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
          val all1 =  productRepository.findAll(PageRequest.of(0,2))
          println()
      }*/

}
