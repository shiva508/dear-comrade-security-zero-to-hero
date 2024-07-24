package com.comrade.service;

import com.comrade.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    RoleEntity save(RoleEntity roleEntity);
    List<RoleEntity> all();
}
