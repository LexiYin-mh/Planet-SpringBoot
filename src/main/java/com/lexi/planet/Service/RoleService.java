package com.lexi.planet.Service;

import com.lexi.planet.dto.RoleDto;

public interface RoleService extends CrudService<RoleDto, Long>{

    RoleDto getRoleDtoByName(String name);

}
