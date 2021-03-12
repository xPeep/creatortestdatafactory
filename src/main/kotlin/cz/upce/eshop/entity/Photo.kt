package cz.upce.eshop.entity

import cz.upce.eshop.repository.AbstractJpaPersistable
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "Photo")
class Photo(
    @Column var name: String,
    @Column var link: String,
    @Column(columnDefinition = "TIMESTAMP") var dateTime: LocalDateTime,
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY) var users: MutableSet<User>
) : AbstractJpaPersistable<Long>()