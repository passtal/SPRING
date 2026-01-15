package com.aloha.product_rest.service;

import java.util.List;

import com.aloha.product_rest.dto.Products;

public interface ProductService {
    
    List<Products> list();
    Products select(Integer no);
    boolean insert(Products board);
    boolean update(Products board);
    boolean delete(Integer no);
}
