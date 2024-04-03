package com.a603.ofcourse.domain.post.repository;

import com.a603.ofcourse.domain.post.domain.Post;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p " +
            "from Post p " +
            "join fetch p.course c " +
            "order by p.createDate desc")
    List<Post> findAllByRecent(Pageable pageable);

}