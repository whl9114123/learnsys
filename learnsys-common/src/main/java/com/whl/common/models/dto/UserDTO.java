package com.whl.common.models.dto;

import com.whl.common.models.SysMenuEntity;
import com.whl.common.models.SysRoleEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class UserDTO {

    Set<SysRoleEntity> roles;
    Set<SysMenuEntity> permissions;
}
