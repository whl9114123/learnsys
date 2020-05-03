/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.whl.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whl.common.mappers.SysDeptDao;
import com.whl.common.models.SysDeptEntity;
import com.whl.common.service.SysDeptService;
import com.whl.common.util.PageUtils;
import com.whl.common.util.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Override

    public List<SysDeptEntity> queryList(Map<String, Object> params) {
        return baseMapper.queryList(params);
    }

    @Override
    public List<Long> queryDetpIdList(Long parentId) {
        return baseMapper.queryDetpIdList(parentId);
    }

    @Override
    public List<Long> getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDetpIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        return deptIdList;
    }

    @Override
    public PageUtils queryPage(HashMap<String, Object> stringObjectHashMap) {
        IPage<SysDeptEntity> page = this.page(
                new Query<SysDeptEntity>().getPage(stringObjectHashMap)
        );


        return new PageUtils(page);
    }

    /**
     * 递归
     */
    private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList) {
        for (Long deptId : subIdList) {
            List<Long> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }
}
