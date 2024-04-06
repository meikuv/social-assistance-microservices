package meikuv.xyz.authservice.controller;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.dto.UserDTO;
import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.payload.response.MessageResponse;
import meikuv.xyz.authservice.service.IRefreshTokenService;
import meikuv.xyz.authservice.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IRefreshTokenService refreshTokenService;

    @GetMapping("/me")
    public UserDTO getConnectedUser(Principal connectedUser) {
        User user = userService.getUserByUsername(connectedUser.getName());
        return user.toDto();
    }

//    @PostMapping("/change-password")
//    public ResponseEntity<MessageResponse> changeUserPassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
//        userService.changeUserPassword(request, connectedUser);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new MessageResponse("Password updated successfully"));
//    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(Principal connectedUser) {
        User user = userService.getUserByUsername(connectedUser.getName());
        refreshTokenService.deleteByUserId(user.getId());

        SecurityContextHolder.clearContext();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("You have been logout"));
    }
}
