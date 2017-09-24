package com.bookstore.controller;
import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.impl.UserService;
import com.bookstore.service.impl.UserSecurityService;
import com.bookstore.utility.MailConstructor;
import com.bookstore.utility.SecurityUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Controller
public class homecontroller {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
  private   UserService userService;

    @Autowired
  private UserSecurityService userSecurityService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

   @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("classActiveLogin",true);
        return "Myaccount";
    }


    @RequestMapping("/ForgetPassword")
    public String ForgetPassword(
            HttpServletRequest request,
            @ModelAttribute("email") String email,
            Model model){
        model.addAttribute("classActiveForgetPassword",true);
        User user = userService.findByEmail(email);
        if( user == null) {
            model.addAttribute("emailNotExits", true);
            return "Myaccount";
        }
        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        userService.save(user);

//mostly sending passowrd to email logic
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user,token);

        String appUrl = "http://" +request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        SimpleMailMessage newEmail = mailConstructor.constructorResetTokenEmail(appUrl, request.getLocale(), token,user,password);

        mailSender.send(newEmail);
        model.addAttribute("forgetPasswordEmailSent",true);

        return "Myaccount";
    }

          //this is for the creation of user /the saving of the new user
    @RequestMapping(value ="/newUser",method =RequestMethod.POST)
    public String newUSerPost(
            HttpServletRequest request,
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("username") String username,
            Model model)
        throws Exception{
        model.addAttribute("classActiveNewAccount" , true);
        model.addAttribute("email",userEmail);
        model.addAttribute("username",username);

        //now if the user exist will  not add aother one
        //findByUsername was highlited red because it wasnt defined by userService
        if(userService.findByUsername(username) != null){
            model.addAttribute("usernameExits",true);

            //logically we return to myaccount not profile cause we cant have same username
            return "Myaccount";
        }
        //only here we are required to use email as defined in user class
        if(userService.findByEmail(userEmail) != null){
            model.addAttribute("emailExits",true);
            //logically we return to myaccount not profile cause we cant have same email
            return "Myaccount";
        }

        //now that we have checked there is no duplicate we can now create new user
        //now we use setters method

        //USER LOGIC
        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleid(1);
        role.setName("Role_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user ,role));
        userService.createUser(user ,userRoles);
//mostly sending passowrd to email logic
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user,token);

        String appUrl = "http://" +request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        SimpleMailMessage email = mailConstructor.constructorResetTokenEmail(appUrl, request.getLocale(), token,user,password);

        mailSender.send(email);
        model.addAttribute("emailsent",true);

        return "Myaccount";
    }

// this part is responsible for the link inside the mail sent to email (not sure )
   @RequestMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);

        if (passToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        model.addAttribute("classActiveEdit", true);

        return "Myprofile";
    }
}
