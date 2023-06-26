package com.klid.demospringbootretryable;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/{id}")
    public ResponseEntity<Post> findOnePost(@PathVariable(name = "id") Integer id) {
        logger.info(String.format("find one post with id %s", id));

        sleep();

        var post = Post.builder()
                .id(id.longValue())
                .title("ea molestias quasi exercitationem repellat qui ipsa sit aut")
                .body("et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut")
                .userId(1L)
                .build();

        logger.info(String.format("Post with id %s is %s", id, post));

        return ResponseEntity.ok(post);
    }

    @SneakyThrows
    private void sleep() {
        long timeout = ThreadLocalRandom.current().nextLong(250, 1000);
        TimeUnit.MILLISECONDS.sleep(timeout);
    }

    @Builder
    @Data
    private static class Post {
        private Long id;
        private String title;
        private String body;
        private Long userId;
    }
}
