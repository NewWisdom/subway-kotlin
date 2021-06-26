package wooteco.subway.line.domain

import wooteco.subway.station.domain.Station
import javax.persistence.*

@Entity
data class Section(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    @ManyToOne(fetch = FetchType.LAZY)
    val upStation: Station,
    @ManyToOne(fetch = FetchType.LAZY)
    val downStation: Station,
    @Column(nullable = false)
    val distance: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    var line: Line? = null
) {
    fun changeLine(line: Line) {
        this.line = line
        line.sections.sections.add(this)
    }
}