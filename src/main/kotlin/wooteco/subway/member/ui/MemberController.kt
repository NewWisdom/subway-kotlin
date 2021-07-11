package wooteco.subway.member.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import wooteco.subway.member.application.MemberService
import java.net.URI


@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {

    @PostMapping("/members")
    fun createMember(@RequestBody request: MemberRequest): ResponseEntity<Void> {
        val memberResponse: MemberResponse = memberService.createMember(request)
        return ResponseEntity.created(URI.create("/members/" + memberResponse.id)).build()
    }

    @GetMapping("/members/{id}")
    fun findMember(@PathVariable id: Long): ResponseEntity<MemberResponse> {
        val memberResponse: MemberResponse = memberService.findMember(id)
        return ResponseEntity.ok().body(memberResponse)
    }

    @PutMapping("/members/{id}")
    fun updateMember(
        @PathVariable id: Long,
        @RequestBody memberRequest: MemberRequest
    ): ResponseEntity<MemberResponse> {
        memberService.updateMember(id, memberRequest)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/members/{id}")
    fun deleteMember(@PathVariable id: Long): ResponseEntity<MemberResponse> {
        memberService.deleteMember(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/members/me")
    fun findMemberOfMine(): ResponseEntity<MemberResponse> {
        return ResponseEntity.ok().build()
    }

    // TODO: 구현 하기
    @PutMapping("/members/me")
    fun updateMemberOfMine(): ResponseEntity<MemberResponse?>? {
        return ResponseEntity.ok().build<MemberResponse?>()
    }

    // TODO: 구현 하기
    @DeleteMapping("/members/me")
    fun deleteMemberOfMine(): ResponseEntity<MemberResponse?>? {
        return ResponseEntity.noContent().build<MemberResponse?>()
    }
}