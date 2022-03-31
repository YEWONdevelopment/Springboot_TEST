package com.prac.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//Entity클래스와 기본 Entity Repository는 같은 선상에 위치해야함
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
