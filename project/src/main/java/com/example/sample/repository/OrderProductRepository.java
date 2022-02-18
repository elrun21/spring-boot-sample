package com.example.sample.repository;

import com.example.sample.domain.entity.OrderInfo;
import com.example.sample.domain.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    List<OrderProduct> findAllByOrderIdx(OrderInfo orderIdx);
}
