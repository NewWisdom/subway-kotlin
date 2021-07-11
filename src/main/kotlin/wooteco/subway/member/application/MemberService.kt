package wooteco.subway.member.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wooteco.subway.member.repository.MemberRepository
import wooteco.subway.member.ui.MemberRequest
import wooteco.subway.member.ui.MemberResponse

@Service
class MemberService(private val memberRepository: MemberRepository) {

    @Transactional
    fun createMember(memberRequest: MemberRequest): MemberResponse {
        if (memberRepository.findMemberByEmail(memberRequest.email) != null) {
            throw DuplicateEmailException();
        }
        val saveMember = memberRepository.save(memberRequest.toEntity())
        return MemberResponse.of(saveMember)
    }

    fun findMember(id: Long): MemberResponse {
        val member = memberRepository.findMemberById(id) ?: throw EmailNotFoundException()
        return MemberResponse.of(member)
    }

    @Transactional
    fun updateMember(id: Long, memberRequest: MemberRequest): MemberResponse {
        val findMember = memberRepository.findMemberById(id) ?: throw EmailNotFoundException()
        findMember.update(memberRequest)
        return MemberResponse.of(memberRepository.save(findMember))
    }

    @Transactional
    fun deleteMember(id: Long) {
        memberRepository.findMemberById(id) ?: throw MemberNotFoundException()
        memberRepository.deleteById(id)
    }
}
