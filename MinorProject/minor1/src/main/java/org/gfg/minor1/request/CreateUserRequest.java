package org.gfg.minor1.request;

import lombok.*;
import org.gfg.minor1.model.User;
import org.gfg.minor1.model.UserState;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    String name;

    @NotBlank(message = "mobile no of a user cannot be blank")
    String mobile;

    @NotBlank(message = "email of a user cannot be blank")
    String email;

    public User toUser() {
       return User.builder().
               name(this.name).
               mail(this.email).
               mobile(this.mobile).
               status(UserState.ACTIVE).
               build();
    }
}
