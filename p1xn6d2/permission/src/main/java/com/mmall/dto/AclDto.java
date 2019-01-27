package com.mmall.dto;

import com.mmall.model.SysAcl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName : AclDto
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-27 22:48
 * @Description : 权限dto  适配的类
 */
@Getter
@Setter
@ToString
public class AclDto extends SysAcl{

    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;

    public static AclDto adapt(SysAcl acl) {
        AclDto dto = new AclDto();
        BeanUtils.copyProperties(acl, dto);
        return dto;
    }}
