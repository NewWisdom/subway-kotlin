package wooteco.subway.line.repository

import org.springframework.data.jpa.repository.JpaRepository
import wooteco.subway.line.domain.Line

interface LineRepository : JpaRepository<Line, Long> {
    fun findLineById(id: Long): Line?
    fun findLineByName(name: String): Line?
    fun findLineByColor(color: String): Line?
}
