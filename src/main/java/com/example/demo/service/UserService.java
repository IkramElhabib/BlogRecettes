package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Recette;
import com.example.demo.model.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    
    public void addFavoriteRecipe(Recette recette);
    
}
