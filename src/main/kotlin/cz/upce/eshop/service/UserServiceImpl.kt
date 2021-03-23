package cz.upce.eshop.service

import cz.upce.eshop.dto.AddOrEditPhotoDto
import cz.upce.eshop.dto.AddOrEditUserDto
import cz.upce.eshop.dto.AddOrEditVideoDto
import cz.upce.eshop.entity.*
import cz.upce.eshop.repository.PhotoRepository
import cz.upce.eshop.repository.UserRepository
import cz.upce.eshop.repository.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserServiceImpl
@Autowired constructor(
    val userRepository: UserRepository<Long>
) : UserService {

    override fun addUser(user: User) {
        userRepository.save(user)
    }

    override fun removeUser(userId: Long) {
        userRepository.deleteById(userId)
    }

    override fun getUser(userId: Long): User? {
        return userRepository.findByIdOrNull(userId)
    }

    override fun modifyUserInformation(userId: Long, userInformation: UserInformation) {
        val foundUser = userRepository.findByIdOrNull(userId) ?: return
        foundUser.userInformation = userInformation
        userRepository.save(foundUser)
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun addPhoto(userId: Long, userPhoto: UserPhoto) {
        val user = getUser(userId) ?: return
        user.photos.add(UserPhoto(userPhoto.name, userPhoto.link, userPhoto.dateTime))
        userRepository.save(user)
    }

    override fun addOrEditProfilePhoto(userId: Long, userPhoto: UserPhoto) {
        val user = getUser(userId) ?: return
        user.profilePhoto = UserPhoto(userPhoto.name, userPhoto.link, userPhoto.dateTime)
        userRepository.save(user)
    }

    override fun removeProfilePhoto(userId: Long) {
        val user = getUser(userId) ?: return
        user.profilePhoto = null
        userRepository.save(user)
    }

    override fun removePhoto(userId: Long, photoId: Long) {
        val user = getUser(userId) ?: return
        user.photos.removeIf { it.id == photoId }
        userRepository.save(user)
    }

    override fun getAllPhoto(userId: Long): List<UserPhoto> {
        val user = getUser(userId) ?: return listOf()
        return user.photos.toMutableList()
    }

    override fun addVideo(userId: Long, userVideo: UserVideo) {
        val user = getUser(userId) ?: return
        user.videos.add(UserVideo(userVideo.name, userVideo.link, LocalDateTime.now()))
        userRepository.save(user)
    }

    override fun getAllVideos(userId: Long): List<UserVideo> {
        return getUser(userId)?.videos?.toMutableList() ?: mutableListOf()
    }

    override fun removeVideo(userId: Long, videoId: Long) {
        val user = getUser(userId) ?: return
        user.videos.removeIf { it.id == videoId }
        userRepository.save(user)
    }

}