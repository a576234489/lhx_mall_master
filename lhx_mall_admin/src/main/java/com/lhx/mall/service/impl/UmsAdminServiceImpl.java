package com.lhx.mall.service.impl;

import com.lhx.mall.bo.AdminUserDetails;
import com.lhx.mall.dao.UmsAdminRoleRelationDao;
import com.lhx.mall.mapper.UmsAdminLoginLogMapper;
import com.lhx.mall.mapper.UmsAdminMapper;
import com.lhx.mall.model.UmsAdmin;
import com.lhx.mall.model.UmsAdminExample;
import com.lhx.mall.model.UmsAdminLoginLog;
import com.lhx.mall.model.UmsPermission;
import com.lhx.mall.service.UmsAdminService;
import com.lhx.mall.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * UmsAdminService实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return token;
    }
    /**
     * 添加登录记录
     * @param username 用户名
     */
    public void insertLoginLog(String username){
        UmsAdmin umsAdmin = getAdminByUsername(username);
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(umsAdmin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        umsAdminLoginLogMapper.insert(loginLog);
    }

}
