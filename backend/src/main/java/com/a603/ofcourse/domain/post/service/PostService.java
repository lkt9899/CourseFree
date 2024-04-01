package com.a603.ofcourse.domain.post.service;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.repository.CourseRepository;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.post.domain.Post;
import com.a603.ofcourse.domain.post.domain.PostContent;
import com.a603.ofcourse.domain.post.dto.PostRequestDto;
import com.a603.ofcourse.domain.post.dto.PostResponseDto;
import com.a603.ofcourse.domain.post.repository.PostRepository;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.a603.ofcourse.domain.member.exception.MemberErrorCode.MEMBER_DOES_NOT_EXISTS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final JwtTokenService jwtTokenService;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /* 게시물 저장 */
    @Transactional
    public Integer save(String accessToken, PostRequestDto requestDto){

        Integer memberId = jwtTokenService.getMemberId(accessToken);

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(MEMBER_DOES_NOT_EXISTS)
        );

        Course course = courseRepository.findById(requestDto.getCourseId()).orElseThrow(
                () -> new InternalException("예외 처리 예정")
        );

        Post post = new Post(
                requestDto.getPostTitle(),
                course,
                member
        );

        requestDto.getPostContentInfoList().forEach(p ->
                {
                    PostContent postContent = new PostContent(
                            p.getTitle(),
                            p.getContent()
                    );

                    postContent.savePost(post);
                }
        );

        return postRepository.save(post).getId();
    }


    /* 게시물 리스트 조회 - 추천 */
    public List<PostResponseDto> findPostListByRecommend(String accessToken){

        Integer memberId = jwtTokenService.getMemberId(accessToken);

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(MEMBER_DOES_NOT_EXISTS)
        );

        List<Post> postList = new ArrayList<>(); // 게시물 추천 받기(현조형이 해줄거임)
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        postList.forEach(p -> postResponseDtoList.add(PostResponseDto
                .builder()
                .postId(p.getId())
                .title(p.getTitle())
                .imageUrl(p.getCourse().getImageUrl())
                .build())
        );

        return postResponseDtoList;
    }

    /* 게시물 리스트 조회 - 최신순 */
    public List<PostResponseDto> findPostListByRecent(int offset){
        PageRequest pageRequest = PageRequest.of(offset, 10, Sort.by(Sort.Direction.DESC, "create_date"));
        List<Post> postList = postRepository.findAllByRecent(pageRequest);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        postList.forEach(p -> postResponseDtoList.add(PostResponseDto
                .builder()
                .postId(p.getId())
                .title(p.getTitle())
                .imageUrl(p.getCourse().getImageUrl())
                .build())
        );

        return postResponseDtoList;
    }

    /* 게시물 리스트 조회 - 찜순 */
    public List<PostResponseDto> findPostListByWishList(int offset) {
        PageRequest pageRequest = PageRequest.of(offset, 10);
        List<Post> postList = postRepository.findAllByUseCount(pageRequest);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        postList.forEach(p -> postResponseDtoList.add(PostResponseDto
                .builder()
                .postId(p.getId())
                .title(p.getTitle())
                .imageUrl(p.getCourse().getImageUrl())
                .build())
        );

        return postResponseDtoList;
    }

}
