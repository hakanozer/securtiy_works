package com.works.services;

import com.works.entities.LoginUser;
import com.works.repositories.LoginUserRepository;
import com.works.util.Util;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Service
public class UserService {

    final DriverManagerDataSource source;
    final HttpServletRequest req;
    final HttpServletResponse res;
    final LoginUserRepository lRepo;
    public UserService(DriverManagerDataSource source, HttpServletRequest req, HttpServletResponse res, LoginUserRepository lRepo) {
        this.source = source;
        this.req = req;
        this.res = res;
        this.lRepo = lRepo;
    }


    // user Login
    public boolean userLogin(LoginUser user, String remember) {
        boolean status = false;
        try {

            //String sql = "select * from LOGIN_USER where email = '"+user.getEmail()+"' and password = '"+user.getPassword()+"' ";
            // select * from  LOGIN_USER where email = 'a@a.com' and password = '' or 1 = 1 -- '
            //Statement st = source.getConnection().createStatement();
            //ResultSet rs = st.executeQuery(sql);

            String sqlp = "select * from LOGIN_USER where email = ? and password = ? ";
            PreparedStatement pre = source.getConnection().prepareStatement(sqlp);
            pre.setString(1, user.getEmail() );
            pre.setString(2, Util.md5(user.getPassword()) );
            ResultSet rs = pre.executeQuery();
            status = rs.next();
            if ( status ) {
                LoginUser loginUser = new LoginUser();
                int uid = rs.getInt("uid");
                String name = rs.getString("user_status");
                loginUser.setUid( uid );
                loginUser.setEmail(user.getEmail() );
                loginUser.setUserStatus(  name );
                req.getSession().setAttribute("user", loginUser);
                // remember control
                if ( remember.equals("on") ) {
                    Cookie cookie = new Cookie("user",  Util.encoder( ""+uid, 5 ) );
                    cookie.setMaxAge(60 * 60);
                    res.addCookie(cookie);
                }
            }

        }catch (Exception ex) {
            System.err.println("userLogin Error : " + ex);
        }
        return status;
    }


    // user logout
    public void logout() {
        req.getSession().removeAttribute("user");
        removeCookie();
    }

    // cookie control
    public void cookieControl() {
        if ( req.getCookies() != null ) {
            Cookie[] cookies = req.getCookies();
            for ( Cookie item : cookies ) {
                if ( item.getName().equals("user") ) {
                    String stUid = item.getValue();
                    try {
                        stUid = Util.decoder( stUid, 5 );
                        int uid = Integer.parseInt( stUid );
                        Optional<LoginUser> oUser = lRepo.findById(uid);
                        if ( oUser.isPresent() ) {
                            req.getSession().setAttribute("user", oUser.get());
                        }else {
                            removeCookie();
                        }
                    }catch (Exception ex) {
                        removeCookie();
                    }
                }
            }
        }
    }

    public void removeCookie() {
        Cookie cookie = new Cookie("user", "" );
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }


}
