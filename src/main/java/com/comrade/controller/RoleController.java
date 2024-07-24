package com.comrade.controller;

import com.comrade.entity.RoleEntity;
import com.comrade.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/save")
    public RoleEntity save(@RequestBody RoleEntity roleEntity){
       return roleService.save(roleEntity);
    }
    @GetMapping("/all")
    public List<RoleEntity> all(){
       return roleService.all();
    }
}
