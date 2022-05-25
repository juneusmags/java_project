package com.codingdojo.controllersviews.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.controllersviews.models.Idea;
import com.codingdojo.controllersviews.models.LoginUser;
import com.codingdojo.controllersviews.models.User;
import com.codingdojo.controllersviews.services.UserService;

@Controller
public class viewsController {
	
	@Autowired
    private UserService userServ;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model){
    	
    	Long loggedInId = (Long)session.getAttribute("user_id");
    	User loggedInUser = this.userServ.findOneUser(loggedInId);
    	model.addAttribute("userlog", loggedInUser);
    	
    	List<Idea> allIdeas = this.userServ.findAllIdeas();
    	model.addAttribute("allIdeas", allIdeas);
    	return "home.jsp";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
    @GetMapping("/ideas/new")
    public String newIdea(@ModelAttribute("idea")Idea idea) {
    	return "newIdea.jsp";
    }
    
    @PostMapping("/idea/create")
    public String createIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
    	if(result.hasErrors()) {
    		return "newIdea.jsp";
    	}
    	else{ 
    		Long idOfUser = (Long)session.getAttribute("user_id");
    		User logUser = this.userServ.findOneUser(idOfUser);
    		idea.setUploader(logUser);
    		this.userServ.createIdea(idea);
    		return "redirect:/home";
    	}
    }
    
    @GetMapping("/ideas/{id}")
    public String showIdea(@PathVariable("id") Long id, Model model) {
    	
    	Idea ideaObj = this.userServ.findOneIdea(id);
    	model.addAttribute("ideaObj", ideaObj);
    	return "showIdea.jsp";
    }
    
    @GetMapping("/ideas/{id}/edit")
    public String editIdea(@PathVariable("id")Long id, Model model) {
    	Idea ideaObj = this.userServ.findOneIdea(id);
    	model.addAttribute("ideaObj", ideaObj);
    	return "editIdea.jsp";
    }
    
    @PostMapping("/idea/{id}/update")
    public String updateIdea(@PathVariable("id")Long id, @Valid @ModelAttribute("ideaObj") Idea idea, BindingResult result) {
    	if(result.hasErrors()) {
    		return "editIdea.jsp";
    	}
    	else {
    		Idea mainIdea = this.userServ.findOneIdea(id);
    		idea.setUploader(mainIdea.getUploader());
    		this.userServ.updateIdea(idea);
    		return "redirect:/home";
    	}
    }
    
    @GetMapping("/idea/{id}/delete")
    public String deleteIdea(@PathVariable("id") Long id) {
    	this.userServ.delteIdea(id);
    	return "redirect:/home";
    }
}
