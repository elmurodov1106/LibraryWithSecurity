package uz.g22.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BookEntity extends BaseEntity{

    private String name;

    private String author;

    private Integer page;
}
