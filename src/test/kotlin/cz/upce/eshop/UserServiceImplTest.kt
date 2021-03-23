package cz.upce.eshop

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
        userServiceImpl.addUser(user)
        val foundUser = userRepository.findByIdOrNull(user.id)

        assertThat(user).isEqualTo(foundUser)
    }

    @Test
    fun removeUser() {
        val user = userMockGenerator.createUser()
        userServiceImpl.addUser(user)
        userServiceImpl.removeUser(user.id ?: -1)
        val removedUser = userRepository.findByIdOrNull(user.id)

        assertThat(removedUser).isEqualTo(null)
    }

    @Test
    fun getUser() {
        val user = userMockGenerator.createUser()
        userServiceImpl.addUser(user)
        val foundUser = userServiceImpl.getUser(user.id ?: -1)

        assertThat(user).isEqualTo(foundUser)
    }

    @Test
    fun modifyUserInformation() {
        val user = userMockGenerator.createUser()
        userServiceImpl.addUser(user)

        val newUserInformation = userMockGenerator.createUserInformation()
        userServiceImpl.modifyUserInformation(user.id ?: -1, newUserInformation)

        val modifiedUser = userServiceImpl.getUser(user.id ?: -1)
        user.userInformation = modifiedUser?.userInformation

        assertThat(user).isEqualTo(modifiedUser)
    }

    @Test
    fun getAllUsers() {
        for (x in 0 until 4) {
            val user = userMockGenerator.createUser()
            userServiceImpl.addUser(user)
        }

        val allUsers = userServiceImpl.getAllUsers()

        assertThat(allUsers.size).isEqualTo(4)
    }

    @Test
    fun addPhoto() {
        val user = userMockGenerator.createUser()
        user.photos = mutableSetOf()

        userServiceImpl.addUser(user)
        val userPhoto = userMockGenerator.createPhoto()

        userServiceImpl.addPhoto(user.id ?: -1, userPhoto)
        val foundPhoto = userServiceImpl.getAllPhoto(user.id ?: -1)

        val foundUser = userServiceImpl.getUser(user.id ?: -1)

        assertThat(foundUser?.photos?.firstOrNull()).isEqualTo(foundPhoto.firstOrNull())
    }

    @Test
    fun addOrEditProfilePhoto() {
        val user = userMockGenerator.createUser()
        userServiceImpl.addUser(user)

        val userPhoto = userMockGenerator.createPhoto()
        userServiceImpl.addOrEditProfilePhoto(user.id ?: -1, userPhoto)

        val foundUser = userServiceImpl.getUser(user.id ?: -1)
        userPhoto.id = foundUser?.profilePhoto?.id

        assertThat(userPhoto).isEqualTo(foundUser?.profilePhoto)
    }

    @Test
    fun removeProfilePhoto() {
        val user = userMockGenerator.createUser()
        userServiceImpl.addUser(user)

        val userPhoto = userMockGenerator.createPhoto()
        userServiceImpl.addOrEditProfilePhoto(user.id ?: -1, userPhoto)
        userServiceImpl.removeProfilePhoto(user.id ?: -1)

        val foundUser = userServiceImpl.getUser(user.id ?: -1)

        assertThat(foundUser?.profilePhoto).isEqualTo(null)
    }

    @Test
    fun removePhoto() {
        val user = userMockGenerator.createUser()
        user.photos = mutableSetOf()

        userServiceImpl.addUser(user)
        val userPhoto = userMockGenerator.createPhoto()

        userServiceImpl.addPhoto(user.id ?: -1, userPhoto)
        val foundPhoto = userServiceImpl.getAllPhoto(user.id ?: -1)

        userServiceImpl.removePhoto(user.id ?: -1, foundPhoto.firstOrNull()?.id ?: -1)
        val foundUser = userServiceImpl.getUser(user.id ?: -1)

        assertThat(foundUser?.photos?.firstOrNull()).isEqualTo(null)
    }

    @Test
    fun getAllPhoto() {
        val user = userMockGenerator.createUser()
        user.photos = mutableSetOf()

        userServiceImpl.addUser(user)

        for (x in 0 until 4) {
            val userPhoto = userMockGenerator.createPhoto()
            userServiceImpl.addPhoto(user.id ?: -1, userPhoto)
        }

        val foundPhotos = userServiceImpl.getAllPhoto(user.id ?: -1)

        assertThat(foundPhotos.size).isEqualTo(4)
    }

    @Test
    fun addVideo() {
        val user = userMockGenerator.createUser()
        user.videos = mutableSetOf()

        userServiceImpl.addUser(user)
        val userVideo = userMockGenerator.createVideo()

        userServiceImpl.addVideo(user.id ?: -1, userVideo)
        val foundVideo = userServiceImpl.getAllVideos(user.id ?: -1)

        val foundUser = userServiceImpl.getUser(user.id ?: -1)

        assertThat(foundUser?.videos?.firstOrNull()).isEqualTo(foundVideo.firstOrNull())
    }

    @Test
    fun getAllVideos() {
        val user = userMockGenerator.createUser()
        user.videos = mutableSetOf()

        userServiceImpl.addUser(user)

        for (x in 0 until 4) {
            val userVideo = userMockGenerator.createVideo()
            userServiceImpl.addVideo(user.id ?: -1, userVideo)
        }

        val foundVideos = userServiceImpl.getAllVideos(user.id ?: -1)

        assertThat(foundVideos.size).isEqualTo(4)
    }

    @Test
    fun removeVideo() {
        val user = userMockGenerator.createUser()
        user.videos = mutableSetOf()

        userServiceImpl.addUser(user)
        val userVideo = userMockGenerator.createVideo()

        userServiceImpl.addVideo(user.id ?: -1, userVideo)
        val foundPhoto = userServiceImpl.getAllVideos(user.id ?: -1)

        userServiceImpl.removeVideo(user.id ?: -1, foundPhoto.firstOrNull()?.id ?: -1)
        val foundUser = userServiceImpl.getUser(user.id ?: -1)

        assertThat(foundUser?.videos?.firstOrNull()).isEqualTo(null)
    }

}