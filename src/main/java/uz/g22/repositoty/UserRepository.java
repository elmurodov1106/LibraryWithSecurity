package uz.g22.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.g22.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserEntityByUsername(String username);
}
