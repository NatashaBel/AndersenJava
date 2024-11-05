package org.example.controller;

import org.example.model.UserStatus;
import org.example.model.ticket.Ticket;
import org.example.model.user.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @PutMapping("/{id}/status")
    public void updateUserStatus(@PathVariable UUID id, @RequestBody UserStatus userStatus) {
        userService.updateUserStatusById(id, userStatus);
    }

    @PostMapping("/{userId}/tickets")
    public ResponseEntity<String> updateUserAndCreateTicket(@PathVariable UUID userId, @RequestBody Ticket ticket) {
        Optional <User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            userService.updateUserAndCreateTicket(user.get(), ticket);
            return ResponseEntity.ok("User updated and ticket created successfully.");
        } catch (UnsupportedOperationException ex) {
            return ResponseEntity.status(403).body("Creating a ticket is disabled.");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}