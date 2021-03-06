package com.prac.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass       //JPA Entity클래스들이 BaseTimeEntity을 상속할 경우 필드들도 컬럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class)  //BaseTimeEntity 클래스에 Auditing 기능 포함
public abstract class BaseTimeEntity {

    @CreatedDate        //Entity가 생성되어 저장될 때 시간이 자동저장(regDt)
    private LocalDateTime createdDate;

    @LastModifiedDate   //수정시간(updDt)
    private LocalDateTime modifiedDate;
}
