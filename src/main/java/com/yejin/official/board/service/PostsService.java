package com.yejin.official.board.service;

import com.yejin.official.board.domain.posts.Posts;
import com.yejin.official.board.domain.posts.PostsRepository;
import com.yejin.official.board.web.dto.PostsResponseDto;
import com.yejin.official.board.web.dto.PostsSaveRequestDto;
import com.yejin.official.board.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("There's no post on that id : "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("There's no post on that id : "+id));
        return new PostsResponseDto(entity);
    }
}
