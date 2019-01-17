package com.mmall.service.impl;

import com.google.common.base.Preconditions;
import com.mmall.dao.SysDeptMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import com.mmall.util.BeanValidator;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : SysDeptService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-13 17:54
 * @Description : 部门业务处理
 */
@Service
public class SysDeptServiceImpl implements SysDeptService{

    protected static final Logger log = LoggerFactory.getLogger(SysDeptServiceImpl.class);

    @Resource
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param){
        //基本的验证
        BeanValidator.check(param);

        //检测当前部门是否已经重复，同一级部门下不能出现名称重复的部门
        //传id用来update是检查
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        //建造者的方式使用
        SysDept dept = SysDept.builder().name(param.getName())
                                        .parentId(13)
                                        .seq(param.getSeq())
                                        .remark(param.getRemark())
                                        .build();
        //dept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        dept.setLevel(LevelUtil.calculateLevel(getLevel(13),13));

        dept.setOperator("system");//TODO:
        dept.setOperateIp("127.0.0.1");//TODO:
        dept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(dept);
    }

    public void update(DeptParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");
        if(checkExist(param.getParentId(),param.getName(),param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }

        SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        after.setOperator("system-update");//TODO:
        after.setOperateIp("127.0.0.1");//TODO:
        after.setOperateTime(new Date());

        updateWithChild(before,after);
    }


    /*@Override
    public void delete(int deptId) {
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        Preconditions.checkNotNull(dept, "待删除的部门不存在，无法删除");
        if (sysDeptMapper.countByParentId(dept.getId()) > 0) {
            throw new ParamException("当前部门下面有子部门，无法删除");
        }
        if(sysUserMapper.countByDeptId(dept.getId()) > 0) {
            throw new ParamException("当前部门下面有用户，无法删除");
        }
        sysDeptMapper.deleteByPrimaryKey(deptId);
    }*/


    /**
     * 更新所有子部门
     * @param before
     * @param after
     */
    @Transactional
     void updateWithChild(SysDept before,SysDept after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        //只有不一致的时候才做子部门的更新
        if(!after.getLevel().equals(before.getLevel())){
            //获取了当前部门level开头的所有部门，包括子部门的子部门
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)){
                for (SysDept dept : deptList){
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0){
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }

        //最后更新当前部门
        sysDeptMapper.updateByPrimaryKey(after);

    }

    //检查数据是否有重复
    private boolean checkExist(Integer parentId,String deptName, Integer deptId){
        //TODO:
        return sysDeptMapper.countByNameAndParentId(parentId,deptName,deptId) > 0;
    }

    /**
     * 查询父级的parentid
     * @param deptId
     * @return
     */
    private String getLevel(Integer deptId){
        SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (dept == null){
            return  null;
        }
        return  dept.getLevel();
    }
}
