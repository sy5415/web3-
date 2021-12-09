package com.atguigu.service.impl;

import com.atguigu.bean.Soldier;
import com.atguigu.dao.SoldierDao;
import com.atguigu.dao.impl.SoldierDaoImpl;
import com.atguigu.service.SoldierService;

import java.util.List;

/**
 * 包名:com.atguigu.service.impl
 *
 * @author Leevi
 * 日期2021-05-13  14:20
 */
public class SoldierServiceImpl implements SoldierService {
    private SoldierDao soldierDao = new SoldierDaoImpl();
    @Override
    public List<Soldier> findAllSoldier() throws Exception {
        return soldierDao.findAll();
    }

    @Override
    public void addSoldier(Soldier soldier) throws Exception {
        soldierDao.addSoldier(soldier);
    }

    @Override
    public void deleteSoldierById(Integer id) throws Exception{
        soldierDao.deleteSoldierById(id);
    }

    @Override
    public Soldier findSoldierById(Integer id) throws Exception {

        return soldierDao.findSoldierById(id);
    }

    @Override
    public void updateSoldier(Soldier soldier) throws Exception {
        soldierDao.updateSoldier(soldier);
    }
}
