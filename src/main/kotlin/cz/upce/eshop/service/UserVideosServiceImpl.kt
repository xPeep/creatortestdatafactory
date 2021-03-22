package cz.upce.eshop.service

import cz.upce.eshop.entity.UserPhoto
import cz.upce.eshop.entity.UserVideo
import cz.upce.eshop.repository.PhotoRepository
import cz.upce.eshop.repository.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.context.annotation.SessionScope

@Service
class UserVideosServiceImpl
@Autowired constructor(
    val videoRepository: VideoRepository<Long>
) : UserVideosService {

    override fun add(userVideo: UserVideo): Long {
        return videoRepository.save(userVideo).id ?: throw NoSuchElementException()
    }

    override fun remove(id: Long) {
        videoRepository.deleteById(id)
    }
}