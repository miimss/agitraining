package com.example.agitraining.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.agitraining.model.User;
import com.example.agitraining.model.UserDto;
import com.example.agitraining.model.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository repository;

	//Go to registration
	@RequestMapping(value = "/register")
    public String addBook(Model model){
    	model.addAttribute("userDto", new UserDto());
        return "register";
    }
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) {
    		if (userDto.getPassword().equals(userDto.getPasswordCheck())) { //Check if password matches		
	    		String pwd = userDto.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(userDto.getUsername());
		    	newUser.setRole("USER");
		    	if (repository.findByUsername(userDto.getUsername()) == null) { //Check if user already exists
		    		repository.save(newUser);
		    	} else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "register";		    		
		    	}
    		} else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "register";
    		}
    	} else {
    		return "register";
    	}
    	
    	return "redirect:/login";    	
    }
}
