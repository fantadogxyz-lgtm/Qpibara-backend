package com.qpibara.Qpibara.config.exepitions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@JsonFormat(shape = JsonFormat.Shape.NUMBER)
@AllArgsConstructor
@Getter
public enum BusinessCode {
    RESOURCE_NOT_FOUND(0, "Resource was not found"),
    INVALID_DATA(1, "Invalid data"),
    USER_ALREADY_EXISTS(2, "User already exists"),
    USER_NOT_FOUND(3, "User was not found");


    private final int value;
    private final String description;

}
