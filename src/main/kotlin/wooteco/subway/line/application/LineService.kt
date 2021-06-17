package wooteco.subway.line.application

import org.springframework.stereotype.Service
import wooteco.subway.line.domain.Section
import wooteco.subway.line.dto.LineRequest
import wooteco.subway.line.dto.LineResponse
import wooteco.subway.line.repository.LineRepository
import wooteco.subway.line.repository.SectionRepository
import wooteco.subway.station.application.StationNotExistException
import wooteco.subway.station.repository.StationRepository

@Service
class LineService(
    val lineRepository: LineRepository,
    val stationRepository: StationRepository,
    val sectionRepository: SectionRepository
) {
    fun save(lineRequest: LineRequest): LineResponse {
        checkExistInfo(lineRequest)
        val upStation = findStationByStationId(lineRequest.upStationId)
        val downStation = findStationByStationId(lineRequest.downStationId)
        val savedLine = lineRepository.save(lineRequest.toEntity())
        val section = Section(
            upStation = upStation,
            downStation = downStation,
            distance = lineRequest.distance,
        )
        section.changeLine(savedLine)
        sectionRepository.save(section)
        return LineResponse.of(savedLine)
    }

    private fun findStationByStationId(stationId: Long) =
        (stationRepository.findStationById(stationId)
            ?: throw StationNotExistException())

    private fun checkExistInfo(lineRequest: LineRequest) {
        if (lineRepository.findLineByName(lineRequest.name) != null) {
            throw ExistLineNameException()
        }
        if (lineRepository.findLineByColor(lineRequest.color) != null) {
            throw ExistLineColorException()
        }
    }

    fun findAll(): List<LineResponse> {
        return LineResponse.listOf(lineRepository.findAll())
    }

    fun findById(id: Long): LineResponse {
        val line = lineRepository.findLineById(id) ?: throw LineNotExistException()
        return LineResponse.of(line)
    }

    fun update(id: Long, lineRequest: LineRequest) {
        val updateLine = lineRepository.findLineById(id) ?: throw LineNotExistException()
        updateLine.update(lineRequest)
        lineRepository.save(updateLine)
    }
}
