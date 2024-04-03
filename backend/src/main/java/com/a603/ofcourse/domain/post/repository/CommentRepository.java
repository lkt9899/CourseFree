package com.a603.ofcourse.domain.post.repository;

import com.a603.ofcourse.domain.post.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select c " +
            "from Comment c " +
            "join fetch c.member " +
            "where c.post.id = :postId " +
            "order by c.createDate desc")
    List<Comment> findByPostId(Integer postId);
}
