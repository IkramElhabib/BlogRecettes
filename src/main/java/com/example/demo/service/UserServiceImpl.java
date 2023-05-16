package com.example.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.Favoris;
import com.example.demo.model.Recette;
import com.example.demo.repository.FavorisRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.TbConstants;

@Service
public class UserServiceImpl implements UserService{
	
	 @Autowired
	    private UserRepository userRepository;

	 	Recette recette;
	    @Autowired
	    private RoleRepository roleRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    private FavorisRepository favorisRepository;

		@Override
		public void saveUser(UserDto userDto) {
			  Role role = roleRepository.findByName(TbConstants.Roles.USER);

		        if (role == null)
		            role = roleRepository.save(new Role(TbConstants.Roles.USER));

		        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
		                Arrays.asList(role));
		        userRepository.save(user);
			
		}

		@Override
		public User findUserByEmail(String email) {
			// TODO Auto-generated method stub
	        return userRepository.findByEmail(email);
		}

		@Override
		public void addFavoriteRecipe(Recette recette) {
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			    String email = authentication.getName();
			    User user = userRepository.findByEmail(email);
			    Favoris favoris = new Favoris(recette, user);
			    favorisRepository.save(favoris);
			
		}
		
}
