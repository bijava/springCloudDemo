//package com.devin.config.service;
//
//import com.netflix.config.ConfigurationManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class MyUserDetailsService implements UserDetailsService {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        logger.info("用户的用户名: {}", username);
//        // 从配置文件中获取登陆密码
//        String securityPwd = ConfigurationManager.getConfigInstance().getString("devin.metadata-map.security-pwd");
//
//        String password = passwordEncoder.encode(securityPwd);
//        logger.info("password: {}", password);
//
//        // TODO 根据用户名，查找到对应的密码，与权限
//
//        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
//        User user = new User(username, password,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return user;
//    }
//}