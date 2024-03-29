# JAVA TERM PROJECT

## A. 알고리즘
### 1. 프로그램의 전반적인 동작 방식
   프로그램의 핵심 알고리즘은 사용자가 일정을 관리하고 확인할 수 있는 캘린더 시스템입니다. 주요 목적은 사용자가 특정 날짜에 일정을 추가하고, 추가된 일정을 확인하며, 필요 시 일정을 삭제할 수 있는 기능을 제공하는 것입니다.

## B. 패키지 구조
### 1. 캘린더 패키지
   Main_ui 클래스: 프로그램의 핵심적인 UI를 담당하며, 캘린더의 초기화와 사용자 인터페이스 업데이트를 수행하는 메소드를 제공합니다. 캘린더를 설정하고 초기화하며 사용자 인터페이스를 동적으로 업데이트합니다.
   DateBox 클래스: 개별 날짜를 나타내는 박스로, 특정 날짜의 일정을 관리합니다. 사용자가 일정을 삭제하며, 해당 날짜의 일정을 데이터베이스에서 삭제하고 UI를 업데이트합니다. 생성자는 객체 초기화와 함께 해당 날짜의 일정을 확인하고 버튼 및 이벤트를 설정합니다. 또한, 일정을 표시하는 다이얼로그를 열어주고 새로운 일정을 생성하는 다이얼로그를 열어줍니다.
### 2. 로그인&회원가입 패키지
   LogInPage 클래스: 사용자의 회원 가입과 로그인을 처리합니다. signUp 메소드는 사용자의 아이디와 비밀번호를 입력받아 데이터베이스에 계정을 추가하고, login 메소드는 입력받은 아이디와 비밀번호를 확인하여 로그인을 처리합니다.
### 3. DB 패키지
   AddDate 클래스: 데이터베이스와 관련된 작업을 담당합니다. addScheduleToDB 메소드는 데이터베이스에 새로운 일정을 추가하고, fetchUserSchedules 메소드는 특정 사용자의 일정을 데이터베이스에서 가져오며, deleteUserSchedule 메소드는 특정 일정을 삭제합니다.**


<캘린더 UI 참조>https://il1110.tistory.com/
