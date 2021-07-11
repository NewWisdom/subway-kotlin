package wooteco.subway.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import wooteco.subway.member.domain.Member

interface MemberRepository : JpaRepository<Member, Long> {
    fun findMemberByEmail(email: String): Member?
    fun findMemberById(id: Long): Member?
}
