package com.a603.ofcourse.domain.schedule.service;

import com.a603.ofcourse.domain.couple.domain.Couple;
import com.a603.ofcourse.domain.couple.exception.CoupleErrorCode;
import com.a603.ofcourse.domain.couple.exception.CoupleException;
import com.a603.ofcourse.domain.couple.repository.MemberCoupleRepository;
import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.service.CourseService;
import com.a603.ofcourse.domain.oauth.exception.OauthErrorCode;
import com.a603.ofcourse.domain.oauth.exception.OauthException;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.schedule.domain.Schedule;
import com.a603.ofcourse.domain.schedule.dto.request.AddScheduleRequestDto;
import com.a603.ofcourse.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.a603.ofcourse.domain.schedule.dto.response.ScheduleListResponseDto;
import com.a603.ofcourse.domain.schedule.exception.ScheduleErrorCode;
import com.a603.ofcourse.domain.schedule.exception.ScheduleException;
import com.a603.ofcourse.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberCoupleRepository memberCoupleRepository;
    private final CourseService courseService;
    private final JwtTokenService jwtTokenService;

    @Transactional
    public void addNewSchedule(String token, AddScheduleRequestDto addScheduleRequestDto) {
        // get memberId from JWT
        Integer memberId = (Integer) jwtTokenService.getPayload(token).get("member_id");
        log.info("memberId : {}", memberId);

        // get couple from MemberCouple table
        Couple couple = memberCoupleRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CoupleException(CoupleErrorCode.NOT_FOUND_ID))
                .getCouple();
        log.info("couple : {}", couple.getId());

        // get course by courseId
        Course course = courseService.findById(addScheduleRequestDto.getCourseId());
        log.info("course : {}", course.getId());
        log.info("코스 추가 완료");
        // save new Schedule
        scheduleRepository.save(Schedule.builder()
                .scheduleTitle(addScheduleRequestDto.getScheduleTitle())
                .appointmentPlace(addScheduleRequestDto.getAppointmentPlace())
                .scheduleDate(addScheduleRequestDto.getScheduleDate())
                .course(course)
                .couple(couple)
                .build());
    }

    public ScheduleListResponseDto findScheduleList(String token, int month) {
        // get couple id
        int coupleId = jwtTokenService.getCoupleId(token);
        log.info("couple : {}", coupleId);

        // get schedules by coupleId and month
        return ScheduleListResponseDto.builder()
                .scheduleDetailResponseDtoList(
                        scheduleRepository.findByCoupleIdAndMonth(coupleId, month).stream()
                                .map(Schedule::toResponse)
                                .toList()
                ).build();
    }

    @Transactional
    public void updateSchedule(String token, UpdateScheduleRequestDto updateScheduleRequestDto) {
        // get schedule
        Schedule schedule = scheduleRepository.findById(updateScheduleRequestDto.getScheduleId())
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.SCHEDULE_NOT_EXIST));

        // is authorized?
        if(!schedule.getCouple()
                .getId().equals(jwtTokenService.getCoupleId(token)))
            throw new OauthException(OauthErrorCode.UNAUTHORIZED);

        // if course is changed
        if(!schedule.getCourse().getId().equals(updateScheduleRequestDto.getCourseId()))
            schedule.setCourse(courseService.findById(updateScheduleRequestDto.getCourseId()));

        // update info
        schedule.update(updateScheduleRequestDto);
    }

    @Transactional
    public void deleteSchedule(String token, int scheduleId) {
        // get schedule and check is present
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ScheduleErrorCode.SCHEDULE_NOT_EXIST));

        // is authorized?
        if(!schedule.getCouple()
                .getId().equals(jwtTokenService.getCoupleId(token)))
            throw new OauthException(OauthErrorCode.UNAUTHORIZED);

        scheduleRepository.deleteById(scheduleId);
    }
}
