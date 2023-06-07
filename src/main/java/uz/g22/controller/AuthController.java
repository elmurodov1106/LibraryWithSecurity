package uz.g22.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.g22.dto.LoginDto;
import uz.g22.dto.UserRequestDto;
import uz.g22.entity.UserEntity;
import uz.g22.entity.UserRole;
import uz.g22.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<UserEntity> login(
            @RequestBody LoginDto loginDto
            ){
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/librarian/sign-up")
    public ResponseEntity<UserEntity> librarianSignUp(
            @RequestBody UserRequestDto userRequestDto
            ){
        return ResponseEntity.ok(userService.save(userRequestDto, List.of(UserRole.LIBRARIAN)));
    }

    @PostMapping("/user/sign-up")
    public ResponseEntity<UserEntity> userSignUp(
            @RequestBody UserRequestDto userRequestDto
    ){
        return ResponseEntity.ok(userService.save(userRequestDto,List.of(UserRole.USER)));
    }
}
