package br.com.lubarino.jarvis.controller;

import br.com.lubarino.jarvis.domains.User;
import br.com.lubarino.jarvis.services.SecurityService;
import br.com.lubarino.jarvis.services.UserService;
import br.com.lubarino.jarvis.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Usuário e/ou senha incorreto(s).");

        if (logout != null)
            model.addAttribute("message", "Você se desconectou com sucesso.");

        return "index";
    }

    @GetMapping("/login-error")
    public ModelAndView loginError(final HttpServletRequest request) {
        final String error = (String) request.getSession().getAttribute("error");
        ModelAndView view = new ModelAndView("index");
        view.addObject("error", error);
        return view;
    }

    @GetMapping({"/"})
    public String welcome(Model model) {
        return "index";
    }
}