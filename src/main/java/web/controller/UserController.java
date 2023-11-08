package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getIndex(Model model) {
        model.addAttribute("user", userService.getAllUser());
        return "index";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUserPage(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editUserPage(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}