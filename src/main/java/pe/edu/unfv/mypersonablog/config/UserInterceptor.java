package pe.edu.unfv.mypersonablog.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.unfv.mypersonablog.entity.UserEntity;
import pe.edu.unfv.mypersonablog.service.UserService;

@Component
public class UserInterceptor implements HandlerInterceptor{

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("user_session_id") != null) {
			Long userId = Long.parseLong(session.getAttribute("user_session_id").toString());
			Optional<UserEntity> optionalUser = userService.getUserById(userId);
			if (optionalUser.isPresent()) {
				request.setAttribute("user", optionalUser.get());
			}else {
				return false;
			}
		}				
		return true;
	}
}
