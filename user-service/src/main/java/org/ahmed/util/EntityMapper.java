package org.ahmed.util;

import lombok.RequiredArgsConstructor;
import org.ahmed.dto.UserDTO;
import org.ahmed.model.Role;
import org.ahmed.model.User;
import org.ahmed.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityMapper {

    private final RoleRepository roleRepository;

    public UserDTO userToUserDto(User user) {
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .address(user.getAddress())
                .roleName(user.getRole().getRoleName())
                .build();
    }

    public User userDtoToUser(UserDTO userDTO) {
        Role role = roleRepository.findByRoleName(userDTO.getRoleName());
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .gender(userDTO.getGender())
                .address(userDTO.getAddress())
                .role(role)
                .build();
    }

}
