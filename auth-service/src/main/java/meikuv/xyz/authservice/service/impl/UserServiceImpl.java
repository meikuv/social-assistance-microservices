package meikuv.xyz.authservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.enums.ERole;
import meikuv.xyz.authservice.exception.HttpException;
import meikuv.xyz.authservice.model.Role;
import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.payload.request.RegisterRequest;
import meikuv.xyz.authservice.repository.RoleRepository;
import meikuv.xyz.authservice.repository.UserRepository;
import meikuv.xyz.authservice.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "User is not exists with given id: " + id)
        );
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "User is not exists with given username: " + username)
        );
    }

    @Override
    @Transactional
    public void enableUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "User is not exists with given email: " + email)
        );
        user.setIsEnabled(true);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void createUser(RegisterRequest request) {
        Role role = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "Role is not found")
        );

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = User.builder()
                .username(request.getUsername()).email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles).isEnabled(false).isLocked(false)
                .build();
        userRepository.save(user);
    }
}
