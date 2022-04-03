package com.prac.springboot.service.posts;

import com.prac.springboot.domain.posts.Posts;
import com.prac.springboot.domain.posts.PostsRepository;
import com.prac.springboot.web.dto.PostsListResponseDto;
import com.prac.springboot.web.dto.PostsResponseDto;
import com.prac.springboot.web.dto.PostsSaveRequestDto;
import com.prac.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.id="+id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)     //트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선 / 등록,수정,삭제는 전혀X
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)     //.map(posts->new PostsListResponseDto(posts))와 같음
                .collect(Collectors.toList());
    }
}
