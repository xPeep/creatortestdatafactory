package cz.upce.eshop.entity

import cz.upce.eshop.dto.AddOrEditUserDto
import cz.upce.eshop.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "USER")
class User(
    @Column(length = 500) var username: String = "",
    @Column(length = 500) var password: String = "",
    @Enumerated(EnumType.STRING) @Column(length = 20) var userType: UserType = UserType.REGULAR,
    @OneToOne(cascade = [CascadeType.ALL]) var userInformation: UserInformation? = null,
    @OneToOne(cascade = [CascadeType.ALL]) var profilePhoto: UserPhoto? = null,
    @OneToMany(
        mappedBy = "id",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    ) var videos: MutableSet<UserVideo> = mutableSetOf(),
    @OneToMany(
        mappedBy = "id",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    ) var photos: MutableSet<UserPhoto> = mutableSetOf()
) : AbstractJpaPersistable<Long>() {

    fun createUserDto(): AddOrEditUserDto {
        return AddOrEditUserDto(
            id,
            username,
            password,
            userInformation?.firstName ?: "",
            userInformation?.lastName ?: "",
            userInformation?.phoneNumber ?: ""
        )
    }
}

