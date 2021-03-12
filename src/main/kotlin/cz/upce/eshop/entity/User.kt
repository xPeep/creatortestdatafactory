package cz.upce.eshop.entity

import cz.upce.eshop.repository.AbstractJpaPersistable
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "User")
class User(
    @Column(length = 500) var username: String,
    @Column(length = 500) var password: String,
    @OneToOne(cascade = [CascadeType.ALL]) var userInformation: UserInformation,
    @OneToOne(cascade = [CascadeType.ALL]) var profilePhoto: Photo,
    @Enumerated(EnumType.STRING) @Column(length = 20) var userType: UserType,
    @OneToMany(mappedBy = "id", cascade = [CascadeType.ALL], fetch = FetchType.LAZY) var photos: MutableSet<Photo>
) : AbstractJpaPersistable<Long>() {

    companion object {
        fun createUser(
            username: String, password: String, firstName: String?,
            lastName: String?, phoneNumber: String?, photoName: String?,
            link: String?, userType: UserType
        ): User {
            val photo = Photo(photoName ?: "", link ?: "", LocalDateTime.now(), mutableSetOf())
            val userInformation = UserInformation(firstName ?: "", lastName ?: "", phoneNumber ?: "")
            return User(username, password, userInformation, photo, userType, mutableSetOf())
        }
    }
}