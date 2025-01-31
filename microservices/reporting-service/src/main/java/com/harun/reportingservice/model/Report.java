package com.harun.reportingservice.model;

import com.harun.common.base.BaseId;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Document(indexName = "report-index")
public class Report extends BaseId<String> {

    @Field(type = FieldType.Text, name = "notification")
    String notification;
}
