//package com.kris.acg.entity.security;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import java.util.Collection;
//
///**
// * @Program: acg
// * @Description:
// * @Author: kris
// * @Create: 2023-09-18 18:36
// **/
//
//public class SecurityUser implements UserDetails {
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//
//    private final String id;
//    private final String username;
//    private final String password;
//    private final boolean enabled;
//    private final Collection<? extends GrantedAuthority> authorities;
//
//    public SecurityUser(
//            String id,
//            String username,
//            String password,
//            boolean enabled,
//            Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.enabled = enabled;
//        this.authorities = authorities;
//    }
//
//    /**
//     * 返回分配给用户的角色列表
//     *
//     * @return
//     */
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @JsonIgnore
//    public String getId() {
//        return id;
//    }
//
//    @JsonIgnore
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    /**
//     * 账户是否激活
//     *
//     * @return
//     */
//    @JsonIgnore
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    /**
//     * 账户是否未过期
//     *
//     * @return
//     */
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    /**
//     * 账户是否未锁定
//     *
//     * @return
//     */
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    /**
//     * 密码是否未过期
//     *
//     * @return
//     */
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//}
