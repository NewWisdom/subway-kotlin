package wooteco.subway.line.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import wooteco.subway.line.application.LineService
import wooteco.subway.line.dto.LineRequest
import wooteco.subway.line.dto.LineResponse
import wooteco.subway.line.dto.SectionRequest
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/lines")
class LineController(private val lineService: LineService) {

    @PostMapping
    fun saveLine(@Valid @RequestBody lineRequest: LineRequest): ResponseEntity<LineResponse> {
        val lineResponse = lineService.save(lineRequest)
        return ResponseEntity.created(URI.create("/lines/${lineResponse.id}"))
            .body(lineResponse)
    }

    @GetMapping
    fun findAllLines(): ResponseEntity<List<LineResponse>> {
        return ResponseEntity.ok(lineService.findAll())
    }

    @GetMapping("/{id:[\\d]+}")
    fun findLineById(@PathVariable id: Long): ResponseEntity<LineResponse> {
        return ResponseEntity.ok(lineService.findById(id))
    }

    @PutMapping("/{id:[\\d]+}")
    fun updateLine(
        @PathVariable id: Long,
        @Valid @RequestBody lineRequest: LineRequest
    ): ResponseEntity<Void> {
        lineService.updateLine(id, lineRequest)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id:[\\d]+}")
    fun deleteLine(@PathVariable id: Long): ResponseEntity<Void> {
        lineService.deleteLineById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{lineId:[\\d]+}/sections")
    fun addSection(
        @PathVariable lineId: Long,
        @Valid @RequestBody sectionRequest: SectionRequest
    ): ResponseEntity<Void> {
        lineService.addSection(lineId, sectionRequest)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{lineId}/sections")
    fun deleteLineSection(
        @PathVariable lineId: Long,
        @RequestParam stationId: Long
    ): ResponseEntity<Void> {
        lineService.deleteSection(lineId, stationId)
        return ResponseEntity.ok().build()
    }
}