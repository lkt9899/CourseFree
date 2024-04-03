package com.a603.ofcourse.domain.post.repository;

import com.a603.ofcourse.domain.post.domain.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostContentRepository extends JpaRepository<PostContent, Integer> {
}
