package com.vwedesam.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class LoginController  {

    @RequestMapping(value = "/login", method = { RequestMethod.GET })
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                    @RequestParam(value = "logout", required = false) String logout,
                                    @RequestParam (value = "register", required = false) String register,
                                    Model model){
        String errorMessage = null;

        if(error != null){
            errorMessage = "Username or Password is incorrect !!";
        }else if(logout != null){
            errorMessage = "You have been successfully logged out !!";
        }else if(register != null) {
            errorMessage = "You registration successful. Login with registered credentials !!";
        }

        log.info("error message " + errorMessage);

        model.addAttribute("errorMessage", errorMessage);
        return "login.html";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Authentication auth = new SecurityContextHolder().getContext().getAuthentication();

        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }


}
