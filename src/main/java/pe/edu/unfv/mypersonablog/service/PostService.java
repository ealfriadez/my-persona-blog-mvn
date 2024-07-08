package pe.edu.unfv.mypersonablog.service;

import pe.edu.unfv.mypersonablog.entity.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostEntity> getAllPost();
    Optional<PostEntity> getPostById(Long id);
    List<PostEntity> getPostByUserId(Long userId);
    void createPost(PostEntity post);
    void updatePost(Long id, PostEntity post);
    void deletePostBy(Long id);
    List<PostEntity> seearchPostByTitle(String title);
}
