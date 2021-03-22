package cz.upce.eshop.service

import cz.upce.eshop.entity.UserPhoto
import cz.upce.eshop.repository.PhotoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.context.annotation.SessionScope

@Service
class UserPhotosServiceImpl
@Autowired constructor(
    val photoRepository: PhotoRepository<Long>
) : UserPhotosService {

    override fun add(userPhoto: UserPhoto): Long {
        return photoRepository.save(userPhoto).id ?: throw NoSuchElementException()
    }

    override fun remove(id: Long) {
        photoRepository.deleteById(id)
    }
}