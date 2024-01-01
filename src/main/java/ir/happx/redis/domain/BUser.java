package ir.happx.redis.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class BUser {

    @Id
    private String id;
    private String firstNamr;
    private String lastName;
    private String phone;
    private String email;
}
