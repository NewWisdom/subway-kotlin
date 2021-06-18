package wooteco.subway.line.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wooteco.subway.line.domain.Section
import wooteco.subway.line.dto.LineRequest
import wooteco.subway.line.dto.LineResponse
import wooteco.subway.line.dto.SectionRequest
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
    @Transactional
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

    private fun checkExistInfo(lineRequest: LineRequest) {
        if (lineRepository.findLineByName(lineRequest.name) != null) {
            throw ExistLineNameException()
        }
        if (lineRepository.findLineByColor(lineRequest.color) != null) {
            throw ExistLineColorException()
        }
    }

    private fun findStationByStationId(stationId: Long) =
        (stationRepository.findStationById(stationId)
            ?: throw StationNotExistException())

    fun findAll(): List<LineResponse> {
        return LineResponse.listOf(lineRepository.findAll())
    }

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
        val upStation = findStationByStationId(sectionRequest.upStationId)
        val downStation = findStationByStationId(sectionRequest.downStationId)
        line.addSection(upStation, downStation, sectionRequest.distance)
        lineRepository.save(line)
    }
}
