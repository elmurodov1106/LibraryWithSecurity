package uz.g22.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.g22.dto.LoginDto;
import uz.g22.dto.UserRequestDto;
import uz.g22.entity.UserEntity;
import uz.g22.entity.UserRole;
import uz.g22.exception.DataNotFoundException;
import uz.g22.repositoty.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntity save(UserRequestDto userDto, List<UserRole> roles){
        UserEntity map = modelMapper.map(userDto, UserEntity.class);
        map.setUserRoles(roles);
        map.setPassword(passwordEncoder.encode(map.getPassword()));
       return userRepository.save(map);
    }

    public UserEntity login(LoginDto loginDto){
        UserEntity userEntity = userRepository.findUserEntityByUsername(loginDto.getUsername())
                .orElseThrow( () -> new DataNotFoundException("user not found"));
        if (passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())){
            return userEntity;
        }
        throw new DataNotFoundException("user not found");
    }
    public Boolean Blocked(UUID id){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("user not found"));
        userEntity.setHasBlocked(true);
        return true;
    }

    public Boolean UnBlocked(UUID id){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("user not found"));
        userEntity.setHasBlocked(false);
        return true;
    }
}
