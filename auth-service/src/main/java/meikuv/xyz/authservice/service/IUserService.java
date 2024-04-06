package meikuv.xyz.authservice.service;

import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.payload.request.RegisterRequest;

public interface IUserService {
    User getUserById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User getUserByUsername(String username);

    void enableUser(String email);

    void deleteUserById(Long id);

    void createUser(RegisterRequest request);
}
