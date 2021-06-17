package wooteco.subway.line.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wooteco.subway.line.domain.Line

@Repository
interface LineRepository : JpaRepository<Line, Long> {
    fun findLineByName(name: String): Line?
    fun findLineByColor(color : String) : Line?
}
