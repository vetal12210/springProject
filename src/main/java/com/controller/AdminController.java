package com.controller;

import com.model.entity.User;
import com.model.dto.UserFormDto;
import com.service.RoleService;
import com.service.UserService;
import com.util.UserValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }

    @GetMapping("")
    public ModelAndView adminPage() {
        ModelAndView mav = new ModelAndView("admin-page");
        List<User> users = userService.findAll();
        mav.addObject("users", users);
        return mav;
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String addUserPage(Model model) {
        UserFormDto userFormDto = new UserFormDto();
        model.addAttribute("userFormDto", userFormDto);
        return "add-user-page";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("userFormDto") @Valid UserFormDto userFormDto, BindingResult bindingResult) {
        userValidator.validate(userFormDto, bindingResult);
        ModelAndView mav = new ModelAndView("add-user-page");
        boolean isValid = !bindingResult.hasErrors();
        User addUser = UserFormDto.userFormDtoToUser(userFormDto);
        addUser.setRole(roleService.findById(addUser.getRole().getId()));

        if (isValid) {
            userService.create(addUser);
            List<User> users = userService.findAll();
            return new ModelAndView("admin-page").addObject("users", users);
        } else {
            return mav;
        }
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.GET)
    public String editUserPage(@RequestParam String editLogin, Model model) {
        UserFormDto userFormDto = UserFormDto.userToUserFormDto(userService.findByLogin(editLogin));
        userFormDto.setPassword("");
        userFormDto.setPasswordRepeat("");
        model.addAttribute("userFormDto", userFormDto);
        return "edit-user-page";
    }

    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("userFormDto") @Valid UserFormDto userFormDto, BindingResult bindingResult) {
        userValidator.validate(userFormDto, bindingResult);
        ModelAndView mav = new ModelAndView("edit-user-page");

        boolean isValid = isValidExcludePassAndLogin(bindingResult);
        User editUser = userService.findByLogin(userFormDto.getLogin());
        if (!userFormDto.getPassword().isEmpty() && !userFormDto.getPasswordRepeat().isEmpty()) {
            editUser.setPassword(userFormDto.getPassword());
        }
        editUser.setLogin(userFormDto.getLogin());
        editUser.setEmail(userFormDto.getEmail());
        editUser.setFirstName(userFormDto.getFirstName());
        editUser.setLastName(userFormDto.getLastName());
        editUser.setBirthday(Date.valueOf(userFormDto.getBirthday()));
        editUser.setRole(roleService.findById(Long.valueOf(userFormDto.getRole())));

        if (isValid) {
            userService.update(editUser);
            List<User> users = userService.findAll();
            return new ModelAndView("admin-page").addObject("users", users);
        } else {
            return mav;
        }
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public String deleteUser(@RequestParam String deleteLogin) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (!deleteLogin.equals(currentPrincipalName)) {
            User user = userService.findByLogin(deleteLogin);
            userService.remove(user);
        }
        return "redirect:/admin";
    }

    private boolean isValidExcludePassAndLogin(BindingResult result) {
        return result.getFieldErrorCount("login") == 1 && result.getFieldErrorCount("password") == 2 && result.getFieldErrorCount("passwordRepeat") == 2 && result.getErrorCount() == 5
                || result.getFieldErrorCount("login") == 1 && result.getErrorCount() == 1;
    }
}
