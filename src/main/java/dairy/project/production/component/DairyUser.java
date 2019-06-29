package dairy.project.production.component;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dairy.project.production.entity.AccountDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class DairyUser implements UserDetails {

    private String name;

    private String email;

    private boolean isEnabled;


    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public DairyUser( String name, String email, String password, boolean isEnabled,Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.email = email;
        this.isEnabled = isEnabled;
        this.password = password;
        this.authorities = authorities;
    }



    public static DairyUser build(AccountDetails account) {
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new DairyUser(
                 account.getEmployee().getName(),
                account.getEmployee().getEmail(),
                 account.getPassword(),
                 account.isEnabled(),
                authorities
        );
    }


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
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DairyUser user = (DairyUser) o;
        return Objects.equals(email, user.email);
    }
}
