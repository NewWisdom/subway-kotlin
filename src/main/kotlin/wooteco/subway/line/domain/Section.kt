package wooteco.subway.line.domain

import wooteco.subway.station.domain.Station
import javax.persistence.*

@Entity
class Section(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    @ManyToOne
    val upStation: Station,
    @ManyToOne
    val downStation: Station,
    @Column(nullable = false)
    val distance: Int,

    @ManyToOne
    var line: Line? = null
) {
    fun changeLine(line: Line) {
        this.line = line
        line.sections.sections.add(this)
    }
}