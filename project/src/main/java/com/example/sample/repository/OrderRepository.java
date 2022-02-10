package com.example.sample.repository;


import com.example.sample.domain.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderInfo, Long>, OrderCustomRepository{

}
