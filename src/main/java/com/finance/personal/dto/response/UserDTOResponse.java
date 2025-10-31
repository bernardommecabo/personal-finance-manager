package com.finance.personal.dto.response;

import com.finance.personal.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {
    private Long id;
    private String name;
    private String email;

    public UserDTOResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
    }
}
