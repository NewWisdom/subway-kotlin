package wooteco.subway.line.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import wooteco.subway.line.application.LineService
import wooteco.subway.line.dto.LineRequest
import wooteco.subway.line.dto.LineResponse
import java.net.URI

@RestController
@RequestMapping("/lines")
class LineController(private val lineService: LineService) {

    @PostMapping
    fun saveLine(@RequestBody lineRequest: LineRequest): ResponseEntity<LineResponse> {
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
    fun updateLine(@PathVariable id: Long, @RequestBody lineRequest: LineRequest) {
    }

    @DeleteMapping("/{id:[\\d]+}")
    fun deleteLine(@PathVariable id: Long) {

    }
}