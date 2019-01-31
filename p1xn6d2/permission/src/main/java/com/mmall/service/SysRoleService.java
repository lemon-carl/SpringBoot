package com.mmall.service;

import com.mmall.model.SysRole;
import com.mmall.model.SysUser;
import com.mmall.param.RoleParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @InterFaceName : SysRoleService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-27 22:37
 * @Description : 角色接口定义
 */
@Service
public interface SysRoleService {

  /**
   * 保存角色
   *
   * @param param
   */
  void save(RoleParam param);

  /**
   * 更新角色
   *
   * @param param
   */
  void update(RoleParam param);

  /**
   * 更具用户角色
   *
   * @param userId
   * @return
   */
  List<SysRole> getRoleListByUserId(int userId);

  /**
   * 根据权限获取角色列表
   *
   * @param aclId
   * @return
   */
  List<SysRole> getRoleListByAclId(int aclId);

  /**
   * 根据角色获取用户列表
   *
   * @param roleList
   * @return
   */
  List<SysUser> getUserListByRoleList(List<SysRole> roleList);

  /**
   * 获取角色树
   *
   * @param roleId
   * @return
   */
  // Object getRoleTreeByRoleId(int roleId);

  /**
   * 获取全部角色
   *
   * @return
   */
  List<SysRole> getAll();

  /**
   * 变更角色权限
   *
   * @param roleId
   * @param aclIds
   */
  // void changeRoleAcls(int roleId, String aclIds);

  /**
   * 变更用户角色
   *
   * @param roleId
   * @param userIds
   */
  // void changeRoleUsers(int roleId, String userIds);

  /**
   * 获取特定角色用户列表
   *
   * @param roleId
   * @return
   */
  // Map<String, List<SysUser>> getUserListByRoleId(int roleId);

}
