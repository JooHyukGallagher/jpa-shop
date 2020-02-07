package me.weekbelt.jpashop.service;

import me.weekbelt.jpashop.domain.member.Member;
import me.weekbelt.jpashop.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원가입")
    @Test
    public void createMember() throws Exception {
        //given
        Member member = Member.builder()
                .name("kim")
                .build();

        //when
        Long saveId = memberService.join(member);

        //then
        assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }

    @DisplayName("중복회원 예외")
    @Test
    public void createMemberException() throws Exception {
        //given
        Member member1 = Member.builder().name("kim").build();
        Member member2 = Member.builder().name("kim").build();

        //when
        memberService.join(member1);

        //then
        assertThatThrownBy(() -> { memberService.join(member2); })
                .isInstanceOf(IllegalStateException.class).hasMessage("이미 존재하는 회원입니다.");
    }
}