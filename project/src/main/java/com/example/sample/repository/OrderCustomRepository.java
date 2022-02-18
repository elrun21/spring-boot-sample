package com.example.sample.repository;

import com.example.sample.domain.dto.response.ResOrderDTO;
import com.example.sample.domain.dto.response.ResProductDTO;
import com.example.sample.domain.entity.Member;
import com.example.sample.domain.entity.OrderInfo;

import java.util.List;

public interface OrderCustomRepository {
    List<ResOrderDTO> findOrder(Member member , Long targetIdx , String productName, int size );
}
