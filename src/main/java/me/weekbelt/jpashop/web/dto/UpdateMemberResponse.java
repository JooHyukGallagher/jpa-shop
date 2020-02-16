package me.weekbelt.jpashop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class UpdateMemberResponse {
    private Long id;
    private String name;
}
