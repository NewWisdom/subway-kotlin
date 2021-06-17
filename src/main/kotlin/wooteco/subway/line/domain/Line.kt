package wooteco.subway.line.domain

import wooteco.subway.station.domain.Station
import javax.persistence.*

@Entity
class Line(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    @Column(unique = true, nullable = false)
    val name: String,
    @Column(unique = true, nullable = false)
    val color: String,

    val extraFare: Int? = null,

    @Embedded
    val sections: Sections = Sections()
) {
    fun sortedStations(): List<Station> {
        return sections.sortedStations()
    }
}