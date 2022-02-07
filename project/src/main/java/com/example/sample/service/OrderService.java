package com.example.sample.service;

import com.example.sample.common.utils.LogUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.domain.dto.request.ReqOrderDTO;
import com.example.sample.repository.MemberInfoRepository;
import com.example.sample.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final MemberRepository repositoryMember;
    private final MemberInfoRepository repositoryMemberInfo;
    private final ResponseUtils response;
    private final LogUtils logUtils;


    public ResponseEntity makeOrder(ReqOrderDTO data) {
        return response.makeSuccessResponse( data);
    }

    public ResponseEntity findOrder(Long lastIdx, Long prevIdx, int size) {
        return response.makeSuccessResponse( null);
    }
}
