package org.ahmed.controller;

import lombok.AllArgsConstructor;
import org.ahmed.model.User;
import org.ahmed.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/async/users")
@AllArgsConstructor
public class UserAsyncController {

    private final UserService userService;

    // Async endpoint for bulk employee creation
    @PostMapping("/create")
    public ResponseEntity<String> createUsers(@RequestBody List<User> users) {
        userService.createUsersAsync(users);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bulk creation request accepted");
    }

    // Async endpoint for bulk employee update
    @PutMapping("/update")
    public ResponseEntity<String> updateUsers(@RequestBody List<User> users) {
        userService.updateUsersAsync(users);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bulk update request accepted");
    }

    // Async endpoint for bulk employee deletion
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUsers(@RequestParam List<Long> ids) {
        userService.deleteUsersAsync(ids);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bulk deletion request accepted");
    }
}

