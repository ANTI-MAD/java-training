package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Data;

/**
 * @author Wladimir Litvinov
 */
@Data
public class CustomerSignUpRequest {

    private String email;
    private String password;
    private String fio;
	@JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;
}
