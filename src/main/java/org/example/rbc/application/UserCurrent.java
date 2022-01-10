package org.example.rbc.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.rbc.domain.RoleType;
import org.example.rbc.domain.User;

@AllArgsConstructor
@NoArgsConstructor
public class UserCurrent {

    @Getter
    @Setter
    private User user;

    public boolean isLoggedIn() {
        return !user.getRole().equals(RoleType.ROLE_ANONYMOUS);
    }

    public String getName() {
        return user.getName();
    }

    public String getPassword() {
        return user.getPassword();
    }

}
