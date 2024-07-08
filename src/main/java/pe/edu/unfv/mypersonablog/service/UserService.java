package pe.edu.unfv.mypersonablog.service;

import pe.edu.unfv.mypersonablog.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    void createUser(UserEntity user);
    Optional<UserEntity> getUserById(Long id);
    Optional<UserEntity> getUSerByUserName(String username);
}
