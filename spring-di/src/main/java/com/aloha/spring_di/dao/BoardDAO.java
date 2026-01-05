package com.aloha.spring_di.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j          // 로그(Log)를 기록하는 객체(log)를 자동으로 생성
@Repository     // DAO 역할로 빈 등록
public class BoardDAO {
    
    public void test() {
        log.info("BoardDAO test() 실행");
    }
}

// sysout~ 을 쓰지 않고 log를 쓰는 이유
// 1. sysout은 속도가 느림 (서버성능저하)
// 2. sysout을 통해 출력되는 문구는 언제, 어떤 스레드에서, 어느 클래스에서 발생했는지 알기 어려움
// 3. log 는 sysout과 다르게 설정을 통해 파일로 저장하거나 에러만 골라보거나, 개발 서버에서만
//    보이게 하는 등, 사용자가 자유롭게 조절이 가능함


// @Slf4j를 붙인 뒤, 코드 안에서 log 라는 변수를 사용해 메서드를 호출하면 됨

// log.trace("..."): 아주 상세한 추적 정보

// log.debug("..."): 개발 단계에서 확인용 (SQL 실행 내역 등)

// log.info("..."): 일반적인 운영 정보 (가장 많이 씀)

// log.warn("..."): 에러는 아니지만 주의가 필요한 상황

// log.error("..."): 심각한 에러 발생 (예외 처리 시 주로 사용)