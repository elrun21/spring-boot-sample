package com.example.sample.repository;


import com.example.sample.domain.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductInfo, Long>, ProductCustomRepository{

    ProductInfo findByIdx(Long productIdx);
}
