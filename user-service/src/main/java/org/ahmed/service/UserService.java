package org.ahmed.service;

import lombok.RequiredArgsConstructor;
import org.ahmed.dto.UserDTO;
import org.ahmed.exception.ResourceNotFoundException;
import org.ahmed.model.User;
import org.ahmed.repository.UserRepository;
import org.ahmed.util.EntityMapper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityMapper mappingHelper;

    @Transactional(readOnly = true)
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mappingHelper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(mappingHelper::userToUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

    }

    @Transactional
    public UserDTO createUser(UserDTO user) {
        User userCreated = userRepository.save(
                mappingHelper.userDtoToUser(user));
        return mappingHelper.userToUserDto(userCreated);
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(Long id, UserDTO userDetails) {
        UserDTO existingUser = getUserById(id);
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setRoleName(userDetails.getRoleName());
        // Set other properties as needed
        return userRepository.save(mappingHelper.userDtoToUser(existingUser));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Async method for bulk employee creation
    @Async
    public void createUsersAsync(List<User> users) {
        userRepository.saveAll(users);
    }

    // Async method for bulk employee update
    @Async
    public void updateUsersAsync(List<User> users) {
        userRepository.saveAll(users);
    }

    // Async method for bulk employee deletion
    @Async
    public void deleteUsersAsync(List<Long> ids) {
        userRepository.deleteEmployeesByIdIn(ids);
    }
}
