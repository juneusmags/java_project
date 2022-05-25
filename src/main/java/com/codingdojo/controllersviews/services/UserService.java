package com.codingdojo.controllersviews.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.controllersviews.models.Idea;
import com.codingdojo.controllersviews.models.LoginUser;
import com.codingdojo.controllersviews.models.User;
import com.codingdojo.controllersviews.repositories.IdeaRepository;
import com.codingdojo.controllersviews.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepo;
	
	@Autowired
	private IdeaRepository ideaRepo;
    
    public User register(User newUser, BindingResult result) {
        if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use!");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }
    
    public User login(LoginUser newLogin, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        User user = potentialUser.get();
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }
    
    public User findOneUser(Long id) {
    	return this.userRepo.findById(id).orElse(null);
    }
    
    public List<Idea> findAllIdeas(){
    	return (List<Idea>)this.ideaRepo.findAll();
    }
    
    public Idea createIdea(Idea idea) {
    	return this.ideaRepo.save(idea);
    }
    
    public Idea findOneIdea(Long id) {
    	return this.ideaRepo.findById(id).orElse(null);
    }
    
    public Idea updateIdea(Idea idea) {
    	return this.ideaRepo.save(idea);
    }
    
    public void delteIdea(Long id) {
    	this.ideaRepo.deleteById(id);
    }
}
