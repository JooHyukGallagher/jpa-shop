package me.weekbelt.jpashop.web.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateMemberResponse {
    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
