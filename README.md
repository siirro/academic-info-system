## 🎓Academic-system
학사정보시스템

## 🔨기능 소개
 1. 원서접수 & 승인 : 학생은 원서를 제출하고, 입학 관리자는 합격한 학생을 승인하여 학적을 생성합니다.
 2. 회원가입 : 학적이 생성된 학생은 아이디와 비밀번호를 등록하여 로그인하고, 학적 정보를 조회합니다.
 3. 개설 강의 조회 : 이번 학기에 개설된 강의 전체를 조회합니다.
 4. 수강신청 : 수강을 원하는 강의를 선택하여 저장합니다.
 5. 수강취소 : 수강을 취소할 강의를 선택하여 삭제합니다.

## 💿ERD
![image](https://github.com/user-attachments/assets/8fa1a073-0673-411a-8dd8-c9d2ad68bcd3)

## 💻개발 환경
- java 21
- Spring Boot 3.4.3
- Spring Data JPA
- mysql

## 📃API

### 사용자 API
|기능        	 |메소드     |엔드포인트  |
|----------------|----------|-------------------------------|
|원서 작성        |`POST`    |`/api/applications`            |
|원서 수정        |`PUT`     |`/api/applications/{appId}`    |
|원서 조회        |`GET`     |`/api/applications/{appId}`    |
|합격자 학번 조회 |`GET`      |`/api/student/find`            |
|비밀번호 등록    |`PUT`     |`/api/student/join/{studentId}` |
|학생 정보 조회   |`GET`     |`/api/info/{studentId}`         |
|강의 목록 조회   |`GET`     |`/api/info/lecture/`            |
|수강 현황        |`GET`     |`/api/info/lecture/{studentId}` |
|수강 신청        |`POST`    |`/api/info/lecture/apply/{lectureId}`  |
|수강 삭제        |`DELETE`  |`/api/info/lecture/delete/{lectureId}` |

### 관리자 API
|기능        	 |메소드     |엔드포인트  |
|----------------|-----------|-------------------|
|원서 목록        |`GET`      |`/api/applications`              |
|학생 승인        |`PATCH`    |`/api/applications/admit/{appId}`|
|강의 목록 	      |`GET`      |`/api/lecture/list`            |
|강의 조회 	      |`GET`      |`/api/lecture/{lectureId}`            |
|강의 추가 	      |`POST`     |`/api/lecture/create`            |
|강의 수정        |`PUT`     |`/api/lecture/update/{lectureId}`        |
|강의 삭제        |`PUT`     |`/api/lecture/delete/{lectureId}`        |


