package pe.edu.unfv.mypersonablog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import pe.edu.unfv.mypersonablog.entity.UserEntity;
import pe.edu.unfv.mypersonablog.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/record")
    public String recordPage(){
        return "/users/register";
    }

    @PostMapping("/register")
    public String register(UserEntity user){
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = {"/login", "/"})
    public String loginPage(){
        return "/users/login";
    }
    
    @GetMapping("/access")
    public String access(HttpSession session) {
    	Optional<UserEntity> optionalUser = userService.getUserById(Long.parseLong(session.getAttribute("user_session_id").toString()));
    	
    	if (optionalUser.isPresent()) {
			session.setAttribute("user_session_id", optionalUser.get().getId());
			return "redirect:/post/home";
		} else {
			return "redirect:/login";
		}
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
    	HttpSession session = request.getSession(false);
    	if (session != null) {
			session.invalidate();
		}
    	
    	return "redirect:/login";
    }
}
