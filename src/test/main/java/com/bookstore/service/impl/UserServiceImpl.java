package com.bookstore.service.impl;


import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger Log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }
    @Override
   public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email){
       return userRepository.findByEmail(email);
    }

    //handling the logic of creating a user and role saving
    @Override
     public User createUser(User user, Set<UserRole> userRoles){
     User localUser = userRepository.findByUsername(user.getUsername());

     if(localUser != null){
         Log.info("user {} already exists. Nothing will be done",user.getUsername());
     }else {
         for (UserRole ur : userRoles){
             roleRepository.save(ur.getRole());
         }
         user.getUserRoles().addAll(userRoles);

         localUser = userRepository.save(user);
     }
    return localUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
