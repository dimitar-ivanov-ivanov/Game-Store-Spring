package gamestore.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserPermission {
    GAME_READ("game:read"),
    GAME_WRITE("game:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    public String getPermission() {
        return permission;
    }
}
