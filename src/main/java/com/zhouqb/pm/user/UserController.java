package com.zhouqb.pm.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/User")
    public String user() {
        return "User";
    }

    /**
     * 验证登录并添加至Session
     * @param user
     * @param httpSession
     * @return
     */
    @PostMapping("/User")
    public String login(User user, HttpSession httpSession) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            Optional<User> optionalUser = userRepository.findByUsernameAndPassword(username, password);
            if (optionalUser.isPresent()) {
                httpSession.setAttribute("user", optionalUser.get());
                return "Slash";
            }
        }
        return "User";
    }
}
