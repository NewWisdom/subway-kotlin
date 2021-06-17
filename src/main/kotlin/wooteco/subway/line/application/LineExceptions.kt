package wooteco.subway.line.application

import wooteco.subway.common.BadRequestException

class ExistLineNameException : BadRequestException("이미 존재하는 노선 이름 입니다.")

class ExistLineColorException : BadRequestException("이미 존재하는 노선 색깔 입니다.")

class SameUpAndDownStationException : BadRequestException("상행 종점, 하행 종점은 같을 수 없습니다.")
