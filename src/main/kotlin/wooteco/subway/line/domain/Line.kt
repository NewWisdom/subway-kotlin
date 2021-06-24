package wooteco.subway.line.domain

import wooteco.subway.line.dto.LineRequest
import wooteco.subway.station.domain.Station
import javax.persistence.*

@Entity
class Line(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    @Column(unique = true, nullable = false)
    var name: String,
    @Column(unique = true, nullable = false)
    var color: String,
    var extraFare: Int? = null,

    @Embedded
    val sections: Sections = Sections()
) {
    fun sortedStations(): List<Station> {
        return sections.sortedStations()
    }

    fun update(lineRequest: LineRequest) {
        this.name = lineRequest.name
        this.color = lineRequest.color
    }

    fun addSection(upStation: Station, downStation: Station, distance: Int) {
        val section = Section(upStation = upStation, downStation = downStation, distance = distance)
        sections.addSection(section)
    }

    fun removeSectionByStation(station: Station) {
        sections.removeStation(station)
    }
}