package ru.bryanin.dev.questionnairebackend.office.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.bryanin.dev.questionnairebackend.office.model.user.BasicUser;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromBasicUser(BasicUser basicUser) {
        return new User(
                basicUser.getEmail(),
                basicUser.getPassword(),
                basicUser.getAccessStatus().equals(AccessStatus.ACTIVE),
                basicUser.getAccessStatus().equals(AccessStatus.ACTIVE),
                basicUser.getAccessStatus().equals(AccessStatus.ACTIVE),
                basicUser.getAccessStatus().equals(AccessStatus.ACTIVE),
                basicUser.getSecurityRole().getAuthorities()
                );
    }
}
