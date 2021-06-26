package wooteco.subway.line.domain

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import wooteco.subway.station.domain.Station
import java.util.stream.Stream

class SectionsTest {

    lateinit var 아마_마찌_구간: Section
    lateinit var 마찌_잠실_구간: Section
    lateinit var sections: Sections

    @BeforeEach
    fun setUp() {
        아마_마찌_구간 = Section(upStation = 아마역, downStation = 마찌역, distance = 10)
        마찌_잠실_구간 = Section(upStation = 마찌역, downStation = 잠실역, distance = 10)
        sections = Sections(mutableListOf(아마_마찌_구간, 마찌_잠실_구간))
    }

    @DisplayName("정렬된 역 목록을 반환한다.")
    @Test
    fun sortedStations() {
        assertThat(sections.sortedStations()).containsExactly(아마역, 마찌역, 잠실역)
    }

    @DisplayName("구간을 추가한다")
    @ParameterizedTest
    @MethodSource("addSectionProvider")
    fun addSection(newSection: Section, expectedSectionAsList: List<Section>) {
        sections.addSection(newSection)
        assertThat(sections.sections).containsAll(expectedSectionAsList)
    }

    @DisplayName("구간을 삭제한다")
    @ParameterizedTest
    @MethodSource("removeStationProvider")
    fun removeStation(targetStation: Station, expectedSectionAsList: List<Section>) {
        sections.removeStation(targetStation)
        assertThat(sections.sections).containsAll(expectedSectionAsList)
    }

    companion object {
        val 아마역 = Station(1L, "아마역")
        val 마찌역 = Station(2L, "마찌역")
        val 잠실역 = Station(3L, "잠실역")
        val 삼전역 = Station(4L, "삼전역")

        @JvmStatic
        fun addSectionProvider() = Stream.of(
            Arguments.of(
                Section(upStation = 아마역, downStation = 삼전역, distance = 4),
                listOf(
                    Section(
                        upStation = 아마역,
                        downStation = 삼전역,
                        distance = 4
                    ),
                    Section(
                        upStation = 삼전역,
                        downStation = 마찌역,
                        distance = 6
                    ),
                    Section(
                        upStation = 마찌역,
                        downStation = 잠실역,
                        distance = 10
                    )
                )
            ),
            Arguments.of(
                Section(upStation = 삼전역, downStation = 아마역, distance = 4),
                listOf(
                    Section(
                        upStation = 삼전역,
                        downStation = 아마역,
                        distance = 4
                    ),
                    Section(
                        upStation = 아마역,
                        downStation = 마찌역,
                        distance = 10
                    ),
                    Section(
                        upStation = 마찌역,
                        downStation = 잠실역,
                        distance = 10
                    )
                )
            ),
        )

        @JvmStatic
        fun removeStationProvider() = Stream.of(
            Arguments.of(
                아마역,
                listOf(
                    Section(
                        upStation = 마찌역,
                        downStation = 잠실역,
                        distance = 10
                    )
                )
            ),
            Arguments.of(
                마찌역,
                listOf(
                    Section(
                        upStation = 아마역,
                        downStation = 잠실역,
                        distance = 20
                    )
                )
            ),
            Arguments.of(
                잠실역,
                listOf(
                    Section(
                        upStation = 아마역,
                        downStation = 마찌역,
                        distance = 10
                    )
                )
            ),
        )
    }
}