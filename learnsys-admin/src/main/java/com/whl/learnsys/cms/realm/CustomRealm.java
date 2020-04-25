package com.whl.learnsys.cms.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whl.common.models.SysUserEntity;
import com.whl.common.models.dto.UserDTO;
import com.whl.common.service.SysMenuService;
import com.whl.common.service.SysRoleService;
import com.whl.common.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDTO userDTO = (UserDTO) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        Set<String> menu = new HashSet<>();
        userDTO.getRoles().forEach(c -> {
            roles.add(c.getRoleName());
        });
        userDTO.getPermissions().forEach(c -> {
            menu.add(c.getPerms());
        });
        info.setRoles(roles);
        info.setStringPermissions(menu);
        return info;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", upToken.getUsername());
        SysUserEntity user = sysUserService.getOne(wrapper);
        String pwd = new String(upToken.getPassword());
        if (user != null && pwd.equals(user.getPassword())) {
//            //登录成功
//            Map<String,Object> map =new HashMap<>();
//            user.setPassword(null);
//            Set<SysRoleEntity> roles = sysRoleService.getRolesById(user.getUserId());
//            List<Long> roleIds = roles.stream().map(SysRoleEntity::getRoleId).collect(Collectors.toList());
//            Set<SysMenuEntity> permissions = sysMenuService.getPermissionByRoles(roleIds);
            UserDTO userDTO = new UserDTO();
//            userDTO.setRoles(roles).setPermissions(permissions);
            //安全数据，密码，realm域名
            return new SimpleAuthenticationInfo(userDTO, upToken.getPassword(), this.getName());
        } else {
            return null;
        }

    }
}
