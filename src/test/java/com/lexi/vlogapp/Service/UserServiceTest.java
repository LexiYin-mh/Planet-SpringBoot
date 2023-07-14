package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.entity.Post;
import com.lexi.vlogapp.entity.User;
import com.lexi.vlogapp.dao.repository.PostRepository;
import com.lexi.vlogapp.dao.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private User testUser;

    private Post testPost1;

    private Post testPost2;

    @BeforeEach
    public void setup(){
        // make up fake data for testing.
        // First, save One Side
        testUser = new User();
        testUser.setName("Test User");
        testUser.setEmail("Test Email");
        testUser.setPassword("Test Pwd");
        //userRepository.save(testUser);

        // Second, save Many Side
        testPost1 = new Post();
        testPost1.setPostContent("test1");
        testPost1.setPostMedia("test/media/1");
        testPost1.setPostDate(Timestamp.valueOf(LocalDateTime.now()));
        testPost1.setUser(testUser);

        testPost2 = new Post();
        testPost2.setPostContent("test2");
        testPost2.setPostMedia("test/media/2");
        testPost2.setPostDate(Timestamp.valueOf(LocalDateTime.now()));
        testPost2.setUser(testUser);


        userRepository.save(testUser);
//        postRepository.save(testPost1);
//        postRepository.save(testPost2);
    }

    @AfterEach
    public void cleanup() {
        // cascade = CascadeType.PERSIST
        // First, delete Many Side
//        postRepository.delete(testPost1);
//        postRepository.delete(testPost2);

        // Next, delete One Side
        userRepository.delete(testUser);
    }

    @Test
    void tryout(){
        Optional<User> retrievedUser = userRepository.findById(9L);
        System.out.println(retrievedUser);
    }

//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void deleteUserWithCommit() {
//        Boolean flag = false;
//        postRepository.deleteById(13L);
//        postRepository.deleteById(14L);
//        flag = userService.deleteById(9L);
//        assertTrue(flag);
//    }

//    @Test
//    void findAll() {
//        Set<User> users = userService.findAll();
//        assertNotNull(users);
//        assertTrue(users.size() >= 8);
//    }

//    @Test
//    void saveUser() {
//        //User savedUser = userService.save(testUser);
//        Set<Post> testPosts = testUser.getPosts();
//        System.out.println(testPosts.size());
//        for (Post post : testPosts) {
//            System.out.println(post.getUser().getName());
//            System.out.println(post.getPostContent());
//        }
//        User savedUser = userService.save(testUser);
//        assertNotNull(savedUser.getId());
//    }


    /*
    * @Transactional for automatic rollback for all the operation, you can remove cleanup() method if using this annotation.
    * @Rollback(value = false) ====  It could be used while we want to test db commitment.
    */

}