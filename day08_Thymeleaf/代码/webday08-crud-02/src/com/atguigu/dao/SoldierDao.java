package com.atguigu.dao;

import com.atguigu.bean.Soldier;

import java.sql.SQLException;
import java.util.List;

/**
 * 包名:com.atguigu.dao
 *
 * @author Leevi
 * 日期2021-05-13  14:16
 */
public interface SoldierDao {

    /**
     * 查询所有士兵
     * @return
     */
    List<Soldier> findAll() throws SQLException;

    /**
     * 添加士兵信息
     * @param soldier
     * @throws SQLException
     */
    void addSoldier(Soldier soldier) throws SQLException;

    /**
     * 根据id删除士兵信息
     * @param id
     * @throws SQLException
     */
    void deleteSoldierById(Integer id) throws SQLException;

    /**
     * 根据id查询士兵信息
     * @param id
     * @return
     * @throws SQLException
     */
    Soldier findSoldierById(Integer id) throws SQLException;

    /**
     * 修改士兵信息
     * @param soldier
     * @throws SQLException
     */
    void updateSoldier(Soldier soldier) throws SQLException;
}
