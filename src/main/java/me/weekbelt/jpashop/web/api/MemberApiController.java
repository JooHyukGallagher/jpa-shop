package me.weekbelt.jpashop.web.api;

import lombok.RequiredArgsConstructor;
import me.weekbelt.jpashop.domain.member.Member;
import me.weekbelt.jpashop.service.MemberService;
import me.weekbelt.jpashop.web.dto.CreateMemberRequest;
import me.weekbelt.jpashop.web.dto.CreateMemberResponse;
import me.weekbelt.jpashop.web.dto.UpdateMemberRequest;
import me.weekbelt.jpashop.web.dto.UpdateMemberResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = Member.builder()
                .name(request.getName())
                .build();

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request){
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }
}
