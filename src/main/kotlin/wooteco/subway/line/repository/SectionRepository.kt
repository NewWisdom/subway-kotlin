package wooteco.subway.line.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wooteco.subway.line.domain.Section

@Repository
interface SectionRepository : JpaRepository<Section, Long> {
}