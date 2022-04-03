package com.prac.springboot.service.posts;

import com.prac.springboot.domain.posts.Posts;
import com.prac.springboot.domain.posts.PostsRepository;
import com.prac.springboot.web.dto.PostsResponseDto;
import com.prac.springboot.web.dto.PostsSaveRequestDto;
import com.prac.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor        //생성자 주입 1)순환 참조 방지, 2)테스트코드 작성용이 3)코드 악취 제거 4)객체변이 방지(final 가능)
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }
}
