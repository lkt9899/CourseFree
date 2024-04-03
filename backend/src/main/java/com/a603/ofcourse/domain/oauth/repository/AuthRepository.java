package com.a603.ofcourse.domain.oauth.repository;

import com.a603.ofcourse.domain.oauth.redis.RefreshToken;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
public interface AuthRepository extends CrudRepository<RefreshToken, Integer> {
}
