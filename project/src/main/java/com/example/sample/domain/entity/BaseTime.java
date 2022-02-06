package com.example.sample.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {
    @ApiModelProperty("생성일")
    @CreatedDate
    private LocalDateTime createAt ;

    @ApiModelProperty("수정일")
    @LastModifiedDate
    private LocalDateTime updateAt ;

}
