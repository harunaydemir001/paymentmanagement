package com.harun.common.dto;

import com.harun.common.enums.EventType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ReportDTO {
    String id;

    Long userId;

    EventType eventType;

    String message;

    Map<String, Object> metadata;
}
