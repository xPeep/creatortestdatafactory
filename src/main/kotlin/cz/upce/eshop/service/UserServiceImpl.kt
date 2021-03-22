package cz.upce.eshop.service

import cz.upce.eshop.dto.AddOrEditPhotoDto
import cz.upce.eshop.dto.AddOrEditUserDto
import cz.upce.eshop.dto.AddOrEditVideoDto
import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserInformation
import cz.upce.eshop.entity.UserPhoto
import cz.upce.eshop.entity.UserVideo
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
    val photoRepository: PhotoRepository<Long>,
    val videoRepository: VideoRepository<Long>,
    val userRepository: UserRepository<Long>,
    val photosServiceImpl: UserPhotosServiceImpl,
    val videosServiceImpl: UserVideosServiceImpl
) : UserService {

    override fun addUser(user: AddOrEditUserDto): Long? {
        return userRepository.save(
            AddOrEditUserDto.createUser(
                userRepository.findByIdOrNull(user.id ?: -1), user
            )
        ).id
    }

    override fun removeUser(userId: Long) {
        userRepository.deleteById(userId)
    }

    override fun getUser(userId: Long): User? {
        return userRepository.findByIdOrNull(userId)
    }

    override fun modifyUserInformation(user: AddOrEditUserDto) {
        val foundUser = userRepository.findByIdOrNull(user.id) ?: return
        foundUser.userInformation = UserInformation(user.firstName, user.lastName, user.phoneNumber)
        userRepository.save(foundUser)
    }

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun addPhoto(userId: Long, userPhoto: AddOrEditPhotoDto) {
        val user = getUser(userId) ?: return
        user.photos.add(UserPhoto(userPhoto.name, userPhoto.link, LocalDateTime.now()))
        userRepository.save(user)
    }

    override fun addOrEditProfilePhoto(userId: Long, userPhoto: AddOrEditPhotoDto) {
        val user = getUser(userId) ?: return
        user.profilePhoto = UserPhoto(userPhoto.name, userPhoto.link, LocalDateTime.now())
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

    override fun addVideo(userId: Long, userVideo: AddOrEditVideoDto) {
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