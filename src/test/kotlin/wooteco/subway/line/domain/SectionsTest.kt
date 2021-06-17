package wooteco.subway.line.domain

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import wooteco.subway.station.domain.Station

internal class SectionsTest {

    @DisplayName("정렬된 역 목록을 반환한다.")
    @Test
    fun sortedStations() {
        assertThat(sections.sortedStations()).containsExactly(아마역, 마찌역)
    }

    companion object {
        val 아마역 = Station(1L, "아마역")
        val 마찌역 = Station(2L, "마찌역")
        val 잠실역 = Station(3L, "잠실역")
        val section = Section(upStation = 아마역, downStation = 마찌역, distance = 10)
        val sections = Sections(mutableListOf(section))
    }
}