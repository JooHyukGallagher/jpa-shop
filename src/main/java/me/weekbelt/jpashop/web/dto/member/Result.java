package me.weekbelt.jpashop.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class Result {
    private List<MemberDto> data;
}
