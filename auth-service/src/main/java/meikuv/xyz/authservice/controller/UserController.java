package meikuv.xyz.authservice.controller;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.dto.UserDTO;
import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.payload.request.ChangePasswordRequest;
import meikuv.xyz.authservice.payload.response.MessageResponse;
import meikuv.xyz.authservice.service.IRefreshTokenService;
import meikuv.xyz.authservice.service.IS3Service;
import meikuv.xyz.authservice.service.IUserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IRefreshTokenService refreshTokenService;
    private final IS3Service s3Service;

    @GetMapping("/me")
    public UserDTO getConnectedUser(Principal connectedUser) {
        User user = userService.getUserByUsername(connectedUser.getName());
        return user.toDto();
    }

    @GetMapping
    public String health() {
        return "UP";
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        s3Service.uploadFile(file.getOriginalFilename(), file);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("User image uploaded successfully"));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(s3Service.getFile(fileName).getObjectContent()));
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<InputStreamResource> viewFile(@PathVariable String fileName) {
        var s3Object = s3Service.getFile(fileName);
        var content = s3Object.getObjectContent();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+fileName+"\"")
                .body(new InputStreamResource(content));
    }

    @PostMapping("/change-password")
    public ResponseEntity<MessageResponse> changeUserPassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("Password updated successfully"));
    }

    @PostMapping("/profile-update")
    public ResponseEntity<MessageResponse> updateProfile(Principal principal, @RequestBody UserDTO userDTO) {
        userService.updateUser(principal, userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Данные обновились"));
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
