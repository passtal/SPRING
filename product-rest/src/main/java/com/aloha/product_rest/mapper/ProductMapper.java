package com.aloha.product_rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.product_rest.dto.Products;

@Mapper
public interface ProductMapper {
    
    List<Products> list();
    Products select(Integer no);
    boolean insert(Products board);
    boolean update(Products board);
    boolean delete(Integer no);

}
