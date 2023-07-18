package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.dto.RoleDto;
import com.lexi.vlogapp.entity.Role;
import com.lexi.vlogapp.entity.User;

public interface RoleService extends CrudService<RoleDto, Long>{

    RoleDto getRoleDtoByName(String name);

}
