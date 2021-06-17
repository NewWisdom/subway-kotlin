package wooteco.subway.line.dto

import wooteco.subway.line.domain.Line
import wooteco.subway.line.domain.Section
import wooteco.subway.station.application.StationResponse
import java.util.stream.Collectors
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

data class LineRequest(
    @NotBlank(message = "name은 필수로 입력하여야 합니다.")
    @Pattern(regexp = "^[가-힣0-9]{2,10}$", message = "노선 이름은 2~20자 이하의 한글/숫자만 가능합니다")
    var name: String,

    @NotBlank(message = "color은 필수로 입력하여야 합니다.")
    var color: String,

    @NotNull(message = "상행역 ID는 필수로 입력하여야 합니다.")
    var upStationId: Long,

    @NotNull(message = "하행역 ID는 필수로 입력하여야 합니다.")
    var downStationId: Long,

    @NotNull(message = "거리 값은 필수로 입력하여야 합니다.")
    @Positive(message = "거리 값은 0보다 커야 합니다.")
    var distance: Int,

    var extraFare: Int? = null
) {
    fun toEntity(): Line {
        return Line(name = name, color = color, extraFare = extraFare)
    }
}

data class LineResponse(
    var id: Long,
    var name: String,
    var color: String,
    var stations: List<StationResponse>,
    var sections: List<SectionResponse>
) {
    companion object {
        fun of(line: Line): LineResponse {
            val stations = StationResponse.listOf(line.sortedStations())
            val sections = SectionResponse.listOf(line.sections.sections)
            return LineResponse(line.id, line.name, line.color, stations, sections)
        }

        fun listOf(lines: List<Line>): List<LineResponse> {
            return lines.map{of(it)}
        }

    }
}

data class SectionResponse(
    var upStation: StationResponse,
    var downStation: StationResponse,
    val distance: Int = 0
) {
    companion object {
        fun from(section: Section): SectionResponse {
            return SectionResponse(
                StationResponse.from(section.upStation),
                StationResponse.from(section.downStation),
                section.distance
            )
        }

        fun listOf(sectionsAsList: List<Section>): List<SectionResponse> {
            return sectionsAsList.map { from(it) }
        }
    }
}