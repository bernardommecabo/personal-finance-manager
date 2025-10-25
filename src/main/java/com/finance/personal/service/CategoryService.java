package com.finance.personal.service;

import com.finance.personal.exception.DuplicatedItemException;
import com.finance.personal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.personal.dto.request.CategoryDTORequest;
import com.finance.personal.model.CategoryEntity;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity createNewCategory(CategoryDTORequest request){
        CategoryEntity exist = categoryRepository.findByName(request.getName());
        if(exist != null){
            throw new DuplicatedItemException("Category already exists");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(request.getName());
        return categoryRepository.save(categoryEntity);
    }

    //findAll que retorna a lista com todas as categorias

    //findByID, recebe um ID como parâmetro, verifica no banco e se não existir = categoria not found
    //se ID existir devolve ID, nome

    //Method - Deletar categoria pelo ID, recebe ID como parâmetro, se não existir = categoria not found
    // se ID existir = deleta e devolve message(Categoria {nome} deletada com sucesso)
}
