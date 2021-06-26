package wooteco.subway.station.dto

import wooteco.subway.station.domain.Station
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

class StationDto(
    val id: Long? = null,
    @field:NotBlank(message = "name은 필수로 입력하여야 합니다.")
    @field:Pattern(regexp = "^[가-힣0-9]{2,10}$", message = "노선 이름은 2~20자 이하의 한글/숫자만 가능합니다")
    val name: String
) {
    fun toEntity(): Station {
        return Station(name = name)
    }

    companion object {
        fun of(station: Station): StationDto {
            return StationDto(station.id, station.name)
        }

        fun listOf(stations: List<Station>): List<StationDto> {
            return stations.map { of(it) }
        }
    }
}
