package com.harun.common.enums.error;

import com.harun.common.utils.StringBuilderUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {

    NOT_FOUND("{} Not Found!"),
    NOT_NULL("{} cannot be null!"),

    NOT_FOUND_WITH_ID("{} with ID {} not found!"),
    DELETION_SUCCESS("{} with ID {} has been successfully deleted."),
    DELETION_FAILED("Failed to delete {} with ID {}. Please try again later.");

    private final String message;

    public String getMessage(Object... args) {
        return StringBuilderUtil.buildMessage(this.message, args);
    }

    public String getMessage() {
        return StringBuilderUtil.buildMessage(this.message);
    }
}
