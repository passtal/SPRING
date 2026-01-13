package com.aloha.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.aloha.board.dto.Files;
import com.aloha.board.dto.ParentTable;

@Mapper
public interface FileMapper {
  
  // 파일 목록
  List<Files> list() throws Exception;
  
  // 파일 조회
  Files select(Integer no) throws Exception;
  Files selectById(String id) throws Exception;

  // 파일 등록
  int insert(Files board) throws Exception;

  // 파일 수정
  int update(Files board) throws Exception;
  int updateById(Files board) throws Exception;

  // 파일 삭제
  int delete(Integer no) throws Exception;
  int deleteById(String id) throws Exception;

  // 파일 업로드 
  int upload(List<MultipartFile> files, ParentTable parentTable, Integer parentNo) throws Exception;

  // 부모 기준 목록
  List<Files> listByParent(Files files) throws Exception;


}