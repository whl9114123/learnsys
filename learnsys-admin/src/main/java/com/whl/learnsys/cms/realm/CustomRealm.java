package com.whl.learnsys.cms.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whl.common.mappers.SysMenuDao;
import com.whl.common.mappers.SysUserDao;
import com.whl.common.models.SysMenuEntity;
import com.whl.common.models.SysUserEntity;
import com.whl.common.models.dto.UserDTO;
import com.whl.common.service.SysMenuService;
import com.whl.common.service.SysRoleService;
import com.whl.common.service.SysUserService;
import com.whl.common.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;
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
        SysUserEntity user = (SysUserEntity) principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
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
            //登录成功
//            Map<String,Object> map =new HashMap<>();
//            user.setPassword(null);
//            Set<SysRoleEntity> roles = sysRoleService.getRolesById(user.getUserId());
//            List<Long> roleIds = roles.stream().map(SysRoleEntity::getRoleId).collect(Collectors.toList());
//            Set<SysMenuEntity> permissions = sysMenuService.getPermissionByRoles(roleIds);
            UserDTO userDTO = new UserDTO();
//            userDTO.setRoles(roles).setPermissions(permissions);
//            安全数据，密码，realm域名
            return new SimpleAuthenticationInfo(user, upToken.getPassword(), this.getName());
        } else {
            return null;
        }

    }
}
