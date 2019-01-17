package com.mmall.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.mmall.dao.SysDeptMapper;
import com.mmall.dto.DeptLevelDto;
import com.mmall.model.SysDept;
import com.mmall.service.SysTreeService;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName : SysTreeService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-13 23:04
 * @Description :各种树形结构拼接实现类
 */
@Service
public class SysTreeServiceImpl implements SysTreeService{

    @Resource
    private SysDeptMapper sysDeptMapper;

   /* @Resource
    private SysAclModuleMapper sysAclModuleMapper;
    @Resource
    private SysCoreService sysCoreService;
    @Resource
    private SysAclMapper sysAclMapper;*/

    /**
     * 部门层级树
     * @return
     */
    public List<DeptLevelDto> deptTree(){
        //获取部门列表
        List<SysDept> deptList = sysDeptMapper.getAllDept();

        //适配器模式
        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept dept : deptList){
            //适配出一个dto出来
            DeptLevelDto dto = DeptLevelDto.adapt(dept);
            dtoList.add(dto);
        }

        return  deptListToTree(dtoList);
    }

    /**
     * 根据部门dto获取root节点并遍历树形结构
     * @param deptLevelList
     * @return
     */
    public  List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList) {
        if (CollectionUtils.isEmpty(deptLevelList)){
            return Lists.newArrayList();
        }
        // level -> [dept1, dept2, ...] Map<String, List<Object>>
        //把所有的都放进去
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        //所有的root节点
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for (DeptLevelDto dto : deptLevelList ) {
            //把所有的都放进去,key为具体的层级关系，可以直接通过key找到某一个层级的所有子节点
            //下一次可以通过level获取所有的下一级部门
            levelDeptMap.put(dto.getLevel(),dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())){
                rootList.add(dto);
            }
        }

        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        //递归生成树，首先获取root层级的所有子节点
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }


    /**
     *  level:0, 0, all 0->0.1,0.2
     * level:0.1
     * level:0.2
     * @param deptLevelList 当前的结构
     * @param level 当前的level
     * @param levelDeptMap map所有的
     */
    private void transformDeptTree(List<DeptLevelDto> deptLevelList, String level, Multimap<String, DeptLevelDto> levelDeptMap) {
        //遍历当前层级数据，获取当前节点的下一层节点
        for (int i = 0; i < deptLevelList.size(); i++){
            // 遍历该层的每个元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<DeptLevelDto> tempDeptList = (List <DeptLevelDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isEmpty(tempDeptList)){
                //排序
                Collections.sort(tempDeptList, deptSeqComparator);
                //设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                //进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    /**
     * 从小到大排序
     */
    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator <DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
