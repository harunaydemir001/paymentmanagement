package com.harun.reportingservice.model;

import com.harun.common.enums.EventType;
import com.harun.entity.base.fields.BaseDate;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Document(indexName = "report-index")
public class Report extends BaseDate<String> {
    @Field(type = FieldType.Long, name = "userId")
    Long userId;
    @Field(type = FieldType.Text, name = "eventType")
    @Enumerated(EnumType.STRING)
    EventType eventType;
    @Field(type = FieldType.Text, name = "message")
    String message;
    Map<String, Object> metadata;
}
