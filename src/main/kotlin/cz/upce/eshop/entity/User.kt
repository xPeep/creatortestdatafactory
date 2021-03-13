package cz.upce.eshop.entity

import cz.upce.eshop.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "USER")
class User(
    @Column(length = 500) var username: String,
    @Column(length = 500) var password: String,
    @Enumerated(EnumType.STRING) @Column(length = 20) var userType: UserType,
    @OneToOne(cascade = [CascadeType.ALL]) var userInformation: UserInformation,
    @OneToOne(cascade = [CascadeType.ALL]) var profilePhoto: UserPhoto,
    @OneToMany(mappedBy = "id", cascade = [CascadeType.ALL], fetch = FetchType.LAZY) var videos: MutableSet<UserVideo>,
    @OneToMany(mappedBy = "id", cascade = [CascadeType.ALL], fetch = FetchType.LAZY) var photos: MutableSet<UserPhoto>
) : AbstractJpaPersistable<Long>()