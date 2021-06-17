## subway-kotlin
죽이되든 밥이되든... 일단 해보는 꼬뜰린과 jpa로 쟈철 미션 구현 🦦

* 몰라 일단 부딪혀보면서 배우는 jpa
* service-apply 참고하면서 구현해보기
* 꼬틀린 트러블 슈팅 기록 기록
* 각각 미션에서 아쉬웠던 부분 보완하기
* 갹각 미션에서 욕심내고 싶은 부분 도전하기


### STEP1 노선도 관리 기능 목록
#### ✅ 노선 생성
##### Request
```json
POST /lines HTTP/1.1
Content-Type: application/json;charset=UTF-8
Content-Length: 133
Host: newwisdom-subway.p-e.kr

{
"name" : "2호선",
"color" : "주황색",
"upStationId" : 1,
"downStationId" : 2,
"distance" : 10,
"extraFare" : 100
}
```
##### Response
```json
HTTP/1.1 201 Created
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Location: /lines/1
Content-Type: application/json
Content-Length: 101

{
"id" : 1,
"name" : "2호선",
"color" : "주황색",
"stations" : [ ],
"sections" : [ ]
}
```

#### 노선 목록 조회
##### Request
```json
GET /lines HTTP/1.1
Host: newwisdom-subway.p-e.kr
```
##### Response
```json
HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Content-Length: 208

[ {
  "id" : 1,
  "name" : "2호선",
  "color" : "주황색",
  "stations" : [ ],
  "sections" : [ ]
}, {
  "id" : 2,
  "name" : "3호선",
  "color" : "주황색",
  "stations" : [ ],
  "sections" : [ ]
} ]
```

#### 노선 ID 조회
##### Request
```json
GET /lines/1 HTTP/1.1
Host: newwisdom-subway.p-e.kr

```
##### Response
```json
HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Content-Length: 101

{
  "id" : 1,
  "name" : "2호선",
  "color" : "주황색",
  "stations" : [ ],
  "sections" : [ ]
}
```

#### 노선 수정
##### Request
```json
PUT /lines/1 HTTP/1.1
Content-Type: application/json;charset=UTF-8
Content-Length: 133
Host: newwisdom-subway.p-e.kr

{
  "name" : "2호선",
  "color" : "주황색",
  "upStationId" : 1,
  "downStationId" : 2,
  "distance" : 10,
  "extraFare" : 100
}
```
##### Response
```json
HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
```

#### 노선 삭제
##### Request
```json
DELETE /lines/1 HTTP/1.1
Host: newwisdom-subway.p-e.kr
```
##### Response
```json
HTTP/1.1 204 No Content
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
```