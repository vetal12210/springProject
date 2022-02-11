package com.controller;


import com.model.entity.User;
import com.model.dto.UserFormDto;
import com.service.CaptchaService;
import com.service.RoleService;
import com.service.UserService;
import com.util.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
    private final RoleService roleService;
    private final CaptchaService captchaService;
    private final UserValidator userValidator;


    public RegistrationController(UserService userService, RoleService roleService, CaptchaService captchaService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.captchaService = captchaService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String registrationUserPage(Model model) {
        UserFormDto userFormDto = new UserFormDto();
        model.addAttribute("userFormDto", userFormDto);
        return "registr-user-page";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute("userFormDto") @Valid UserFormDto userFormDto,
                                 BindingResult bindingResult, HttpServletRequest request) {
        userValidator.validate(userFormDto, bindingResult);
        String response = request.getParameter("g-recaptcha-response");
        captchaProcess(response, bindingResult);

        ModelAndView mav = new ModelAndView("registr-user-page");
        boolean isValid = !bindingResult.hasErrors();
        userFormDto.setRole("2");
        User addUser = UserFormDto.userFormDtoToUser(userFormDto);
        addUser.setRole(roleService.findById(addUser.getRole().getId()));

        if (isValid) {
            userService.create(addUser);
            return new ModelAndView("login").addObject("reg", true);
        } else {
            return mav;
        }
    }

    private void captchaProcess(String response, BindingResult bindingResult) {
        try {
            captchaService.processResponse(response);
        } catch (RuntimeException ex) {
            ObjectError objectError = new ObjectError("recaptcha", "ReCaptcha is invalid");
            bindingResult.addError(objectError);
        }
    }

}
