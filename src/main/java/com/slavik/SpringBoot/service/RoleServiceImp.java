package com.slavik.SpringBoot.service;

import com.slavik.SpringBoot.dao.RoleDao;
import com.slavik.SpringBoot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImp implements RoleService{

    private RoleDao roleDao;
    @Autowired
    public RoleServiceImp(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    @Transactional
    @Override
    public Role getOne(Long id){
        return roleDao.getOne(id);
    }
}
