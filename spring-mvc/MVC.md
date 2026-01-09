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



## 응답처리
- void : 요청 경로와 동일한 뷰 이름 사용
- String : 뷰 이름 반환
- ModelAndView : 모델과 뷰 이름 같이 반환
- 클래스 : 객체를 JSON 형식으로 변환하여 응답
- 컬렉션 : 리스트를 JSON 형식으로 변환하여 응답
- ResponseEntity<T> : HTTP 응답 상태, 헤더, 본문 직접 제어
                    (@ResponseBody 없어도 응답본문 직접 지정)

* @ResponseBody
    - 반환값을 응답 본문에 직접 지정 (JSON 등으로 변환)
    * 클래스 또는 컬렉션 반환 시 사용



## 요청처리
- 주요 매개변수
    * 기본타입 & Wrapper 클래스 : @RequestParam("파라미터명") 매개변수명
    * 객체 + 컬렉션
        * 바인딩
        - 폼(form) 데이터 : 자동 바인딩
        - JSON 데이터 : @RequestBody 매개변수명

        * 선언
        - 기본 생성자로 객체 생성
        - setter 메소드로 값 설정

    * HttpServletRequest : 요청 정보 직접 접근
    * HttpServletResponse : 응답 정보 직접 접근
    * HttpSession : 세션 정보 접근
    * @RequestHeader : 요청 헤더 값 매핑
    * @CookieValue : 쿠키 값 매핑

    ★ 폼 요청 처리
    - 일반 파라미터 : @RequestParam("파라미터명") 매개변수명
    - 멀티 파라미터 : @RequestParam("파라미터명") List<타입> 매개변수명
    - 파일 업로드 : @RequestParam("파라미터명") MultipartFile 매개변수명
                    @RequestParam("파라미터명") List<MultipartFile> 매개변수명
                    
                    ▶ application.properties ◀
                    * 파일 업로드 경로 설정
                    upload.path=업로드 경로
                    spring.servlet.multipart.location=업로드경로

                    * 파일 용량 설정 (file 기본 1MB, request 기본 10MB)
                    spring.servlet.multipart.max-file-size=10MB
                    spring.servlet.multipart.max-request-size=50MB

                    ▶ 컨트롤러 ◀
                    @Value("${upload.path}") String uploadPath
                    @Value("${spring,servlet.multipart.location}") String multipartLocation

    - 날짜 타입 : @DateTimeFormar(pattern="yyyy-MM-dd") 매개변수명