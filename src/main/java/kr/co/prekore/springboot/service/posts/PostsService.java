package kr.co.prekore.springboot.service.posts;

import kr.co.prekore.springboot.domain.posts.Posts;
import kr.co.prekore.springboot.domain.posts.PostsRepostitory;
import kr.co.prekore.springboot.web.dto.PostsListResponseDto;
import kr.co.prekore.springboot.web.dto.PostsResponseDto;
import kr.co.prekore.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepostitory postsRepostitory;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepostitory.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {
        Posts posts = postsRepostitory.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepostitory.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException(("해당 게시글이 없습니다. id="+id)));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepostitory.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepostitory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id="+id));
        postsRepostitory.delete(posts);
    }


}
