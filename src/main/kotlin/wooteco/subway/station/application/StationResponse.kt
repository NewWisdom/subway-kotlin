package wooteco.subway.station.application

import wooteco.subway.station.domain.Station

class StationResponse(var id: Long, var name: String) {
    companion object {
        fun from(station: Station): StationResponse {
            return StationResponse(station.id, station.name)
        }

        fun listOf(stations: List<Station>): List<StationResponse> {
            return stations.map { from(it) }
        }
    }
}
