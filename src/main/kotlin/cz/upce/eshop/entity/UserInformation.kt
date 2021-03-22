package cz.upce.eshop.entity

import cz.upce.eshop.repository.AbstractJpaPersistable
import javax.persistence.*

@Entity(name = "USER_INFORMATION")
class UserInformation(
    @Column(length = 500) var firstName: String = "",
    @Column(length = 500) var lastName: String = "",
    @Column(length = 50) var phoneNumber: String = ""
) : AbstractJpaPersistable<Long>()