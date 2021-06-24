package wooteco.subway.line.domain

import wooteco.subway.line.application.NotAbleToDeleteInSectionException
import wooteco.subway.line.application.NotOnlyOneRegisteredStationInSection
import wooteco.subway.line.application.SameUpAndDownStationException
import wooteco.subway.line.application.SectionDistanceInvalidException
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

    fun addSection(section: Section) {
        checkOnlyOneRegisteredStations(listOf(section.upStation, section.downStation))
        updateSectionUpToUpIfAble(section)
        updateSectionDownToDownIfAble(section)
        this.sections.add(section)
    }

    private fun updateSectionUpToUpIfAble(section: Section) {
        sections.find { it.upStation == section.upStation }
            ?.let { replaceSectionWithDownStation(section, it) }
    }

    private fun updateSectionDownToDownIfAble(section: Section) {
        sections.find { it.downStation == section.downStation }
            ?.let { replaceSectionWithUpStation(section, it) }
    }

    private fun replaceSectionWithDownStation(newSection: Section, existSection: Section) {
        if (existSection.distance <= newSection.distance) {
            throw SectionDistanceInvalidException()
        }
        this.sections.add(
            Section(
                upStation = newSection.downStation,
                downStation = existSection.downStation,
                distance = existSection.distance - newSection.distance
            )
        )
        this.sections.remove(existSection)
    }

    private fun replaceSectionWithUpStation(newSection: Section, existSection: Section) {
        if (existSection.distance <= newSection.distance) {
            throw SectionDistanceInvalidException()
        }
        this.sections.add(
            Section(
                upStation = existSection.upStation,
                downStation = newSection.upStation,
                distance = existSection.distance - newSection.distance
            )
        )
        this.sections.remove(existSection)
    }

    private fun checkOnlyOneRegisteredStations(stations: List<Station>) {
        val registeredStations = sortedStations()
        if (registeredStations.containsAll(stations)) {
            throw NotOnlyOneRegisteredStationInSection()
        }
        stations.find { registeredStations.contains(it) }
            ?: throw NotOnlyOneRegisteredStationInSection()
    }

    fun removeStation(station: Station) {
        if (sections.size < 2) {
            throw NotAbleToDeleteInSectionException()
        }
        val upSection = sections.find { it.upStation == station }
        val downSection = sections.find { it.downStation == station }
        if (upSection != null && downSection != null) {
            val newUpStation = downSection.upStation
            val newDownStation = upSection.downStation
            val newDistance = upSection.distance + downSection.distance
            sections.add(
                Section(
                    upStation = newUpStation,
                    downStation = newDownStation,
                    distance = newDistance
                )
            )
        }
        upSection.let { sections.remove(it) }
        downSection.let { sections.remove(it) }
    }
}