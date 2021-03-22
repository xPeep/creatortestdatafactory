package cz.upce.eshop.service

import cz.upce.eshop.dto.AddOrEditPhotoDto
import cz.upce.eshop.dto.AddOrEditUserDto
import cz.upce.eshop.dto.AddOrEditVideoDto
import cz.upce.eshop.entity.User
import cz.upce.eshop.entity.UserInformation
import cz.upce.eshop.entity.UserPhoto
import cz.upce.eshop.entity.UserVideo

interface UserService {
    fun addUser(user: AddOrEditUserDto): Long?
    fun removeUser(userId: Long)
    fun getUser(userId: Long): User?
    fun modifyUserInformation(user: AddOrEditUserDto)
    fun getAllUsers(): List<User>

    fun addPhoto(userId: Long, userPhoto: AddOrEditPhotoDto)
    fun addOrEditProfilePhoto(userId: Long, userPhoto: AddOrEditPhotoDto)
    fun removeProfilePhoto(userId: Long)
    fun removePhoto(userId: Long, photoId: Long)
    fun getAllPhoto(userId: Long): List<UserPhoto>

    fun addVideo(userId: Long, userVideo: AddOrEditVideoDto)
    fun getAllVideos(userId: Long): List<UserVideo>
    fun removeVideo(userId: Long, videoId: Long)
}
