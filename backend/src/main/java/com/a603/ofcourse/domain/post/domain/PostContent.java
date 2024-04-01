package com.a603.ofcourse.domain.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "post_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostContent {

    @Id
    @Column(name="post_content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title")
    private String title;

    @Lob
    @Column(name="content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @Builder
    public PostContent(
            String title,
            String content
    ){
        this.title = title;
        this.content = content;
    }

    public void savePost(Post post){
        this.post = post;
    }

}
