package by.onyx.common.pojo.profile;

import lombok.Getter;

public enum Role {
    SUPER_ADMIN("SUPER_ADMIN"),
    USER("USER"),
    GUEST("GUEST");

    @Getter
    private String name;

    Role(final String name) {
        this.name = name;
    }

}
