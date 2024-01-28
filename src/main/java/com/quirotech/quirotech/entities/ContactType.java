package com.quirotech.quirotech.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public enum ContactType {

    @Enumerated(EnumType.STRING)
    EMAIL(1, "email"),
    PHONE_NUMBER(2, "phone");

    private final int id;
    private final String name;

    ContactType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ContactType fromId(int id) {
        for (ContactType type : ContactType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ContactType ID: " + id);
    }
}
