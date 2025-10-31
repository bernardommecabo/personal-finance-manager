package com.finance.personal.service;

import com.finance.personal.dto.request.UserDTORequest;
import com.finance.personal.dto.request.UserProfileDTORequest;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.dto.response.UserDTOResponse;
import com.finance.personal.exception.DuplicatedItemException;
import com.finance.personal.exception.NotFoundException;
import com.finance.personal.model.UserEntity;
import com.finance.personal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTOResponse createNewUser(UserDTORequest user) {
        List<String> validationErrors = new ArrayList<>();
        if (userRepository.existsByName(user.getName())) {
            validationErrors.add("This username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            validationErrors.add("This email already exists");
        }
        if (!validationErrors.isEmpty()) {
            String errorMessage = String.join(", ", validationErrors);
            throw new DuplicatedItemException(errorMessage);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userRepository.save(userEntity);
        return new UserDTOResponse(userEntity);
    }

    public List<UserDTOResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(UserDTOResponse::new)
                .collect(Collectors.toList());
    }

    public UserDTOResponse getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User ID:" + id + " not found"));
        return new UserDTOResponse(userEntity);
    }

    public UserDTOResponse updateUserProfile(Long id, UserProfileDTORequest userDTORequest) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User ID:" + id + " not found"));
        if (userDTORequest.getName() != null) {
            UserEntity nameExists = userRepository.findByName(userDTORequest.getName());
            if (nameExists != null && !nameExists.getId().equals(id)) {
                throw new DuplicatedItemException("Username already exists");
            }
            userEntity.setName(userDTORequest.getName());
        }
        if (userDTORequest.getEmail() != null) {
            UserEntity emailExists = userRepository.findByEmail(userDTORequest.getEmail());
            if (emailExists != null && !emailExists.getId().equals(id)) {
                throw new DuplicatedItemException("Email already exists");
            }
            userEntity.setEmail(userDTORequest.getEmail());
        }
        userRepository.save(userEntity);
        return new UserDTOResponse(userEntity);
    }

    //Update password method

    public MessageDTOResponse deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User ID:" + id + " not found");
        }
        MessageDTOResponse messageDTOResponse = new MessageDTOResponse();
        userRepository.deleteById(id);
        messageDTOResponse.setMessage("User ID:" + id + " has been deleted");
        return messageDTOResponse;
    }
}
