package wooteco.subway.station.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wooteco.subway.station.domain.Station

@Repository
interface StationRepository : JpaRepository<Station, Long> {
    fun findStationById(id: Long) : Station?
}