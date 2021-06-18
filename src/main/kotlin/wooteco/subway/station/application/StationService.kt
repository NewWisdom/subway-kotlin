package wooteco.subway.station.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wooteco.subway.station.dto.StationDto
import wooteco.subway.station.repository.StationRepository

@Service
class StationService(private val stationRepository: StationRepository) {
    @Transactional
    fun save(stationRequest: StationDto): StationDto {
        val saveStation = stationRepository.save(stationRequest.toEntity())
        return StationDto.of(saveStation)
    }

    fun findAll(): List<StationDto> {
        val stations = stationRepository.findAll()
        return StationDto.listOf(stations)
    }

    @Transactional
    fun delete(id: Long) {
        stationRepository.deleteById(id)
    }
}