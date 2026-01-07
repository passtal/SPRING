# MVC
- 요청 경로 매핑
- Model (뷰에서 사용할 데이터)
- 응답 처리
- 요청 처리

## 요청 경로 매핑
- @Controller
- @RequestMapping("value="/경로", method=RequestMethod.메소드)
    - @GetMapping("/경로")
    - @PostMapping("/경로")
    - @PutMapping("/경로")
    - @DeleteMapping("/경로")

    * 주요 속성
    - value : 요청경로
    - method : HTTP 메소드(GET, POST, PUT, DELETE)
    - params : 요청 파라미터 조건
    - headers : 요청 헤더 조건
    - consumes : 요청 컨텐츠 타입 조건 (Content-Type)
    - produces : 요청 컨텐츠 타입 조건 (Accept)
- @PathVariable("변수명") : 경로 변수 매핑
    * 예시: /user/{id} → @PathVariable("id") String id
- @RequestParam("파라미터명") : 요청 파라미터 매핑
    * 예시 : /search?query=java → @RequestParam("query") String query

## Model
: 뷰에서 사용할 데이터를 담는 객체
★ 모델에 데이터 등록하기

1. 컨트롤러 메소드에  Model 매개변수 추가
    * 컨트롤러 메소드 (Model model)
2. model.addAttribute("속성명", "값") 메소드 호출
3. 뷰에서 모델 데이터 사용하기
    * Thymeleaf
    <th:block th:text="${속성명}"></th:block>
    <h1 th:text="${속성명}"></h1>
    <input type="text" th:value="${속성명}"/>
    <input type="text" th:value="${객체.변수명}"/>

    * @{} : URL 링크 표현식
    <img th:src="@{${속성명}}"/>

    * 특별한 의미의 속성
    - th:text : 텍스트 출력
    - th:object : 폼 객체 바인딩
    - th:fideld : 폼 필드 바인딩
        * object, field 예시
        class User {
            private String username;
            private String name;
            private String email;
        }
        *{변수명} = ${user.변수명}
        <form th:object="${user}>
            <input type="text" th:field="*{username}"/>
            <input type="text" th:field="*{name}"/>
            <input type="text" th:field="*{email}"/>
        </form>
        * id, name 속성 자동 생성
        <form th:object="${user}>
            <input type="text" id="username" name="username"/>
            <input type="text" id="username" name="name"/>
            <input type="text" id="username" name="email"/>
        </form>
    
    - th:if, th:unless → 조건부 렌더링
    <th:block th:if="${조건식}">조건이 참일 때 출력</th:block>
    <th:block th:if="${조건식}">조건이 거짓일 때 출력</th:block>

    - th:each → 반복문
    <th:block th:each="item, iterStat : ${리스트}">
        <th:block th:text="${iterStat.index} + ' : ' + ${item}"></th:block>
    </th:block>

    컨트롤러 메소드에 컬렉션을 반환타입으로 지정하는 경우, 일반적을 JSON 또는 XML 데이터 형식으로 응답한다.