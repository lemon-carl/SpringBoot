package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.beans.LogType;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysLogMapper;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.dao.SysUserMapper;
import com.mmall.model.SysLogWithBLOBs;
import com.mmall.model.SysRoleUser;
import com.mmall.model.SysUser;
import com.mmall.service.SysLogService;
import com.mmall.service.SysRoleUserService;
import com.mmall.util.IpUtil;
import com.mmall.util.JsonMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @ClassName : SysRoleUserServiceImpl
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-31 23:59
 * @Description :
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

  @Resource private SysRoleUserMapper sysRoleUserMapper;

  @Resource private SysUserMapper sysUserMapper;

 // @Resource private SysLogService sysLogService;
  @Resource private SysLogMapper sysLogMapper;

  @Override
  public List<SysUser> getListByRoleId(int roleId) {
    List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
    if (CollectionUtils.isEmpty(userIdList)) {

      return Lists.newArrayList();
    }

    return sysUserMapper.getByIdList(userIdList);
  }


  // 更新需要先删除再更新
  @Override
  public void changeRoleUsers(int roleId, List<Integer> userIdList) {
    List<Integer> originUserIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
    if (originUserIdList.size() == userIdList.size()) {
      Set<Integer> originUserIdSet = Sets.newHashSet(originUserIdList);
      Set<Integer> userIdSet = Sets.newHashSet(userIdList);
      originUserIdSet.removeAll(userIdSet);
      if (CollectionUtils.isEmpty(originUserIdSet)) {
        return;
      }
    }
    updateRoleUsers(roleId, userIdList);
    //sysLogService.saveRoleUserLog(roleId, originUserIdList, userIdList);
    saveRoleUserLog(roleId, originUserIdList, userIdList);
  }

  @Transactional
  protected void updateRoleUsers(int roleId, List<Integer> userIdList) {
    sysRoleUserMapper.deleteByRoleId(roleId);

    if (CollectionUtils.isEmpty(userIdList)) {
      return;
    }
    List<SysRoleUser> roleUserList = Lists.newArrayList();
    for (Integer userId : userIdList) {
      SysRoleUser roleUser =
          SysRoleUser.builder()
              .roleId(roleId)
              .userId(userId)
              .operator(RequestHolder.getCurrentUser().getUsername())
              .operateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()))
              .operateTime(new Date())
              .build();
      roleUserList.add(roleUser);
    }
    sysRoleUserMapper.batchInsert(roleUserList);
  }


  private void saveRoleUserLog(int roleId, List<Integer> before, List<Integer> after) {
     SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
    sysLog.setType(LogType.TYPE_ROLE_USER);
    sysLog.setTargetId(roleId);
    sysLog.setOldValue(before == null ? "" : JsonMapper.obj2String(before));
    sysLog.setNewValue(after == null ? "" : JsonMapper.obj2String(after));
    sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
    sysLog.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
    sysLog.setOperateTime(new Date());
    sysLog.setStatus(1);
    sysLogMapper.insertSelective(sysLog);
  }

}
