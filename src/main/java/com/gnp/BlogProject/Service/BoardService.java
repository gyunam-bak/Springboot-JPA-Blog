package com.gnp.BlogProject.Service;

import com.gnp.BlogProject.model.RoleType;
import com.gnp.BlogProject.model.User;
import com.gnp.BlogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
// 서비스
// 1. 트랜잭션 관리
// 2. 서비스 의미 때문에 -> SQL의 SP사용의 의미구나..
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user) {

        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬
        user.setPassword(encPassword);
        user.setRole((RoleType.USER));
        userRepository.save(user);
    }
}
