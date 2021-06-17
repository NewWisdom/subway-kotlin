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
        lineService.save(lineRequest)
    }

}