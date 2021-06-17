package wooteco.subway.station.repository

import org.assertj.core.api.AssertionsForInterfaceTypes
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import wooteco.subway.line.repository.LineRepository
import wooteco.subway.line.repository.LineRepositoryTest
import wooteco.subway.station.domain.Station

@DataJpaTest
class StationRepositoryTest {
    @Autowired
    lateinit var stationRepository: StationRepository

    @BeforeEach
    fun setUp() {
        stationRepository.deleteAll()
    }

    @Test
    fun `id로 역을 찾는다`() {
        val 저장된_잠실역 = stationRepository.save(잠실역)

        val findStationById = stationRepository.findStationById(저장된_잠실역.id)

        AssertionsForInterfaceTypes.assertThat(저장된_잠실역).isEqualTo(findStationById)
    }

    companion object {
        val 잠실역 = Station(name = "잠실역")
        val 강남역 = Station(name = "강남역")
    }
}