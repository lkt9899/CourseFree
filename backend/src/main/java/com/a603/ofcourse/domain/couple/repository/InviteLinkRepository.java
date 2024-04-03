package com.a603.ofcourse.domain.couple.repository;

import com.a603.ofcourse.domain.couple.redis.InviteLink;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
public interface InviteLinkRepository extends CrudRepository<InviteLink, String> {
}
