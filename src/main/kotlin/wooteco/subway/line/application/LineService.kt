package wooteco.subway.line.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wooteco.subway.line.domain.Section
import wooteco.subway.line.dto.LineRequest
import wooteco.subway.line.dto.LineResponse
import wooteco.subway.line.dto.SectionRequest
import wooteco.subway.line.repository.LineRepository
import wooteco.subway.line.repository.SectionRepository
import wooteco.subway.station.application.StationService

@Service
class LineService(
    val lineRepository: LineRepository,
    val sectionRepository: SectionRepository,
    val stationService: StationService
) {
    @Transactional
    fun save(lineRequest: LineRequest): LineResponse {
        checkExistInfo(lineRequest)
        val savedLine = lineRepository.save(lineRequest.toEntity())
        val section = Section(
            upStation = stationService.findById(lineRequest.upStationId),
            downStation = stationService.findById(lineRequest.downStationId),
            distance = lineRequest.distance,
        )
        section.changeLine(savedLine)
        sectionRepository.save(section)
        return LineResponse.of(savedLine)
    }

    private fun checkExistInfo(lineRequest: LineRequest) {
        if (lineRepository.findLineByName(lineRequest.name) != null) {
            throw ExistLineNameException()
        }
        if (lineRepository.findLineByColor(lineRequest.color) != null) {
            throw ExistLineColorException()
        }
    }

    @Transactional(readOnly = true)
    fun findAll(): List<LineResponse> {
        return LineResponse.listOf(lineRepository.findAll())
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): LineResponse {
        val line = lineRepository.findLineById(id) ?: throw LineNotExistException()
        return LineResponse.of(line)
    }

    @Transactional
    fun updateLine(id: Long, lineRequest: LineRequest) {
        val updateLine = lineRepository.findLineById(id) ?: throw LineNotExistException()
        updateLine.update(lineRequest)
        lineRepository.save(updateLine)
    }

    @Transactional
    fun deleteLineById(id: Long) {
        lineRepository.deleteById(id)
    }

    @Transactional
    fun addSection(lineId: Long, sectionRequest: SectionRequest) {
        val line = lineRepository.findLineById(lineId) ?: throw LineNotExistException()
        val upStation = stationService.findById(sectionRequest.upStationId)
        val downStation = stationService.findById(sectionRequest.downStationId)
        line.addSection(upStation, downStation, sectionRequest.distance)
        lineRepository.save(line)
    }

    @Transactional
    fun deleteSection(lineId: Long, stationId: Long) {
        val line = lineRepository.findLineById(lineId) ?: throw LineNotExistException()
        val station = stationService.findById(stationId)
        line.removeSectionByStation(station)
    }
}
