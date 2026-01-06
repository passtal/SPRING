package com.aloha.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;





@Slf4j
@Controller                     // Controller로 지정하고 빈 등록
@RequestMapping("/request")     // [클래스 레벨 요청경로 매핑] : /request/~
public class RequestController {
    
    // 컨트롤러 메소드

    // 요청 경로 매핑
    /**
     * @RequestMapping : 요청 경로 매핑
     * - 요청 : /request/board
     * - 응답 : /request/board.html
     * @return
     */
    // @RequestMapping(value="/request/board", method=RequestMethod.GET)
    // @RequestMapping(value="/board", method=RequestMethod.GET)
    // @RequestMapping("/board")
    @GetMapping("/board")
    public String request() {
        log.info("[GET] - /request/board");
        return "request/board";
    }

    /**
     * 경로 패턴 매핑
     * @param no
     * @return
     */
    // @RequestMapping(value="/board/{no}", method=RequestMethod.GET)
    @GetMapping("/board/{no}")
    public String requestPath(@PathVariable("no") Long no) {
        log.info("[GET] - /request/board/{no}");
        log.info("no : {}", no);
        return "request/board";
    }

    /**
     * 요청 메소드 매핑
     * @return
     */
    // @RequestMapping(value="/", method=RequestMethod.GET)
    @PostMapping("/board")
    public String requestPost(@RequestParam("no") Long no) {
        log.info("[POST] - /request/board");
        log.info("no : {}", no);
        return "redirect:/request/board/list";
    }
    
    @GetMapping("/board/list")
    public String requestList() {
        return "request/board/list";
    }
    
    /**
     * 파라미터 매핑
     * @param param
     * @return
     * * params 속성으로 요청 파라미터가 id인 경우 매핑함
     * * /request/board?id=aloha
     */
    // @RequestMapping(value="/board", method=RequestMethod.GET
    //                , params = {"id", "age"}
    // id가 string age가 int인 경우에 매핑
    // )
    @GetMapping(value="/board", params={"id", "age"})
    public String requestParams(
        @RequestParam("id") String id,
        @RequestParam("age") Long age) {
        log.info("[GET] - /request/board?id=" + id + "&age" + age);
        log.info("id : {}", id);
        log.info("age : {}", age);
        return "request/board";
    }

    /**
     * 헤더 매핑
     * @param param
     * @return
     * * headers = "헤더명=값"으로 지정하여 헤더를 매핑 조건으로 사용한다.
     */

    @ResponseBody           // 반환 값을, 응답 메세지 본문(body)에 직접 지정
    @RequestMapping(value={"/board", "/board2"}, method=RequestMethod.POST
                    ,headers = "Content-Type=application/json"
                    // ,headers = {"헤더1", "헤더2"}
    )

    public String requestHeader() {
        log.info("[POST] = /request/board");
        log.info("헤더 매핑 ... ");
        return "SUCCESS";
    }
    
}
