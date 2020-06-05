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

### 브렌치 관리 전략
Git Flow를 사용하여 branch를 관리   
모든 branch는 pull request 리뷰 완료후 merge   ##

![git-flow-strategy](https://user-images.githubusercontent.com/29122916/83837107-79166100-a730-11ea-8744-3761ad01ca96.png)

+ master: 개발, 테스트 완료후 검증이 완료된 코드가 있는 branch
+ develop: 개발이 끝난후 issue branch를 merge
+ issue(feature): develop에서 새로운 기능을 개발 진행
+ release: issue에서 develop으로 merge하여 master에 merge전 배포하여 테스트를 진행
+ hot-fix: release, master에서 발생한 버그를 수정

> #### 브렌치 관리 전략 참고 문헌
+ https://riptutorial.com/ko/git/example/4182/gitflow-%EC%9B%8C%ED%81%AC-%ED%94%8C%EB%A1%9C%EC%9A%B0
