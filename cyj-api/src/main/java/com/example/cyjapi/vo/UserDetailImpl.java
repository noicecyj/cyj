package com.example.cyjapi.vo;import org.springframework.security.core.GrantedAuthority;import org.springframework.security.core.userdetails.UserDetails;import java.util.Collection;/** * @author yaohw * @date 2019-09-25 16:12 */public class UserDetailImpl implements UserDetails {    private String id;    private String userName;    private String password;    private boolean isEnable;    private Collection<? extends GrantedAuthority> authorities;    @Override    public Collection<? extends GrantedAuthority> getAuthorities() {        return this.authorities;    }    @Override    public String getPassword() {        return this.password;    }    @Override    public String getUsername() {        return this.userName;    }    @Override    public boolean isAccountNonExpired() {        return true;    }    @Override    public boolean isAccountNonLocked() {        return true;    }    @Override    public boolean isCredentialsNonExpired() {        return true;    }    @Override    public boolean isEnabled() {        return this.isEnable;    }    public void setEnable(boolean enable) {        isEnable = enable;    }    public void setUserName(String userName) {        this.userName = userName;    }    public void setPassword(String password) {        this.password = password;    }    public String getId() {        return id;    }    public void setId(String id) {        this.id = id;    }    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {        this.authorities = authorities;    }}