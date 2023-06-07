package uz.g22.repositoty;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.g22.entity.BookEntity;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    List<BookEntity> searchBookEntitiesByNameContainingIgnoreCase(String name, Pageable page);
}
