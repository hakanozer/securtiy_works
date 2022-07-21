package com.works.services;

import com.works.entities.Role;
import com.works.entities.UserX;
import com.works.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends SimpleUrlLogoutSuccessHandler implements UserDetailsService, LogoutSuccessHandler {

    final UserRepository uRepo;
    public UserService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }


    // security login
    @Override
    public UserDetails loadUserByUsername( String email ) {
        UserDetails userDetails = null;
        Optional<UserX> oUser = uRepo.findByEmailEqualsAllIgnoreCase(email);
        if ( oUser.isPresent() ) {
            UserX us = oUser.get();
            userDetails = new org.springframework.security.core.userdetails.User(
                    us.getEmail(),
                    us.getPassword(),
                    us.isEnabled(),
                    us.isTokenExpired(),
                    true,
                    true,
                    getAuthorities( us.getRoles() ));
        }else {
            throw new UsernameNotFoundException("Kullanıcı adı yada şifre hatalı");
        }
        return userDetails;
    }


    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        }
        return authorities;
    }


    public UserX register( UserX us ) throws AuthenticationException {

        Optional<UserX> uOpt = uRepo.findByEmailEqualsAllIgnoreCase(us.getEmail());
        if ( uOpt.isPresent() ) {
            throw new AuthenticationException("Bu kullanıcı daha önce kayıtlı!");
        }
        us.setPassword( encoder().encode( us.getPassword() ) );

        return uRepo.save(us);
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }





}

