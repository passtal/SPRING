package com.aloha.mybatis.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
    
    private Integer no;
    private String id;
    private String title;
    private String writer;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
