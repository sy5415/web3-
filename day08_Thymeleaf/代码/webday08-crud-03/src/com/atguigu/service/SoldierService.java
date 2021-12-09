package com.atguigu.service;

import com.atguigu.bean.Soldier;

import java.util.List;

/**
 * 包名:com.atguigu.service
 *
 * @author Leevi
 * 日期2021-05-13  14:19
 */
public interface SoldierService {
    /**
     * 查询所有士兵信息
     * @return
     */
    List<Soldier> findAllSoldier() throws Exception;

    /**
     * 添加士兵信息
     * @param soldier
     * @throws Exception
     */
    void addSoldier(Soldier soldier) throws Exception;

    /**
     * 根据id删除士兵信息
     * @param id
     */
    void deleteSoldierById(Integer id) throws Exception;

    /**
     * 根据id查询士兵信息
     * @param id
     * @return
     * @throws Exception
     */
    Soldier findSoldierById(Integer id) throws Exception;

    /**
     * 修改士兵信息
     * @param soldier
     * @throws Exception
     */
    void updateSoldier(Soldier soldier) throws Exception;
}
