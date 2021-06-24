package wooteco.subway.line.application

import wooteco.subway.common.BadRequestException

class ExistLineNameException : BadRequestException("이미 존재하는 노선 이름 입니다.")

class ExistLineColorException : BadRequestException("이미 존재하는 노선 색깔 입니다.")

class SameUpAndDownStationException : BadRequestException("상행 종점, 하행 종점은 같을 수 없습니다.")

class LineNotExistException : BadRequestException("조회하려는 노선이 없습니다.")

class NotOnlyOneRegisteredStationInSection : BadRequestException("노선에 등록할 구간의 역이 하나만 등록되어 있어야 합니다.")

class SectionDistanceInvalidException : BadRequestException("구간의 길이가 잘못되었습니다.")

class NotAbleToDeleteInSectionException : BadRequestException("구간이 하나인 경우 역 삭제 불가능합니다.")