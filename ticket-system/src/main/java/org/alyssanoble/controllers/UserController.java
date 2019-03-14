package org.alyssanoble.controllers;

import org.alyssanoble.models.SignUser;
import org.alyssanoble.models.User;
import org.alyssanoble.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "") /* It provides list of employees in model object */
    public String index(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "user/index";

    }

    /**
     * It displays a form to input data, here "users" is a reserved request attribute
     * which is used to display object data into form
     */

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displayUserForm(Model model) {
        model.addAttribute("title", "Sign up");
        model.addAttribute(new User());
        model.addAttribute("users", userDao.findAll());
        return "user/signup";
    }

    /**
     * It saves object into database. The @ModelAttribute puts request data
     * into model object. You need to mention RequestMethod.POST method
     * because default request is GET
     */

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processUserForm(@ModelAttribute @Valid User user, Errors errors,  Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Sign up");
            return "user/signup";
        }
        User existUser = userDao.findByEmail(user.getEmail());
        User existPuser =userDao.findByPhone(user.getPhone());
        if(existUser != null || existPuser !=null){
//          model.addAttribute("disMsg","User already exists");
//            return "user/signup";
            return "redirect:/user/signup?q=User+already+exists";
        }
        userDao.save(user);

        return "redirect:";
    }

    /**
     * It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.
     */

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String editDisplayForm(@PathVariable int userId, Model model) {
        model.addAttribute("user", userDao.findById(userId));
        model.addAttribute("users", userDao.findAll());
        return "user/edit";

    }

    /**
     * It updates model object.
     */

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "Sign in form");
        model.addAttribute("signUser",new SignUser());
        return "user/signin";
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid SignUser signUser,Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "user/signin";
        }
        User matchUser;
        matchUser= userDao.findByEmail(signUser.getEmail());
        if(matchUser == null) {
            matchUser = userDao.findByPhone(signUser.getEmail());
        }
        if (matchUser!=null && signUser.getPassword().equals(matchUser.getPassword())) {
            return "redirect:/ticket";
        }
        return "redirect:/user/signin?q=Invalid+Email+Or+Phone";
        //return "redirect";

    }

    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String processLogoutForm(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "user/signout";
    }
}