package meikuv.xyz.authservice.service;

import meikuv.xyz.authservice.dto.UserDTO;
import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.payload.request.ChangePasswordRequest;
import meikuv.xyz.authservice.payload.request.RegisterRequest;

import java.security.Principal;

public interface IUserService {
    User getUserById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User getUserByUsername(String username);

    void enableUser(String email);

    void updateUser(Principal principal, UserDTO userDTO);

    void changePassword(ChangePasswordRequest request, Principal principal);

    void deleteUserById(Long id);

    void createUser(RegisterRequest request);
}
