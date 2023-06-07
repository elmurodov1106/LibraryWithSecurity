package uz.g22.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequestDto {
    private String name;

    private String username;

    private String password;

}
