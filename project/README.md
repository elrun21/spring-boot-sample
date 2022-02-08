# Kwak Joo Hyeong Apis

## summary
- 기능 
  - 회원 가입
  - 회원 탈퇴  
  - 로그인 
  - 로그아웃 
  - 회원 리스트 조회 
  - 회원 상세 조회
  - 상품 조회
  - 상품 등록
  - 상품 주문
  - 상품 주문 내역 조회
  - 상품 주문 상세내역 조회
- 파일 구성 
  - /project : intellij 기반의 프로젝트 ( 소스 )
  - sample-0.0.1-SNAPSHOT.jar : 실행 파일   
  - /data/testdb.mv.db :  샘플 데이터가 탑재 되있는 H2 데이터 파일 
  - report_성능.html :  성능 테스트 결과 보고서 

## Spec
- **SpringBoot 2.5.3**
- **JAVA 11**
- **Gradle 7.1.1**
- **Build: Gradle**
- **H2 DB**
- **JPA**
- **QueryDSL**
- **JWT**
- **Swagger2**

## Installation
- 자바(JDK 11) 설치
  - mac os ( homebrew가 설치 되었을 경우 )
    - brew tap AdoptOpenJDK/openjdk
    - brew cask install adoptopenjdk11
  - window
    - 링크 참조 : https://www.jeu.kr/28
  - unbuntu 
    - sudo apt install openjdk-11-jdk
  - centos 
    - yum install java-11-openjdk-devel.x86_64
- 실행  
    - jar 파일 실행 : java -jar sample-0.0.1-SNAPSHOT.jar
    - 백그라운드 실행시 : nohup java -jar sample-0.0.1-SNAPSHOT.jar &

## How to use
- api 문서를 참고하여 API 호출 가능 
    - 서버구동 후 아래 가이드된 접속 링크로 들어가서 확인 가능
    - 일부 토큰이 필요한 API 는 회원가입/로그인 이후 발급되는 토큰정보를 사용하여 활용가능  
    - api swagger 접속 
      - Api Document : http://localhost:8081/swagger-ui.html
- 사용하는 서버 포트 : 8081
- 데이터베이스 
  - 서버 구동후 접속 ( http://localhost:8081/h2-db )
  - ID/PW : sa/sa

## Performance Statistics
- 기준
    - 테스트 시간 : 약 10분
    - 총 유저 : 1000명 ( 데이터베이스 임의 생성)
    - 유저별 투입 시점 : 5초 마다 1명 
    - 유저별 재시도 시간 : 5초~15초 사이에 랜덤하게 트래픽 발생 
    - 대상 API  : 로그인 , 로그아웃 
    - report 파일 :  report_1628147343.73775.html
  
       