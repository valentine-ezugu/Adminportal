package com.bookstore.utility;

import com.bookstore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by VAlentine Ezugu on 8/5/2017.
 */
@Component
public class MailConstructor {

    @Autowired
    private Environment env;

    public SimpleMailMessage constructorResetTokenEmail(
            String contextPath, Locale locale, String token, User user, String password
    ){
        String url = contextPath +"/newUser?token=" +token;
        String message = "\nplease click on this link to verify your " +
                "email and edit your personal information. Your password is: \n"+password;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Val's Bookstore - New User");
        email.setText(url+message);
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
