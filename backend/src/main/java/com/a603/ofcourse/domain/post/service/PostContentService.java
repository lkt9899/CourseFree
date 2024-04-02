package com.a603.ofcourse.domain.post.service;

import com.a603.ofcourse.domain.course.domain.CoursePlace;
import com.a603.ofcourse.domain.course.repository.CoursePlaceRepository;
import com.a603.ofcourse.domain.member.domain.Profile;
import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.domain.post.domain.Post;
import com.a603.ofcourse.domain.post.domain.PostContent;
import com.a603.ofcourse.domain.post.dto.PostContentResponseDto;
import com.a603.ofcourse.domain.post.repository.PostRepository;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostContentService {
    private final PostRepository postRepository;
    private final CoursePlaceRepository coursePlaceRepository;

    /* 게시글 리스트 조회 */
    public PostContentResponseDto findPostContentList(int postId){

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new InternalException("예외 처리")
        );

        List<PostContentResponseDto.PostContentInfo> postContentInfoList = new ArrayList<>();

        Profile memberProfile = post.getMember().getProfile();
        List<PostContent> postContentList = post.getPostContentList();
        List<CoursePlace> coursePlaceList = coursePlaceRepository.findAllByCourseId(post.getCourse().getId());

        for (int i=0; i<postContentList.size(); i++){
            PostContent postContent = postContentList.get(i);
            Place place = coursePlaceList.get(i).getPlace();

            postContentInfoList.add(PostContentResponseDto.PostContentInfo
                    .builder()
                    .placeName(place.getName())
                    .url(place.getUrl())
                    .placeImageUrl(place.getImageUrl())
                    .title(postContent.getTitle())
                    .content(postContent.getContent())
                    .build()
            );
        }

        return PostContentResponseDto
                .builder()
                .postId(postId)
                .postTitle(post.getTitle())
                .memberNickname(memberProfile.getNickname())
                .memberImageUrl(memberProfile.getImage())
                .postContentInfoList(postContentInfoList)
                .build();
    }
}
