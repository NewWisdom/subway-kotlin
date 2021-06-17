package wooteco.subway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SubwayKotlinApplication

fun main(args: Array<String>) {
    runApplication<SubwayKotlinApplication>(*args)
}
