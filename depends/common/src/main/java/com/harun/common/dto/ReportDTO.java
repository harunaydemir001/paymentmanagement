package com.harun.common.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ReportDTO {

    String id;

    String notification;

}
