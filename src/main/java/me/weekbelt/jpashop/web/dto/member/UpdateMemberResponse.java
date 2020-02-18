package me.weekbelt.jpashop.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class UpdateMemberResponse {
    private Long id;
    private String name;
}
