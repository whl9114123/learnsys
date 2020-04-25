package com.whl.common.models.dto;

import com.whl.common.models.SysMenuEntity;
import com.whl.common.models.SysRoleEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -3207880482640325843L;

    Set<SysRoleEntity> roles = new HashSet<>();
    Set<SysMenuEntity> permissions = new HashSet<>();
}
