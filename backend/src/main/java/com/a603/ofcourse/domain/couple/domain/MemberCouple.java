package com.a603.ofcourse.domain.couple.domain;

import com.a603.ofcourse.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert //null 값 배제 for 디폴트 설정
public class MemberCouple {
    @Id
    @Column(name = "member_couple_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    private Couple couple;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberCouple(Couple couple, Member member){
        addRelatedCouple(couple);
        addRelatedMember(member);
    }

    private void addRelatedCouple(Couple couple){
        this.couple = couple;
        couple.getMemberCoupleList().add(this);
    }

    private void addRelatedMember(Member member){
        this.member = member;
        member.updateMemberCouple(this);
    }
}
