package wooteco.subway.member.application

import wooteco.subway.common.BadRequestException

class DuplicateEmailException : BadRequestException("이미 존재하는 아이디 입니다.")

class EmailNotFoundException : BadRequestException("존재하지 않는 계정입니다.")

class MemberNotFoundException : BadRequestException("존재하지 않는 회원입니다.")