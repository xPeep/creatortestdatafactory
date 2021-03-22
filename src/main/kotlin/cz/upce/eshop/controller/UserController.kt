package cz.upce.eshop.controller

import cz.upce.eshop.dto.AddOrEditUserDto
import cz.upce.eshop.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController
@Autowired constructor(
    val userServiceImpl : UserServiceImpl
) {

    @ExceptionHandler(RuntimeException::class)
    fun handleException(): String = "error"

    @GetMapping("/user-detail/{userId}")
    fun showUserDetail(@PathVariable userId: Long?, model: Model): String {
        model.addAttribute(
            "user",
            userServiceImpl.getUser(userId ?: -1) ?: return "error"
        )
        return "user-detail"
    }

    @GetMapping("/")
    fun showAllUsers(model: Model): String {
        model.addAttribute("userList", userServiceImpl.getAllUsers())
        return "user-list"
    }

    @GetMapping("/user-form", "/user-form/{id}")
    fun showUserForm(@PathVariable id: Long?, model: Model): String {
        model.addAttribute(
            "user", AddOrEditUserDto.createUserDto(userServiceImpl.getUser(id ?: -1))
        )
        return "user-form"
    }

    @PostMapping("/addUser")
    fun addUserProcess(addOrEditUserDto: AddOrEditUserDto, model: Model): String {
        userServiceImpl.addUser(addOrEditUserDto)
        return "redirect:/"
    }

}