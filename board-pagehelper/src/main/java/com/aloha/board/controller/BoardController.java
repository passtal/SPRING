package com.aloha.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.aloha.board.dto.Board;
import com.aloha.board.dto.Pagination;
import com.aloha.board.service.BoardService;
import com.github.pagehelper.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [GET]    - /board/list       : 게시글 목록 화면
 * [GET]    - /board/detail     : 게시글 조회 화면
 * [GET]    - /board/create     : 게시글 등록 화면
 * [POST]   - /board/create     : 게시글 등록 처리
 * [GET]    - /board/updatee    : 게시글 수정 화면
 * [POST]   - /board/update     : 게시글 수정 처리
 * [POST]   - /board/delete     : 게시글 삭제 처리
 */
@Slf4j                      // 로그 어노테이션
@Controller                 // 컨트롤러 빈으로 등록
@RequestMapping("/board")   // 클레스 레벨 요청 경로 매핑
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  /**
   * 게시글 목록 화면
   * @param model
   * @return
   * @throws Exception
   */
  @GetMapping("/list")
  public String list(
    Model model,
    Pagination pagination
  ) throws Exception {
    // 데이터 요청
    // List<Board> list = boardService.list();

    // PageHelper 라이브러리로 페이징 처리
    long page = (int) pagination.getPage();
    long size = (int) pagination.getSize();
    PageInfo<Board> pageInfo = boardService.page(page, size);
    model.addAttribute("pageInfo", pageInfo);
    List<Board> list = pageInfo.getList();

    log.info("pageInfo : {}", pageInfo);

    // pagination 객체로 직접 페이징 처리
    List<Board> list2 = boardService.page(pagination);

    log.info("### 게시글 목록 ###");
    log.info("# " + list);
    // 모델 등록
    model.addAttribute("pagination", pagination);
    model.addAttribute("list", list);

    // Uri 빌더
    String  pageUri = UriComponentsBuilder.fromPath("/board/list")
                                          // Pagination(직접구현)
                                          // .queryParam("size", pagination.getSize())
                                          // .queryParam("count", pagination.getCount())
                                          // PageHelper(라이브러리)
                                          .queryParam("size", pageInfo.getSize())
                                          .queryParam("count", pageInfo.getPageSize())
                                          .build()
                                          .toUriString();
    model.addAttribute("pageUri", pageUri);

    // 뷰 지정
    return "board/list";
  }
  

  /**
   * 게시글 조회 화면
   * - /board/detail?no=💎
   * @param no
   * @param model
   * @return
   * @throws Exception 
   */
  @GetMapping("/detail")
  public String detail(
    @RequestParam("no") Integer no,
    Model model
  ) throws Exception {
    // 데이터 요청
    Board board = boardService.select(no);
    // 모델 등록
    model.addAttribute("board", board);
    // 뷰 지정
    return "board/detail";
  }
  
  /**
   * 게시글 등록 화면
   * @return
   */
  @GetMapping("/create")
  public String create() {
      return "board/create";
  }

  /**
   * 게시글 등록 처리
   * @param board
   * @return
   * @throws Exception
   */
  @PostMapping("/create")
  public String create(Board board) throws Exception {
    // 데이터 요청
    boolean result = boardService.insert(board);
    // 리다이렉트 
    // ⭕ 데이터 처리 성공
    if( result )  
      return "redirect:/board/list";
    // ❌ 데이터 처리 실패
    return "redirect:/board/create?error";
  }
  

  /**
   * 게시글 수정 화면
   * @param no
   * @param model
   * @return
   * @throws Exception
   */
  @GetMapping("/update")
  public String update(
    @RequestParam("no") Integer no,
    Model model
  ) throws Exception {
    Board board = boardService.select(no);
    model.addAttribute("board", board);
    return "board/update";
  }
  
  /**
   * 게시글 수정 처리
   * @param board
   * @return
   * @throws Exception
   */
  @PostMapping("/update")
  public String update(Board board) throws Exception {
    boolean result = boardService.update(board);
    if( result )
      return "redirect:/board/list";
    int no = board.getNo();
    return "redirect:/board/update?no=" + no + "&error";
  }
  

  /**
   * 게시글 삭제 처리
   * @param no
   * @return
   * @throws Exception
   */
  @PostMapping("/delete")
  public String delete(@RequestParam("no") Integer no) throws Exception {
    boolean result = boardService.delete(no);
    if( result )
      return "redirect:/board/list";
    return "redirect:/board/update?no=" + no + "&error";
  }
}