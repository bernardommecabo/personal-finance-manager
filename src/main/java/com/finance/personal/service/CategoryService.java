package com.finance.personal.service;

import com.finance.personal.dto.response.AccountDTOResponse;
import com.finance.personal.dto.response.CategoryDTOResponse;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.exception.DuplicatedItemException;
import com.finance.personal.exception.NotFoundException;
import com.finance.personal.model.UserEntity;
import com.finance.personal.repository.CategoryRepository;
import com.finance.personal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.personal.dto.request.CategoryDTORequest;
import com.finance.personal.model.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public CategoryDTOResponse createNewCategory(CategoryDTORequest request, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        CategoryEntity exist = categoryRepository.findByName(request.getName());
        if(exist != null){
            throw new DuplicatedItemException("Category already exists");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(request.getName());
        categoryEntity.setUser(user);
        categoryRepository.save(categoryEntity);
        return new CategoryDTOResponse(categoryEntity);
    }

    public List<CategoryDTOResponse> getAllCategory(Long userId) {
        CategoryEntity exist = categoryRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if(!exist.getUser().getId().equals(userId)) {
            throw new SecurityException("Access denied");
        }

        return categoryRepository.findAllByUserId(userId)
                .stream()
                .map(CategoryDTOResponse:: new)
                .toList();
    }

    public CategoryDTOResponse getCategoryById(Long categoryId, Long userId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category ID:" + categoryId + " not found"));

        if(!categoryEntity.getUser().getId().equals(userId)) {
            throw new SecurityException("Access denied");
        }
        CategoryDTOResponse response = new CategoryDTOResponse();
        response.setId(categoryId);
        response.setName(categoryEntity.getName());
        return response;
    }

    public CategoryDTOResponse updateCategory(Long id, String name, Long userId) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category ID:" + id + " not found"));

        if(!categoryEntity.getUser().getId().equals(userId)) {
            throw new SecurityException("Access denied");
        }
        categoryEntity.setName(name);
        categoryRepository.save(categoryEntity);
        return new CategoryDTOResponse(categoryEntity.getId(),categoryEntity.getName());
    }

    public MessageDTOResponse deleteCategoryById(Long id, Long userId){
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category ID:" + id + " not found"));

        if(!categoryEntity.getUser().getId().equals(userId)) {
            throw new SecurityException("Access denied");
        }
        MessageDTOResponse messageDTOResponse = new MessageDTOResponse();
        categoryRepository.deleteById(id);
        messageDTOResponse.setMessage("Category ID:" + id + " has been deleted");
        return messageDTOResponse;
    }
}
