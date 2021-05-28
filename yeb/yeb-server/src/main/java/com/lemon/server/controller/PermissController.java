package com.lemon.server.controller;

import com.lemon.server.constants.PermissionConstants;
import com.lemon.server.model.Role;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.pojo.vo.MenuVO;
import com.lemon.server.service.IMenuRoleService;
import com.lemon.server.service.IMenuService;
import com.lemon.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @des: 权限组-角色功能 (基础信息模块)
 * @author: Lemon
 * @Date : 2021/5/27 15:06
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public List<Role> get() {
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public RespBean add(@RequestBody Role role) {
        if (!role.getName().startsWith(PermissionConstants.ROLE_PREV_CONSTANTS)) {
            role.setName(PermissionConstants.ROLE_PREV_CONSTANTS + role.getName());
        }

        // todo: 待优化不能添加相同中文名称和code的 角色
        int result = roleService.getRoleByNameOrNameZh(role);
        if (result != 0) {
            return RespBean.error("已存在该角色名或标识名");
        }

        if (roleService.save(role)) {
            return RespBean.ok("添加成功！");

        }
        return RespBean.error("添加失败！");
    }


    @ApiOperation("删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean delete(@PathVariable("rid") Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("查询所有菜单")
    @GetMapping("/menus")
    public List<MenuVO> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable("rid") Integer rid) {
        return menuRoleService.getMidByRid(rid);
    }

    @ApiOperation("更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuByRoleId(Integer rid, Integer[] mIds) {
        return menuRoleService.updateMenuByRoleId(rid, mIds);
    }


}
