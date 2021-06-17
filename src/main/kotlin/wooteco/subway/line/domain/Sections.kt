package wooteco.subway.line.domain

import wooteco.subway.line.application.SameUpAndDownStationException
import wooteco.subway.station.domain.Station
import javax.persistence.Embeddable
import javax.persistence.OneToMany

@Embeddable
class Sections(
    @OneToMany(mappedBy = "line")
    val sections: MutableList<Section> = mutableListOf()
) {
    fun sortedStations(): List<Station> {
        if (sections.isNullOrEmpty()) {
            return emptyList()
        }
        val stations: MutableList<Station> = ArrayList()
        val upEndSection: Section = findUpEndSection()
        stations.add(upEndSection.upStation)
        var nextSection: Section? = upEndSection
        while (nextSection != null) {
            stations.add(nextSection.downStation)
            nextSection = findSectionByNextUpStation(nextSection.downStation)
        }
        return stations
    }

    private fun findUpEndSection(): Section {
        val downStations = sections.map { it.downStation }
        return sections.find { !downStations.contains(it.upStation) }
            ?: throw SameUpAndDownStationException()
    }

    private fun findSectionByNextUpStation(station: Station): Section? {
        return sections.find { it.upStation == station }
    }
}