package wooteco.subway.line.controller

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath
import wooteco.subway.AcceptanceTest
import wooteco.subway.line.dto.LineRequest
import wooteco.subway.line.dto.LineResponse

@DisplayName("노선 관련 테스트")
class LineAcceptanceTest : AcceptanceTest() {
    @Test
    fun `지하철 노선을 생성한다`() {
        `노선 생성 요청`(신분당선_요청) Then {
            log().all()
            statusCode(HttpStatus.CREATED.value())
            header("Location", notNullValue())
        }
    }

    @Test
    fun `지하철 노선들을 조회한다`() {
        `노선 생성 요청`(신분당선_요청)
        `노선 생성 요청`(구신분당선_요청)

        Given {
            log().all()
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            get("/lines")
        } Then {
            log().all()
            statusCode(HttpStatus.OK.value())
            body("size()", `is`(2))
        }
    }

    private fun `노선 생성 요청`(lineRequest: LineRequest) = Given {
        log().all()
        contentType(MediaType.APPLICATION_JSON_VALUE)
        body(lineRequest)
    } When {
        post("/lines")
    }

    @Test
    fun `id로 지하철 노선을 조회한다`() {
        // given
        val response = `노선 생성 요청`(신분당선_요청)
        val 신분당선_응답 = response.`as`(LineResponse::class.java)

        Given {
            log().all()
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            get(response.header("Location"))
        } Then {
            log().all()
            statusCode(HttpStatus.OK.value())
            jsonPath("id", 신분당선_응답.id)
        }
    }

    @Test
    fun `지하철 노선을 수정한다`() {
        // given
        val response = `노선 생성 요청`(신분당선_요청)
        val 신분당선_응답 = response.`as`(LineResponse::class.java)
        val 마찌선으로_수정_요청 = LineRequest("마찌선", "핑크색", 1L, 2L, 11)

        Given {
            log().all()
            contentType(MediaType.APPLICATION_JSON_VALUE)
            body(마찌선으로_수정_요청)
        } When {
            put(response.header("Location"))
        } Then {
            log().all()
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    fun `지하철 노선을 삭제한다`() {
        // given
        val response = `노선 생성 요청`(신분당선_요청)
        val 신분당선_응답 = response.`as`(LineResponse::class.java)

        Given {
            log().all()
            contentType(MediaType.APPLICATION_JSON_VALUE)
        } When {
            delete(response.header("Location"))
        } Then {
            log().all()
            statusCode(HttpStatus.NO_CONTENT.value())
        }
    }

    companion object {
        val 신분당선_요청: LineRequest = LineRequest("신분당선", "주황색", 1L, 2L, 10)
        val 구신분당선_요청: LineRequest = LineRequest("구신분당선", "노란", 1L, 2L, 15)
    }
}