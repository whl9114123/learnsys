/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.whl.learnsys.cms.controller;


import com.whl.common.enums.ResultCode;
import com.whl.common.models.ResultModel;
import com.whl.common.models.SysUserEntity;
import com.whl.common.models.dto.UserDTO;
import com.whl.common.service.SysUserRoleService;
import com.whl.common.service.SysUserService;
import com.whl.common.util.PageUtils;
import com.whl.common.util.R;
import com.whl.learnsys.cms.realm.ShiroUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/user")

public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysUserService.queryPage(params);

        return R.ok().put("page", page);
    }

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
    public R info() {
		return R.ok().put("user", getUser());
	}

	/**
	 * 修改登录用户密码
	 */

	@RequestMapping("/password")
    public R password(String password, String newPassword) {


        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
			return R.error("原密码不正确");
		}

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") Long userId) {
		SysUserEntity user = sysUserService.getById(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */

	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
    public R save(@RequestBody SysUserEntity user) {


        sysUserService.saveUser(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */

	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
    public R update(@RequestBody SysUserEntity user) {


        sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */

	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
			return R.error("系统管理员不能删除");
		}

        if (ArrayUtils.contains(userIds, getUserId())) {
			return R.error("当前用户不能删除");
		}

		sysUserService.removeByIds(Arrays.asList(userIds));

		return R.ok();
	}

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ResultModel<UserDTO> profile(HttpServletRequest request) throws Exception {
        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        //2.获取安全数据
        UserDTO result = (UserDTO) principals.getPrimaryPrincipal();

//        String userid = claims.getId();
//        //获取用户信息
//        User user = userService.findById(userid);
//        //根据不同的用户级别获取用户权限
//
//        ProfileResult result = null;
//
//        if("user".equals(user.getLevel())) {
//            result = new ProfileResult(user);
//        }else {
//            Map map = new HashMap();
//            if("coAdmin".equals(user.getLevel())) {
//                map.put("enVisible","1");
//            }
//            List<Permission> list = permissionService.findAll(map);
//            result = new ProfileResult(user,list);
//        }
        return ResultModel.valueOf(ResultCode.SUCCESS, result);
    }
}
