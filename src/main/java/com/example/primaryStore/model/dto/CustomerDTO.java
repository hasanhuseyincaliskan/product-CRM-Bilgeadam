package com.example.primaryStore.model.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    Long id;
    String name;
    String surname;
    Integer age;
    Date insertDate;
}
