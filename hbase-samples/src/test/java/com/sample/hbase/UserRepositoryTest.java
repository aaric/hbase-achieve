package com.sample.hbase;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.MessageFormat;
import java.util.List;

/**
 * UserRepository测试类
 *
 * @author Aaric, created on 2017-05-25T10:07.
 * @since 1.0-SNAPSHOT
 */
@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    protected UserUtils userUtils;

    @Autowired
    protected UserRepository userRepository;

    @Test
    @Disabled
    public void testCreateTable() throws Exception {
        userUtils.initialize();
    }

    @Test
    @Disabled
    public void testFindAll() throws Exception {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    @Disabled
    public void testGet() throws Exception {
        System.out.println(userRepository.get(1));
    }

    @Test
    @Disabled
    public void testSave() throws Exception {
        String email;
        String password;
        for (int i = 1; i <= 100000; i++) {
            email = "6688" + MessageFormat.format("{0,number,000000}@qq.com", new Object[]{i});
            password = MessageFormat.format("{0,number,000000}", new Object[]{i});
            System.out.println(userRepository.save(i, "user" + i, email, DigestUtils.md5Hex(password)));
        }
    }

    @Test
    @Disabled
    public void testDelete() throws Exception {
        userRepository.delete(10);
    }

    @Test
    @Disabled
    public void testCount() throws Exception {
        // 100000(9708ms)
        System.out.println(userRepository.count());
    }

    @Test
    @Disabled
    public void testCount2() throws Exception {
        // 100000(646ms)
        System.out.println(userRepository.count2());
    }
}
