
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.repository.CategoryRepository;
import co.usa.ciclo3.ciclo3.modelo.Category;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
        
   @Autowired 
   private CategoryRepository categoryRepository;
   
   public List <Category> getAll(){
       return categoryRepository.getAll();
   }
   public Optional <Category> getCategory (int id){
       return categoryRepository.getCategory(id);
   }
   
   public Category save(Category category) { 
        if (category.getId()== null){
            return categoryRepository.save(category);
        }
        else
        {
            Optional<Category> co =  categoryRepository.getCategory(category.getId());
            if (co.isEmpty()){
                return categoryRepository.save(category);
            }
            else
            {
                return category;
            }
        }
    }
        
    public Category update (Category category){ 
        if(category.getId()!=null){
            Optional<Category>g=categoryRepository.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return categoryRepository.save(g.get());
            }
        }
        return category;
    }
     public boolean delete(int categoryId){
        Boolean d=getCategory(categoryId).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}