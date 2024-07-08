package pe.edu.unfv.mypersonablog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unfv.mypersonablog.entity.PostEntity;
import pe.edu.unfv.mypersonablog.repository.PostRepository;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostEntity> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Optional<PostEntity> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<PostEntity> getPostByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public void createPost(PostEntity post) {
        postRepository.save(post);
    }

    @Override
    public void updatePost(Long id, PostEntity post) {
        PostEntity postDB = getPostById(id).orElseThrow(() -> new InvalidParameterException("Invalid post id"));
        postDB.setTitle(post.getTitle());
        postDB.setContent(post.getContent());
        postRepository.save(postDB);
    }

    @Override
    public void deletePostBy(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostEntity> seearchPostByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }
}
