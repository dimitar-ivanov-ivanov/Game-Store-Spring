package gamestore.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            UserPermission.values()
    )),
    ADMIN_TRAINEE(Sets.newHashSet(
            UserPermission.GAME_READ,
            UserPermission.USER_READ
    ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(
                        permission.getPermission()
                ))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority(
                "ROLE_" + this.name()
        ));

        return authorities;
    }
}
