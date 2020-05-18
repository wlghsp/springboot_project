package kr.co.prekore.springboot.web.domain.posts;

import kr.co.prekore.springboot.domain.posts.Posts;
import kr.co.prekore.springboot.domain.posts.PostsRepostitory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepostitory postsRepostitory;

    @After
    public void cleanup() {
        postsRepostitory.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepostitory.save(Posts.builder()
                .title(title)
                .content(content)
                .author("wlghsp@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepostitory.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}
