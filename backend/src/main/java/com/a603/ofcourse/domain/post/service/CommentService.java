package com.a603.ofcourse.domain.post.service;

import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.domain.Profile;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.post.domain.Comment;
import com.a603.ofcourse.domain.post.domain.Post;
import com.a603.ofcourse.domain.post.dto.CommentRequestDto;
import com.a603.ofcourse.domain.post.dto.CommentResponseDto;
import com.a603.ofcourse.domain.post.repository.CommentRepository;
import com.a603.ofcourse.domain.post.repository.PostRepository;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.a603.ofcourse.domain.member.exception.MemberErrorCode.MEMBER_DOES_NOT_EXISTS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final JwtTokenService jwtTokenService;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    /* 댓글 등록 */
    @Transactional
    public Integer save(String accessToken, CommentRequestDto requestDto){
        Integer memberId = jwtTokenService.getMemberId(accessToken);
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(MEMBER_DOES_NOT_EXISTS)
        );

        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                () -> new InternalException("예외 처리")
        );

        return commentRepository.save(Comment.builder()
                .content(requestDto.getContent())
                .member(member)
                .post(post)
                .build()).getId();
    }


    /* 댓글 조회 */
    public List<CommentResponseDto> findComment(Integer postId){

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        commentRepository.findByPostId(postId).forEach(c ->
            {
                Profile profile = c.getMember().getProfile();

                commentResponseDtoList.add(CommentResponseDto
                        .builder()
                        .memberNickname(profile.getNickname())
                        .memberImageUrl(profile.getImage())
                        .content(c.getContent())
                        .build()
                );
            }
        );

        return commentResponseDtoList;
    }
}
