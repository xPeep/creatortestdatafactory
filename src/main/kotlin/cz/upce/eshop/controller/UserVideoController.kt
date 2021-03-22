package cz.upce.eshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class UserVideoController {

    @GetMapping("user-video-add/{idUser}/{idVideo}")
    fun userVideoAdd(@PathVariable idUser: Long, @PathVariable idVideo: Long, model: Model): String {
        return ""
    }

    @GetMapping("user-video-remove/{idUser}/{idVideo}")
    fun userVideoRemove(@PathVariable idUser: Long, @PathVariable idVideo: Long, model: Model): String {
        return ""
    }

    @GetMapping("user-videos/{idUser}")
    fun userVideos(@PathVariable idUser: Long, model: Model): String {
        return ""
    }

    @GetMapping("user-videos-remove-all/{idUser}")
    fun userVideosRemoveAll(@PathVariable idUser: Long, model: Model): String {
        return ""
    }

}