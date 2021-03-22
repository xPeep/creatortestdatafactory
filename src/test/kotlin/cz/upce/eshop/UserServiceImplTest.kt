package cz.upce.eshop

import cz.upce.eshop.dto.AddOrEditUserDto
import cz.upce.eshop.repository.UserRepository
import cz.upce.eshop.service.UserServiceImpl
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
@ComponentScan
class UserServiceImplTest
@Autowired constructor(
    val userRepository: UserRepository<Long>,
    val userMockGenerator: UserMockGenerator,
    val userServiceImpl: UserServiceImpl
) {

    @Test
    fun addUser() {
        val user = userMockGenerator.createUser()
        val userId = userServiceImpl.addUser(AddOrEditUserDto.createUserDto(user))
        val foundUser = userRepository.findByIdOrNull(userId)

        assertThat(foundUser == user)
    }

    @Test
    fun removeUser() {
    }

    @Test
    fun getUser() {
    }

    @Test
    fun modifyUserInformation() {
    }

    @Test
    fun getAllUsers() {
    }

    @Test
    fun addPhoto() {
    }

    @Test
    fun addOrEditProfilePhoto() {
    }

    @Test
    fun removeProfilePhoto() {
    }

    @Test
    fun removePhoto() {
    }

    @Test
    fun getAllPhoto() {
    }

    @Test
    fun addVideo() {
    }

    @Test
    fun getAllVideos() {
    }

    @Test
    fun removeVideo() {
    }

    @Test
    fun getPhotoRepository() {
    }

    @Test
    fun getVideoRepository() {
    }

    @Test
    fun getUserRepository() {
    }

    @Test
    fun getPhotosServiceImpl() {
    }

    @Test
    fun getVideosServiceImpl() {
    }
}