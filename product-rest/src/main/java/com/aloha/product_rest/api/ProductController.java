package com.aloha.product_rest.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.product_rest.dto.Products;
import com.aloha.product_rest.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
// @Controller        // MVC Controller       : View 반환
@RestController       // REST API Controller  : Data 반환
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  
  // ⭐sp-crud : CRUD 컨트롤러 메소드 자동완성 (Spring Code Generator 확장)
  // @ResponseBody :: @RestController 에서 이미 포함하고 있으므로 생략 가능
  @GetMapping()
  public ResponseEntity<?> getAll() {
      try {
          List<Products> products = productService.list();
          return new ResponseEntity<>(products, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @GetMapping("/{no}")
  public ResponseEntity<?> getOne(@PathVariable("no") Integer no) {
      try {
          Products product = productService.select(no);
          if (product == null) {
            return new ResponseEntity<>("FAIL", HttpStatus.NOT_FOUND);
          }
          return new ResponseEntity<>(product, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }


  
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody Products product) {
      try {
          boolean result = productService.insert(product);
          if( !result ) {
              return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PutMapping()
  public ResponseEntity<?> update(@RequestBody Products product) {
      try {
          boolean result = productService.update(product);
          if( !result ) {
              return new ResponseEntity<>("FAIL",HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @DeleteMapping("/{no}")
  public ResponseEntity<?> destroy(@PathVariable("no") Integer no) {
      try {
          boolean result = productService.delete(no);
          if( !result ) {
              return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

}