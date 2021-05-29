# SWEngineeringPRJ
소프트웨어 공학 프로젝트 ATM

구현된 부분 DB 연동 부분 Util 로 분리하여 함수 호출하도록 구조 변경 

Deposit 기능에서 CheckCard 및 Deposit 기능 구현

withdraw, transfer, certification 기능 구현

파일 연결 구조 (아래 파일 순으로 확인 부탁드립니다) 

WebContent/index.jsp > src/controller/Home.java > WebContent/view/deposit.jsp > src/controller/CheckCard.java > src/model/service/ATMService.java > src/model/dao/ATMTrading.java

WebContent/view/deposit.jsp(으로 복귀) 

src/controller/Deposit.java > src/model/service/ATMService.java > src/model/dao/ATMTrading.java 

WebContent/view/deposit.jsp(으로 복귀)

git eclipse 프로젝트 연동 및 사용방법 참고자료 

https://yayongi.tistory.com/entry/github%EC%97%90%EC%84%9C-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EA%B0%80%EC%A0%B8%EC%98%A4%EA%B8%B0

https://jwgye.tistory.com/38