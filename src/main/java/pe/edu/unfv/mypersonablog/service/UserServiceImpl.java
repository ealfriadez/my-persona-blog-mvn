package pe.edu.unfv.mypersonablog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unfv.mypersonablog.entity.UserEntity;
import pe.edu.unfv.mypersonablog.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> getUSerByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}