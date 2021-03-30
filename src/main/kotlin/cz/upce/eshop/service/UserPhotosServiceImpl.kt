package cz.upce.eshop.service

import cz.upce.eshop.entity.UserPhoto
import cz.upce.eshop.repository.UserPhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserPhotosServiceImpl
@Autowired constructor(
    val userPhotoRepository: UserPhotoRepository<Long>
) : UserPhotosService {

    override fun add(userPhoto: UserPhoto): Long {
        return userPhotoRepository.save(userPhoto).id ?: throw NoSuchElementException()
    }

    override fun remove(id: Long) {
        userPhotoRepository.deleteById(id)
    }
}