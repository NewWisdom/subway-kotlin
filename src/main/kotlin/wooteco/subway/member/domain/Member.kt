package wooteco.subway.member.domain

import wooteco.subway.member.ui.MemberRequest
import javax.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    @Column(unique = true, nullable = false)
    var email: String,
    @Column(unique = true, nullable = false)
    var password: String,
    var age: Int? = null,
) {
    fun update(memberRequest: MemberRequest) {
        age = memberRequest.age
        email = memberRequest.email
        password = memberRequest.password
    }

}
