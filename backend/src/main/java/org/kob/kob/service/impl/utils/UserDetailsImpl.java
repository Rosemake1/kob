package org.kob.kob.service.impl.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kob.kob.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private User user;

    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of();
        }

    @Override
    public String getPassword() {
        return user.getPassword();
    }



    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //账户未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账户可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
