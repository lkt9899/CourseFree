package com.a603.ofcourse.domain.post.service;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.domain.CoursePlace;
import com.a603.ofcourse.domain.course.repository.CoursePlaceRepository;
import com.a603.ofcourse.domain.course.repository.MyCourseRepository;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.domain.Profile;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.domain.post.domain.Post;
import com.a603.ofcourse.domain.post.domain.PostContent;
import com.a603.ofcourse.domain.post.dto.PostContentResponseDto;
import com.a603.ofcourse.domain.post.exception.PostException;
import com.a603.ofcourse.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.a603.ofcourse.domain.member.exception.MemberErrorCode.MEMBER_DOES_NOT_EXISTS;
import static com.a603.ofcourse.domain.post.exception.PostErrorCode.POST_NOT_EXIST;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostContentService {
    private final PostRepository postRepository;
    private final CoursePlaceRepository coursePlaceRepository;
    private final MyCourseRepository myCourseRepository;
    private final JwtTokenService jwtTokenService;
    private final MemberRepository memberRepository;

    /* 게시글 리스트 조회 */
    public PostContentResponseDto findPostContentList(String accessToken, int postId){

        Integer memberId = jwtTokenService.getMemberId(accessToken);
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(MEMBER_DOES_NOT_EXISTS)
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostException(POST_NOT_EXIST)
        );

        Course course = post.getCourse();

        List<PostContentResponseDto.PostContentInfo> postContentInfoList = new ArrayList<>();

        Profile memberProfile = post.getMember().getProfile();
        List<PostContent> postContentList = post.getPostContentList();
        List<CoursePlace> coursePlaceList = coursePlaceRepository.findAllByCourseId(course.getId());

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
                .courseId(course.getId())
                .isHave(myCourseRepository.existsByMemberIdAndCourseId(member.getId(), course.getId()))
                .build();
    }
}
