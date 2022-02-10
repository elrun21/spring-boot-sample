package com.example.sample.repository;

import com.example.sample.domain.dto.response.ResOrderProductDetail;
import com.example.sample.domain.entity.OrderInfo;
import com.example.sample.domain.entity.OrderProduct;
import com.example.sample.domain.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findAllByOrderIdx(OrderInfo orderIdx);
}
