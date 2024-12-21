package org.ahmed.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.ahmed.dto.UserDTO;
import org.ahmed.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/user/hateoas")
public class UserHateoasController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "List User")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * @param id : user id
     * @return : returns user with subsequent actions which can be performed
     * User DTO with link (url) to get all users
     */
    @GetMapping("/{id}")
    public EntityModel<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        EntityModel<UserDTO> userEntityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(link.withRel("user-list"));
        return userEntityModel;
    }
}
