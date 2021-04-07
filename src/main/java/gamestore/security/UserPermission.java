package gamestore.security;

public enum UserPermission {
    GAME_READ("game:read"),
    GAME_WRITE("game:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
