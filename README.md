# hi-bus-go

+ 버스 예매를 하고 노선 조회를 제공하는 서버입니다.
+ 버스 예매, 노선 조회 기능을 직접 구현함으로써 대규모 트래픽에도 견고한 어플레이션을 구현할 수 있드록 하고 있습니다.

## 기능

### 사용자
+ 버스 예매
+ 버스 예매 및 변경
+ 버스 노선 조회
+ 회원 정보 수정, 탈퇴

### 관리자
+ 버스 노선 등록, 수정 및 삭제
+ 공지사항 및 이벤트 등록

### 사용 기술
+ Spring Boot, Maven, Mybatis, Mysql, Redis, Java8

### 기술적인 집중 요소
+ 객체지향의 기본 원리와 spring의 IOC/DI , AOP, ASP 활용과 의미 있는 코드 작성
+ 라이브러리 및 기능 추가 시 이유있는 선택과 사용 목적 고려

### 브렌치 관리 전략
Git Flow를 사용하여 branch를 관리   
모든 branch는 pull request 리뷰 완료후 merge   

![git-flow-strategy](https://user-images.githubusercontent.com/29122916/83837107-79166100-a730-11ea-8744-3761ad01ca96.png)

+ master: 개발, 테스트 완료후 검증이 완료된 코드가 있는 branch
+ develop: 개발이 끝난후 issue branch를 merge
+ issue(feature): develop에서 새로운 기능을 개발 진행
+ release: issue에서 develop으로 merge하여 master에 merge전 배포하여 테스트를 진행
+ hot-fix: release, master에서 발생한 버그를 수정

> #### 브렌치 관리 전략 참고 문헌
+ https://riptutorial.com/ko/git/example/4182/gitflow-%EC%9B%8C%ED%81%AC-%ED%94%8C%EB%A1%9C%EC%9A%B0

### ERD
읽기 전용 url(비밀번호 5ez653)
https://aquerytool.com:443/aquerymain/index/?rurl=70475dc0-1cb5-4573-a864-93e68f4ddcaa
![hi-bus-go_20200826_25_59](https://user-images.githubusercontent.com/29122916/91303300-c6134c00-e7e2-11ea-8819-c789ace7b5e6.png)


