package pe.edu.unfv.mypersonablog.service;

import java.util.Optional;

import pe.edu.unfv.mypersonablog.entity.CommentEntity;


public interface CommentService {

    Optional<CommentEntity> getCommentById(Long id);
    void createComment(CommentEntity comment);
    void updateComment(Long id, CommentEntity entity);
    void deleteComment(Long id);
}
