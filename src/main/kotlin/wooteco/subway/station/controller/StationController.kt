package wooteco.subway.station.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import wooteco.subway.station.application.StationService
import wooteco.subway.station.dto.StationDto
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/stations")
class StationController(private val stationService: StationService) {
    @PostMapping
    fun createStations(@Valid @RequestBody stationRequest: StationDto): ResponseEntity<StationDto> {
        val stationDto = stationService.save(stationRequest)
        return ResponseEntity.created(URI.create("/stations/${stationDto.id}")).body(stationDto)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllStations(): ResponseEntity<List<StationDto>> {
        return ResponseEntity.ok().body(stationService.findAll())
    }

    @DeleteMapping("/{id:[\\d]+}")
    fun deleteStation(@PathVariable id: Long): ResponseEntity<Void> {
        stationService.delete(id)
        return ResponseEntity.noContent().build()
    }
}