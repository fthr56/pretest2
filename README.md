## 개발 환경

- Java 1.8
- SpringBoot 2.1.5
- spring data jpa
- gradle
- h2
- junit5
- lombok
- OpenCSV

## 문제해결 전략

- csv파일에서 데이터베이스 저장 시 OpenCSV를 사용하여 레코드를 읽어옴([baeldung: penCSV](https://www.baeldung.com/opencsv) 참고)
- model
    - Program 
        - 프로그램에 대한 정보를 가짐(프로그램 코드, 프로그램명, 프로그램 소개, 프로그램 상세 소개)
        - 지역(Region)과는 ManyToMany
        - 테마(Theme)와는 ManyToMany
        - PK인 프로그램 코드의 경우 커스텀 Generator 사용하여 자동 증가([baeldung: identifiers in Hibernate](https://www.baeldung.com/hibernate-identifiers) 참고)
    - Region
        - 지역에 대한 정보를 가짐(지역 코드, 지역명)
            - 상/하위(Parent, Children으로 표현) 계층을 가짐 ex) 경기도 성남시 분당구 -> 성남시 기준 상위 : 경기도, 하위 : 분당구, **미구현**
        - PK의 경우 커스텀 Generator로 자동 증가
    - Theme
        - 테마 정보를 가짐(테마별 분류)
    - EcoTourism
        - 생태 관광정보 데이터를 추가/수정할 수 있는 API 개발 시 csv 레코드와 같은 형태의 데이터를 가진다고 판단하고 구현
            - csv 레코드와 마찬가지로 모든 필드가 String
            - String 필드를 통해 Program, Region, Theme 생성
                - Theme로 생성시 콤마, 빈 공간 처리로 잘못 된 테마 생성 방지 
- 일부를 제외한 각 api에 대한 Request/Respose를 만들어 api 요청 처리
- aService에서 b에 대한것이 필요한 경우 bRepository가 아닌 bService를 통한 처리
- Post/Put 등 데이터베이스에 저장 된 데이터가 변경이 있는 테스트의 경우 @Transactional 추가
- 선택문제(키워드를 통한 프로그램 추천)의 경우 우선순위 경합은 조회 지역에 한해서만 발생
    - 검색한 지역 내의 프로그램으로만 추천 경합 진행
    - 테마 : 30, 프로그램 소개 : 20, 상세 소개 : 10 의 가중치를 가진다.
        - 추천 점수  = (각 텍스트에 키워드가 나온 횟수 * 가중치) 합산       

## 남은 사항

- 추가 제약사항(API 인증)
- 다양한 테스트 케이슥(특히 실패하는 경우들)
- Region의 경우 "경기도 성남시 분당구 삼평동"의 경우 하나의 지역으로 처리하는 점 보완
    - Region 생성 시 도/시/군/구/그 외로 계층 구조를 가지게 하여 해결 필요
    - Program과 매핑되는 건 최하위 노드
- Request/Response의 구분을 좀 더 명확히하고 리팩토링을 필요
- 너무 많은 역할을 담당하는 EcoTourism
    - 역할 분리 필요
    - Program과 경계가 모호한 감이 있음
- ProgramController 분리
- 예외처리 미약
    - 커스텀 Exception 구현 필요
    - GlobalExceptionHandler의 추가적인 Exception 발생 케이스 추가
    - Optional의 제대로된 사용
        - 마찬가지로 Null처리 보강 필요

## 실행 및 빌드

```
$ ./gradlew clean build

$ java -jar build/libs/ecotourism-0.0.1.jar
```

- default profiles은 local(현재는 local만 존재)

- 8080 포트 사용(localhost:8080)

- [api 문서](document/api.md)
