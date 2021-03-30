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
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@ComponentScan
@ExperimentalStdlibApi
class UserRepositoryTest
@Autowired constructor(
    private val creator: Creator,
    private val userRepository: UserRepository<Long>,
    private val userMockGenerator: UserMockGenerator
) {

    @Test
    fun `should not find any user`() {
        assertThat(userRepository.findAll()).isEmpty()
    }

    @Test
    fun `should store new user`() {
        val newUser = userMockGenerator.createUser()
        val foundUser = creator.save(newUser)

        assertThat(foundUser).isEqualTo(newUser)
    }

    @Test
    fun `should find all users`() {
        val fakeUsers = mutableListOf<User>()

        for (i in 0..10) {
            val user = userMockGenerator.createUser()
            fakeUsers.add(user)
            creator.save(user)
        }

        assertThat(userRepository.findAll())
            .hasSize(fakeUsers.size)
            .containsAll(fakeUsers)
    }

    @Test
    fun `should find user by id`() {
        val firstUser = userMockGenerator.createUser(UserType.REGULAR)
        val secondUser = userMockGenerator.createUser(UserType.REGULAR)

        creator.save(firstUser)
        creator.save(secondUser)

        val foundUser = userRepository.findByIdOrNull(secondUser.id)

        assertThat(foundUser).isEqualTo(secondUser)
    }

    @Test
    fun `should find VIP users`() {
        val vipUser = userMockGenerator.createUser(UserType.VIP)
        val regularUser = userMockGenerator.createUser(UserType.REGULAR)
        val adminUser = userMockGenerator.createUser(UserType.ADMIN)

        creator.save(vipUser)
        creator.save(regularUser)
        creator.save(adminUser)

        val foundUser = userRepository.findByUserType(UserType.VIP).firstOrNull()

        assertThat(foundUser).isEqualTo(vipUser)
    }

    @Test
    fun `should update user by id`() {
        val vipUser = userMockGenerator.createUser(UserType.VIP)
        val regularUser = userMockGenerator.createUser(UserType.REGULAR)
        val adminUser = userMockGenerator.createUser(UserType.ADMIN)

        creator.save(vipUser)
        creator.save(regularUser)
        creator.save(adminUser)

        val updatedUser = userRepository.findByUsername(vipUser.username)
        updatedUser?.userType = UserType.ADMIN
        creator.save(updatedUser ?: throw IllegalStateException("User not found"))

        val checkUser = userRepository.findByUsername(vipUser.username)

        assertThat(checkUser?.userType).isEqualTo(UserType.ADMIN)
    }

    @Test
    fun `should delete all users`() {
        val vipUser = userMockGenerator.createUser(UserType.VIP)
        val regularUser = userMockGenerator.createUser(UserType.REGULAR)
        val adminUser = userMockGenerator.createUser(UserType.ADMIN)

        creator.save(vipUser)
        creator.save(regularUser)
        creator.save(adminUser)

        userRepository.deleteAll()

        assertThat(userRepository.findAll()).isEmpty()
    }

}
