package wooteco.subway.member.ui

import wooteco.subway.member.domain.Member
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class MemberRequest(
    @field:NotBlank(message = "email은 필수로 입력하여야 합니다.")
    @field:Email(message = "email의 형식을 지켜야 합니다.")
    var email: String,
    @field:NotBlank(message = "password는 필수로 입력하여야 합니다.")
    var password: String,
    var age: Int? = null
) {
    fun toEntity(): Member {
        return Member(email = email, password = password, age = age)
    }
}

class MemberResponse(
    val id: Long,
    val email: String,
    val age: Int?
) {
    companion object {
        fun of(member: Member): MemberResponse {
            return MemberResponse(member.id, member.email, member.age)
        }
    }
}