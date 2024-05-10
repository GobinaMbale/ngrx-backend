package com.eda.ngrx.controller;

import com.eda.ngrx.api.UserApi;
import com.eda.ngrx.dto.UserDTO;
import com.eda.ngrx.mapper.UserMapper;
import com.eda.ngrx.model.User;
import com.eda.ngrx.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserDTO> createUser(@Valid UserDTO user) {
        System.out.println("createUser");
        return ResponseEntity.ok(userMapper.entityToDTO(userRepository.save(userMapper.dtoToEntity(user))));
    }

    @Override
    public ResponseEntity<UserDTO> createUsersWithListInput(@Valid List<@Valid UserDTO> user) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        userRepository.delete(userRepository.findByUsername(username));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<UserDTO> getUserByName(String username) {
        System.out.println("getUserByName: " + username);
        return ResponseEntity.ok(userMapper.entityToDTO(userRepository.findByUsername(username)));
    }

    @Override
    public ResponseEntity<String> loginUser(@Valid String username, @Valid String password) {
        return ResponseEntity.ok(userRepository.findByUsernameAndPassword(username, password));
    }

    @Override
    public ResponseEntity<Void> logoutUser() {
        return null;
    }

    @Override
    public ResponseEntity<List<UserDTO>> listUsers(String username, Pageable pageable) {
        System.out.println("listUsers: " + username);
        if(StringUtils.hasText(username)) {
            return ResponseEntity.ok(userRepository.findAllByUsername(username)
                    .stream().map(userMapper::entityToDTO).collect(Collectors.toList()));
        }
        return ResponseEntity.ok(userRepository.findAll()
                .stream().map(userMapper::entityToDTO).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Void> updateUser(String username, @Valid UserDTO user) {
        System.out.println("updateUser: " + username);
        User getUser = userRepository.findByUsername(username);
        getUser.setEmail(user.getEmail());
        getUser.setFirstName(user.getFirstName());
        getUser.setLastName(user.getLastName());
        getUser.setPhone(user.getPhone());
        getUser.setPassword(user.getPassword());
        getUser.setUsername(user.getUsername());
        userRepository.save(getUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
