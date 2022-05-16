package com.nttdatabootcamp.springwithmongodb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String age;
    private String type;
}
