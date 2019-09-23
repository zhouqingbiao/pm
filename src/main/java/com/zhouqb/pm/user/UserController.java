package com.zhouqb.pm.user;

import com.zhouqb.pm.virtualMachine.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VirtualMachineRepository virtualMachineRepository;

    @GetMapping("/User")
    public String user() {
        return "User";
    }

    @PostMapping("/User")
    public String login(Model model, User user, HttpSession httpSession) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            User u = userRepository.findByUsernameAndPassword(username, password).get();
            if (!StringUtils.isEmpty(u.getUsername()) && !StringUtils.isEmpty(u.getPassword())) {
                httpSession.setAttribute("user", user);
                return "Slash";
            }
        }
        return "User";
    }
}
