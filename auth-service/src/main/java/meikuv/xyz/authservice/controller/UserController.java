package meikuv.xyz.authservice.controller;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.dto.UserDTO;
import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.payload.request.ChangePasswordRequest;
import meikuv.xyz.authservice.payload.response.MessageResponse;
import meikuv.xyz.authservice.service.IRefreshTokenService;
import meikuv.xyz.authservice.service.IS3Service;
import meikuv.xyz.authservice.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IRefreshTokenService refreshTokenService;
    private final IS3Service s3Service;

    @GetMapping("/connected-user")
    public UserDTO getConnectedUser(Principal connectedUser) {
        User user = userService.getUserByUsername(connectedUser.getName());
        return user.toDto();
    }

    @PostMapping(path = "/upload-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UserDTO uploadFile(@RequestParam("file") MultipartFile file, Principal connectedUser) {
        User user = userService.getUserByUsername(connectedUser.getName());
        s3Service.uploadFile(file.getOriginalFilename(), file);
        String url = s3Service.getFile(file.getOriginalFilename());
        s3Service.deleteFile(user.getPhotoName());
        user.setPhotoUrl(url);
        user.setPhotoName(file.getOriginalFilename());
        userService.save(user);
        return user.toDto();
    }

    @GetMapping("/view/{fileName}")
    public String viewFile(@PathVariable String fileName) {
        return s3Service.getFile(fileName);
    }

    @PostMapping("/change-password")
    public ResponseEntity<MessageResponse> changeUserPassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("Password updated successfully"));
    }

    @PostMapping("/profile-update")
    public UserDTO updateProfile(Principal principal, @RequestBody UserDTO userDTO) {
        userService.updateUser(principal, userDTO);
        return userDTO;
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(Principal connectedUser) {
        User user = userService.getUserByUsername(connectedUser.getName());
        refreshTokenService.deleteByUserId(user.getId());

        SecurityContextHolder.clearContext();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("Вы вышли из системы"));
    }
}
