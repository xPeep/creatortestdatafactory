package cz.upce.eshop

import cz.upce.eshop.dto.AddOrEditUserDto
import cz.upce.eshop.entity.*
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserMockGenerator {

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    private fun generateName(size: Int): String {
        return (1..size)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("");
    }

    fun createPhoto(): UserPhoto {
        return UserPhoto(generateName(5), generateName(5), LocalDateTime.now())
    }

    fun createVideo(): UserVideo {
        return UserVideo(generateName(10), generateName(10), LocalDateTime.now())
    }

    fun createPhotos(): MutableSet<UserPhoto> {
        val videos = mutableSetOf<UserPhoto>()
        for (x in 0..10) {
            videos.add(UserPhoto(generateName(10), generateName(10), LocalDateTime.now()))
        }
        return videos
    }

    fun createVideos(): MutableSet<UserVideo> {
        val videos = mutableSetOf<UserVideo>()
        for (x in 0..10) {
            videos.add(UserVideo(generateName(10), generateName(10), LocalDateTime.now()))
        }
        return videos
    }

    fun createUserInformation(): UserInformation {
        return UserInformation(generateName(8), generateName(8), generateName(8))
    }

    fun createUser(): User {
        return User(
            generateName(4), generateName(4), UserType.values().first(),
            createUserInformation(), createPhoto(), createVideos(), createPhotos()
        )
    }

    fun createUser(userType: UserType): User {
        return User(
            generateName(4), generateName(4), userType,
            createUserInformation(), createPhoto(), createVideos(), createPhotos()
        )
    }
}

