package wooteco.subway.line.application

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import wooteco.subway.line.dto.LineRequest

@SpringBootTest
@ActiveProfiles("test")
internal class LineServiceTest {
    @Autowired
    lateinit var lineService: LineService

    @Test
    @DisplayName("노선을 저장한다.")
    fun save() {
        val lineRequest = LineRequest("2호선", "주황색", 1L, 2L, 10)
        val saveLine = lineService.save(lineRequest)
    }

    @Test
    fun `노선을 수정한다`() {
        val lineRequest = LineRequest("2호선", "주황색", 1L, 2L, 10)
        val saveLine = lineService.save(lineRequest)
        lineService.updateLine(saveLine.id, LineRequest("3호선", "노랑색", 1L, 2L, 10))
    }
}