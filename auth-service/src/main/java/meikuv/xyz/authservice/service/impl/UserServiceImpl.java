package meikuv.xyz.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import meikuv.xyz.authservice.dto.UserDTO;
import meikuv.xyz.authservice.enums.ERole;
import meikuv.xyz.authservice.exception.HttpException;
import meikuv.xyz.authservice.model.Role;
import meikuv.xyz.authservice.model.User;
import meikuv.xyz.authservice.payload.request.ChangePasswordRequest;
import meikuv.xyz.authservice.payload.request.RegisterRequest;
import meikuv.xyz.authservice.repository.RoleRepository;
import meikuv.xyz.authservice.repository.UserRepository;
import meikuv.xyz.authservice.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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
                () -> new HttpException(HttpStatus.NOT_FOUND, "Пользователь не существует с указанным именем пользователя: " + username)
        );
    }

    @Override
    @Transactional
    public void enableUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new HttpException(HttpStatus.NOT_FOUND, "Пользователь не существует с указанным адресом электронной почты: " + email)
        );
        user.setIsEnabled(true);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Principal principal, UserDTO userDTO) {
        User user = getUserByUsername(principal.getName());
        user.update(userDTO);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request, Principal principal) {
        User user = getUserByUsername(principal.getName());

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new HttpException(HttpStatus.CONFLICT, "Неверный пароль");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new HttpException(HttpStatus.CONFLICT, "Пароль не совпадает");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new HttpException(HttpStatus.CONFLICT, "Текущий и новый пароли совпадают");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

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
                () -> new HttpException(HttpStatus.NOT_FOUND, "Роль не найдена")
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

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
