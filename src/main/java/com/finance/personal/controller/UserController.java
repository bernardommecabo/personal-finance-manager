package com.finance.personal.controller;

import com.finance.personal.dto.request.UserDTORequest;
import com.finance.personal.dto.request.UserProfileDTORequest;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.dto.response.UserDTOResponse;
import com.finance.personal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTOResponse> createNewUser(@RequestBody @Valid UserDTORequest request) {
        UserDTOResponse userDTOResponse = userService.createNewUser(request);
        return new ResponseEntity<UserDTOResponse>(userDTOResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getAllUsers() {
        List<UserDTOResponse> userDTOResponses = userService.getAllUsers();
        return new ResponseEntity<List<UserDTOResponse>>(userDTOResponses, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable Long id) {
        UserDTOResponse userDTOResponse =  userService.getUserById(id);
        return new ResponseEntity<UserDTOResponse>(userDTOResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UserProfileDTORequest userProfileDTORequest) {
        UserDTOResponse userDTOResponse = userService.updateUserProfile(id,userProfileDTORequest);
        return new ResponseEntity<UserDTOResponse>(userDTOResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTOResponse> deleteUser(@PathVariable Long id) {
        MessageDTOResponse messageDTOResponse = userService.deleteUserById(id);
        return new ResponseEntity<MessageDTOResponse>(messageDTOResponse, HttpStatus.OK);
    }
}
