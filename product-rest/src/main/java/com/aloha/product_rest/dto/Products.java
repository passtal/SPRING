package com.aloha.product_rest.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Products {

    private Integer no;
    private String productName;
    private Integer price;
    private Integer stock;
    private Date createdAt;
    private Date updatedAt;
    
}
