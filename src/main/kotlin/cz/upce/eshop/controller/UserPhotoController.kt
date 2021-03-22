package cz.upce.eshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class UserPhotoController {

    @GetMapping("user-photo-add/{idUser}/{idPhoto}")
    fun userPhotoAdd(@PathVariable idUser: Long, @PathVariable idPhoto: Long, model: Model): String {
        return "redirect:/user-photos"
    }

    @GetMapping("user-photo-remove/{idUser}/{idPhoto}")
    fun userPhotoRemove(@PathVariable idUser: Long, @PathVariable idPhoto: Long, model: Model): String {
        return ""
    }

    @GetMapping("user-photo/{idUser}")
    fun userPhotos(@PathVariable idUser: Long, model: Model): String {
        return ""
    }

    @GetMapping("user-photos-remove-all/{idUser}")
    fun userPhotosRemoveAll(@PathVariable idUser: Long, model: Model): String {
        return ""
    }
}