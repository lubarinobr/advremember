package br.com.lubarino.jarvis.controller;

import br.com.lubarino.jarvis.domains.User;
import br.com.lubarino.jarvis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@SessionAttributes("user")
public class ViewController {

    private UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ModelAndView adminIndex(HttpSession session) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        session.setAttribute("user", user);
        ModelAndView modelAndView = new ModelAndView("admin/index");
        return modelAndView;
    }
}
