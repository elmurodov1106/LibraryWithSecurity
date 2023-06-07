package uz.g22.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookRequestDto {

    private String name;

    private String author;

    private Integer page;
}
