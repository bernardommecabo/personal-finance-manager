package com.finance.personal.service;

import com.finance.personal.dto.response.CategoryDTOResponse;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.exception.DuplicatedItemException;
import com.finance.personal.exception.NotFoundException;
import com.finance.personal.repository.CategoryRepository;
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

    public CategoryDTOResponse createNewCategory(CategoryDTORequest request){
        CategoryEntity exist = categoryRepository.findByName(request.getName());
        if(exist != null){
            throw new DuplicatedItemException("Category already exists");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(request.getName());
        categoryRepository.save(categoryEntity);
        return new CategoryDTOResponse(categoryEntity.getId(),categoryEntity.getName());
    }

    public List<CategoryDTOResponse> getAllCategory(){
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntities.stream()
                .map(CategoryDTOResponse::new)
                .collect(Collectors.toList());
    }

    public CategoryDTOResponse getCategoryById(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category ID:" + id + " not found"));
        CategoryDTOResponse response = new CategoryDTOResponse();
        response.setId(id);
        response.setName(categoryEntity.getName());
        return response;
    }

    public CategoryDTOResponse updateCategory(Long id, String name){
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category ID:" + id + " not found"));
        categoryEntity.setName(name);
        categoryRepository.save(categoryEntity);
        return new CategoryDTOResponse(categoryEntity.getId(),categoryEntity.getName());
    }

    public MessageDTOResponse deleteCategoryById(Long id){
        if(!categoryRepository.existsById(id)){
            throw new NotFoundException("Category ID:" + id + " not found");
        }
        MessageDTOResponse messageDTOResponse = new MessageDTOResponse();
        categoryRepository.deleteById(id);
        messageDTOResponse.setMessage("Category ID:" + id + " has been deleted");
        return messageDTOResponse;
    }
}
