package cz.upce.eshop

import cz.upce.eshop.controller.FakeUserController
import cz.upce.eshop.entity.UserType
import cz.upce.eshop.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest
@Autowired constructor(
    val userRepository: UserRepository,
    val testEntityManager: TestEntityManager
) {

    @Test
    fun `should not find any user`() {
        assertThat(userRepository.findAll()).isEmpty()
    }

    @Test
    fun `should store new user`() {
        val newUser = FakeUserController.fakeUsers.first()
        val foundUser = userRepository.save(newUser)

        assertThat(foundUser).isEqualTo(newUser)
    }

    @Test
    fun `should find all users`() {
        FakeUserController.fakeUsers.forEach { user ->
            testEntityManager.persist(user)
        }
        assertThat(userRepository.findAll())
            .hasSize(FakeUserController.fakeUsers.size)
            .containsAll(FakeUserController.fakeUsers)
    }

    @Test
    fun `should find user by id`() {
        val firstUser = FakeUserController.fakeUsers.first()
        val secondUser = FakeUserController.fakeUsers.first { it != firstUser }

        testEntityManager.persist(firstUser)
        testEntityManager.persist(secondUser)

        val foundUser = userRepository.findByIdOrNull(secondUser.id)

        assertThat(foundUser).isEqualTo(secondUser)
    }

    @Test
    fun `should find  VIP user`() {
        val vipUser = FakeUserController.fakeUsers.first { it.userType == UserType.VIP }
        val regularUser = FakeUserController.fakeUsers.first { it.userType == UserType.REGULAR }
        val adminUser = FakeUserController.fakeUsers.first { it.userType == UserType.ADMIN }

        testEntityManager.persist(vipUser)
        testEntityManager.persist(regularUser)
        testEntityManager.persist(adminUser)

        val foundUser = userRepository.findByUserType(UserType.VIP).firstOrNull()

        assertThat(foundUser).isEqualTo(vipUser)
    }

    /*
       @Test
       fun saveProductTest2() {
           val product = Product("MyProduct", mutableSetOf())
           val order = Order(StateEnum.NEW, mutableSetOf())
           val orderHasProduct1 = OrderHasProduct(order,product, 4)
           val orderHasProduct2 = OrderHasProduct(order,product, 1)

           productRepository.save(product)
           orderRepository.save(order)

           orderHasProductRepository.save(orderHasProduct1)
           orderHasProductRepository.save(orderHasProduct2)

           val all = orderRepository.findAll()
       }
       @Test
        fun `basic entity checks`() {
             val product = Product("Laptop", mutableSetOf())
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
