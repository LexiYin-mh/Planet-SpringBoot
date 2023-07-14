//package com.lexi.vlogapp.Service;
//
//import com.lexi.vlogapp.dao.hibernateDao.UserDao;
//import com.lexi.vlogapp.entity.User;
//import com.lexi.vlogapp.dao.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class UserService implements UserDao {
//
//    public Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public Optional<User> findById(Long id) {
//        Optional<User> retrievedUser = userRepository.findById(id);
//        return Optional.empty();
//    }
//
//    public Set<User> findAll() {
//        Set<User> users = new HashSet<>(userRepository.findAll());
//        return users;
//    }
//
//    @Override
//    public Set<User> findUsersByName(String name) {
//        return null;
//    }
//
//    @Transactional
//    @Override
//    public User save(User user) {
//        User savedUser = userRepository.save(user);
//        return savedUser;
//    }
//
//    @Override
//    public User update(User model) {
//        return null;
//    }
//
//    @Override
//    public boolean delete(User user) {
//        boolean successFlag = false;
//        try {
//            user.getPosts().clear();
//            userRepository.delete(user);
//            successFlag = true;
//        } catch (RuntimeException re) {
//            logger.error("caught Exception when trying delete major with majorId = {}, error = {}", user.getId(), re.getMessage());
//        }
//        return successFlag;
//    }
//
//    @Override
//    public boolean deleteById(Long id) {
//        boolean successFlag = false;
//        try {
//            userRepository.deleteById(id);
//            successFlag = true;
//        } catch (RuntimeException re) {
//            logger.error("caught Exception when trying delete major with majorId = {}, error = {}", id, re.getMessage());
//        }
//        return successFlag;
//    }
//
//
//}
