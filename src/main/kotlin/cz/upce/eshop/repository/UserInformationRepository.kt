package cz.upce.eshop.repository

import cz.upce.eshop.entity.UserInformation
import org.springframework.data.jpa.repository.JpaRepository

interface UserInformationRepository<T> : JpaRepository<UserInformation, T>
