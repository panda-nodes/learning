package com.pandanodes.controller;

import com.pandanodes.dao.mysql.SysMenuDao;
import com.pandanodes.dao.phoenix.TestTableDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private TestTableDao testTableDao;

    @Resource
    private SysMenuDao sysMenuDao;

    @GetMapping("/mysql")
    public List mysql(){
        return sysMenuDao.getMenuList();
    }

    @GetMapping("/phoenix")
    public List phoenix(){
        return testTableDao.list();
    }
}
