package wooteco.subway.station.application

import wooteco.subway.common.BadRequestException

class StationNotExistException : BadRequestException("존재하지 않는 역입니다.")

class StationNameDuplicationException : BadRequestException("이미 존재하는 역 이름 입니다.")