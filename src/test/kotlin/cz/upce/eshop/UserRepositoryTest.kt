package cz.upce.eshop

import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserType
import cz.upce.eshop.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class UserRepositoryTest
@Autowired constructor(
    val userRepository: UserRepository<Long>,
    val testEntityManager: TestEntityManager
) {

    @Test
    fun `should not find any user`() {
        assertThat(userRepository.findAll()).isEmpty()
    }

    @Test
    fun `should store new user`() {
        val newUser = UserMockGenerator.createUser()
        val foundUser = userRepository.save(newUser)

        assertThat(foundUser).isEqualTo(newUser)
    }

    @Test
    fun `should find all users`() {
        val fakeUsers = mutableListOf<User>()

        for (i in 0..10) {
            val user = UserMockGenerator.createUser()
            fakeUsers.add(user)
            userRepository.save(user)
        }
        assertThat(userRepository.findAll())
            .hasSize(fakeUsers.size)
            .containsAll(fakeUsers)
    }

    @Test
    fun `should find user by id`() {
        val firstUser = UserMockGenerator.createUser(UserType.REGULAR)
        val secondUser = UserMockGenerator.createUser(UserType.REGULAR)

        testEntityManager.persist(firstUser)
        testEntityManager.persist(secondUser)

        val foundUser = userRepository.findByIdOrNull(secondUser.id)

        assertThat(foundUser).isEqualTo(secondUser)
    }

    @Test
    fun `should find VIP users`() {
        val vipUser = UserMockGenerator.createUser(UserType.VIP)
        val regularUser = UserMockGenerator.createUser(UserType.REGULAR)
        val adminUser = UserMockGenerator.createUser(UserType.ADMIN)

        testEntityManager.persist(vipUser)
        testEntityManager.persist(regularUser)
        testEntityManager.persist(adminUser)

        val foundUser = userRepository.findByUserType(UserType.VIP).firstOrNull()

        assertThat(foundUser).isEqualTo(vipUser)
    }

    @Test
    fun `should update user by id`() {
        val vipUser = UserMockGenerator.createUser(UserType.VIP)
        val regularUser = UserMockGenerator.createUser(UserType.REGULAR)
        val adminUser = UserMockGenerator.createUser(UserType.ADMIN)

        testEntityManager.persist(vipUser)
        testEntityManager.persist(regularUser)
        testEntityManager.persist(adminUser)

        val updatedUser = userRepository.findByUsername(vipUser.username)
        updatedUser?.userType = UserType.ADMIN
        userRepository.save(updatedUser ?: throw IllegalStateException("User not found"))

        val checkUser = userRepository.findByUsername(vipUser.username)

        assertThat(checkUser?.userType).isEqualTo(UserType.ADMIN)
    }

    @Test
    fun `should delete all users`() {
        val vipUser = UserMockGenerator.createUser(UserType.VIP)
        val regularUser = UserMockGenerator.createUser(UserType.REGULAR)
        val adminUser = UserMockGenerator.createUser(UserType.ADMIN)

        testEntityManager.persist(vipUser)
        testEntityManager.persist(regularUser)
        testEntityManager.persist(adminUser)

        userRepository.deleteAll()

        assertThat(userRepository.findAll()).isEmpty()
    }

}
