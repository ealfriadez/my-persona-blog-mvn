package pe.edu.unfv.mypersonablog.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import pe.edu.unfv.mypersonablog.entity.CommentEntity;
import pe.edu.unfv.mypersonablog.entity.PostEntity;
import pe.edu.unfv.mypersonablog.entity.UserEntity;
import pe.edu.unfv.mypersonablog.service.CommentService;
import pe.edu.unfv.mypersonablog.service.PostService;
import pe.edu.unfv.mypersonablog.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addComment")
	public String addComment(@RequestParam("postId") Long postId, CommentEntity comment, HttpSession session) {
		UserEntity user = userService.getUserById(Long.parseLong(session.getAttribute("user_session_id").toString())).get();
		PostEntity post = postService.getPostById(postId).orElseThrow(() -> new IllegalArgumentException("¡Invalid post id!")); 
		
		comment.setCreated_at(LocalDateTime.now());
		comment.setUser(user);
		comment.setPost(post);
		
		commentService.createComment(comment);
				
		return "redirect:/post/postPage/" + postId;
	}
}
