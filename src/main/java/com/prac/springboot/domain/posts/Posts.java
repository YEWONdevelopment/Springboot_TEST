package com.prac.springboot.domain.posts;

import com.prac.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter             //클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor  //기본 생성자 자동추가, public Posts(){}와 같은 효과
@Entity             //테이블과 링크될 클래스, 기본값으로 클래스의 이름을 테이블 이름으로 매칭, 절대 Setter메소드 만들지 않음
public class Posts extends BaseTimeEntity {

    @Id         //PK를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //PK생성규칙
    private Long id;

    @Column(length = 50, nullable = false)      //선언하지 않아도 해당 클래스의 필드는 모두 컬럼, 기본값 외 추가 변경 필요 옵션 위해 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함, 생성자나 빌더나 생성 시점에 값을 채워주는 역할ㄹ
    public Posts(String title,String content,String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
