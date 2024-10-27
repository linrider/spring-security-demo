package ua.example.spring_security_demo.domain;

import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Application {
    int id;
    String name;
    String author;
    String version;



}
