package wooteco.subway.line.repository

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import wooteco.subway.line.domain.Line
import wooteco.subway.line.domain.Section
import wooteco.subway.station.domain.Station

@DataJpaTest
class LineRepositoryTest() {

    @Autowired
    lateinit var lineRepository: LineRepository

    @BeforeEach
    fun setUp() {
        lineRepository.deleteAll()
    }

    @Test
    fun `노선을 저장한다`() {
        section.changeLine(line)

        val savedLine = lineRepository.save(line)

        assertThat(line).isEqualTo(savedLine)
    }

    @Test
    internal fun `이름으로 노선을 찾는다`() {
        val savedLine = lineRepository.save(line)

        val findLineByName = lineRepository.findLineByName(savedLine.name)

        assertThat(savedLine).isEqualTo(findLineByName)
    }

    @Test
    internal fun `색깔로 노선을 찾는다`() {
        val savedLine = lineRepository.save(line)

        val findLineByColor = lineRepository.findLineByColor(savedLine.color)

        assertThat(savedLine).isEqualTo(findLineByColor)
    }

    @Test
    fun `id로 노선을 찾는다`() {
        val savedLine = lineRepository.save(line)
        val findLineById = lineRepository.findLineById(savedLine.id)

        assertThat(savedLine).isEqualTo(findLineById)
    }

    companion object {
        val 잠실역 = Station(name = "잠실역")
        val 강남역 = Station(name = "강남역")
        val section = Section(upStation = 잠실역, downStation = 강남역, distance = 10)
        val line = Line(name = "2호선", color = "주황색")
    }
}