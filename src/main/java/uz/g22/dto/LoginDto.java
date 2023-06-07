package uz.g22.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class LoginDto {

    private String username;
    private String password;
}
