package uz.g22.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.g22.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/{userId}/block")
    public ResponseEntity<Boolean> block(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.Blocked(userId));
    }

    @PutMapping("/{userId}/unblock")
    public ResponseEntity<Boolean> unBlock(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.UnBlocked(userId));
    }
}
