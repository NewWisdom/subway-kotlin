package wooteco.subway.station.domain

import javax.persistence.*

@Entity
class Station(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    @Column(unique = true, nullable = false)
    val name: String
)