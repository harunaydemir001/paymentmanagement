package com.harun.common.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailRequest implements Serializable {
    String recipient;
    String msgBody;
    String subject;
    String attachment;
}
