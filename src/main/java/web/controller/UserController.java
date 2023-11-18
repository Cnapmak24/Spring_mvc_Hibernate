package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "all-users-t";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }


    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user-info";
    }

    @RequestMapping("deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}