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

    // Получение списка юзеров
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "index";
    }

    // Получение юзера по id
    @GetMapping("/show")
    public String show(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    // Добавление юзера Get
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    // Добавление юзера Post
    @PostMapping("/add")
    public String addUserPage(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    // Удаление юзера
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    // Изменение юзера Get
    @GetMapping(value = "/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    // Изменение юзера Post
    @PostMapping(value = "/edit")
    public String editUserPage(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }
}