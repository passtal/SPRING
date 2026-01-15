package com.aloha.product_rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.product_rest.dto.Products;
import com.aloha.product_rest.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;
    
    @Override
    public List<Products> list() {
        return productMapper.list();
    }

    @Override
    public Products select(Integer no) {
        return productMapper.select(no);
    }

    @Override
    public boolean insert(Products board) {
        return productMapper.insert(board);
    }

    @Override
    public boolean update(Products board) {
        return productMapper.update(board);
    }

    @Override
    public boolean delete(Integer no) {
        return productMapper.delete(no);
    }

}
