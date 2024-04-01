package com.a603.ofcourse.domain.member.service;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.domain.CoursePlace;
import com.a603.ofcourse.domain.course.domain.MyCourse;
import com.a603.ofcourse.domain.course.repository.CoursePlaceRepository;
import com.a603.ofcourse.domain.course.repository.CourseRepository;
import com.a603.ofcourse.domain.course.repository.MyCourseRepository;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.domain.Profile;
import com.a603.ofcourse.domain.member.domain.enums.Gender;
import com.a603.ofcourse.domain.member.dto.MyFavoriteCourse;
import com.a603.ofcourse.domain.member.dto.CoursePlaceDetails;
import com.a603.ofcourse.domain.member.dto.request.Preference;
import com.a603.ofcourse.domain.member.domain.NicknameHashMap;
import com.a603.ofcourse.domain.member.dto.request.ProfileInfoRequest;
import com.a603.ofcourse.domain.member.repository.ProfileRepository;
import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final NicknameHashMap nicknameHashMap;
    private final MyCourseRepository myCourseRepository;
    private final CourseRepository courseRepository;
    private final CoursePlaceRepository coursePlaceRepository;
    private final PlaceRepository placeRepository;

    private static final double HALF_AS_MANY = 1.5;
    private static final double ONCE = 1.0;
    private static final double HALF = 0.5;

    /*
    작성자 : 김은비
    작성내용 : 닉네임 중복 체크
     * @param nickname, memberId
     * @return 중복 아니면 false, 중복이면 true
     */
    public boolean checkNickName(String nickName) {
        return profileRepository.existsByNickname(nickName);
    }

    public boolean isDuplicateNickName(String nickName){
        //데이터베이스에 없고 해쉬맵에도 없으면
        if(!profileRepository.existsByNickname(nickName) && !nicknameHashMap.isExistInHashMap(nickName)){
            return false;
        }
        return true;
    }

    /*
    작성자 : 김은비
    작성내용 : 닉네임 해쉬맵에 저장
     * @prama nickname
     * @param memberId
     * @return 저장 성공하면 true, 실패하면 false
     */
    public boolean saveNicknameToHashMap(String nickname, Integer memberId){
        return nicknameHashMap.saveNicknameInHashMap(nickname, memberId);
    }

    /*
    작성자 : 김은비
    작성내용 : 닉네임 해쉬맵에서 삭제
     * @prama nickname
     */
    public void deleteNicknameFromHashMap(String nickname){
        nicknameHashMap.deleteNicknameFromHashMap(nickname);
    }

    /*
    작성자 : 김은비
    작성내용 : 내가 찜한 코스 리스트 반환
     * @param memberId
     */
    public List<MyFavoriteCourse> getMyFavoriteCourseList(Integer memberId){
            List<MyFavoriteCourse> myFavoriteCourseList = new ArrayList<>();

            List<MyCourse> myCourseList = myCourseRepository.findAllByMemberId(memberId);
            for(MyCourse myCourse : myCourseList){
                Course course = myCourse.getCourse();

                myFavoriteCourseList.add(
                        MyFavoriteCourse.from(
                                course.getId(),
                                course.getTitle(),
                                course.getImageUrl()
                        )
                );
            }

            return myFavoriteCourseList;
        }

    /*
    작성자 : 김은비
    작성내용 : 내가 찜한 코스 상세 내용 리스트 반환
     * @param courseId
     */
    public List<CoursePlaceDetails> getCoursePlaceDetailsList(int courseId){
        List<CoursePlaceDetails> coursePlaceDetailsList = new ArrayList<>();

        List<CoursePlace> coursePlaceList = coursePlaceRepository.findAllByCourseId(courseId);
        for(CoursePlace coursePlace : coursePlaceList){
            Place place = coursePlace.getPlace();

            coursePlaceDetailsList.add(
                    CoursePlaceDetails.from(
                            place.getPlaceCategory().getValue(),
                            place.getAddress(),
                            place.getImageUrl(),
                            place.getUrl()
                    )
            );
        }

        return coursePlaceDetailsList;
    }

    /**
     * @author 손현조
     * @date 2024-03-27
     * @description 프로필 저장
     **/
    public void saveMemberProfile(Member member, ProfileInfoRequest profileInfoRequest) {
        String vector = Arrays.toString(getVector(profileInfoRequest.getPreference()));
        Profile profile = Profile.builder()
                .member(member)
                .memberVector(vector)
                .gender(Gender.valueOf(profileInfoRequest.getGender()))
                .nickname(profileInfoRequest.getNickName())
                .build();
        profileRepository.save(profile);
        //저장 후 해쉬맵의 값 삭제
        nicknameHashMap.deleteNicknameFromHashMap(profileInfoRequest.getNickName());
    }

    /**
     * @author 손현조
     * @date 2024-03-27
     * @description 콜드 스타트용 벡터값 생성
     **/
    private Double[] getVector(Preference preference) {
        Double[] vector = new Double[41];
        Arrays.fill(vector, 0.0);
        String[] preferenceList = {
                preference.getFirst(),
                preference.getSecond(),
                preference.getThird()};

        int cnt = 0;
        for (String pref : preferenceList) {
            int[] indexes = getIndexes(pref);
            adjustVectorChange(cnt++, vector, indexes);
        }
        return vector;
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 사용자 선호도에 따른 벡터 분배
     **/
    private int[] getIndexes(String preference) {
        return switch (preference) {
            case "맛" -> new int[]{0, 7, 13, 18, 23, 26, 20, 32, 35, 37};
            case "가격" -> new int[]{1, 8, 14, 19};
            case "특별" -> new int[]{2, 9, 14, 20, 24, 27, 30, 33};
            case "위생" -> new int[]{3, 7, 15, 17};
            case "분위기" -> new int[]{4, 10, 11, 16, 21, 22, 25, 28, 31, 34, 36, 38, 39, 40};
            case "편의성" -> new int[]{5, 11, 16, 22, 12, 29};
            case "친절" -> new int[]{6, 12, 17};
            default -> new int[]{};
        };
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 선호순으로 가중치 부과
     **/
    private void adjustVectorChange(int cnt, Double[] vector, int[] indexes) {
        switch (cnt) {
            case 0:
                addWeight(vector, indexes, HALF_AS_MANY);
                break;
            case 1:
                addWeight(vector, indexes, ONCE);
                break;
            case 2:
                addWeight(vector, indexes, HALF);
                break;
        }
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 사용자 벡터에 가중치 추가
     **/
    private void addWeight(Double[] vector, int[] indexes, double weight) {
        for (int index : indexes) {
            vector[index] += weight;
        }
    }
}

