package wooteco.subway.line.application

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import wooteco.subway.line.dto.LineRequest
import wooteco.subway.line.repository.LineRepository

@SpringBootTest
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
        lineService.update(1L, LineRequest("3호선", "노랑색", 1L, 2L, 10))
    }
}