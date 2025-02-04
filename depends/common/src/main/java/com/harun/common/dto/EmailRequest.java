package com.harun.common.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class EmailRequest implements Serializable {
    String recipient;
    String msgBody;
    String subject;
    String attachment;
}
