package com.atguigu.dao.impl;

import com.atguigu.bean.Soldier;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.SoldierDao;

import java.sql.SQLException;
import java.util.List;

/**
 * 包名:com.atguigu.dao.impl
 *
 * @author Leevi
 * 日期2021-05-13  14:17
 */
public class SoldierDaoImpl extends BaseDao<Soldier> implements SoldierDao {
    @Override
    public List<Soldier> findAll() throws SQLException {
        String sql = "select soldier_id soldierId,soldier_name soldierName,soldier_weapon soldierWeapon from t_soldier";
        return getBeanList(Soldier.class,sql);
    }

    @Override
    public void addSoldier(Soldier soldier) throws SQLException {
        String sql = "insert into t_soldier (soldier_name,soldier_weapon) values (?,?)";
        update(sql,soldier.getSoldierName(),soldier.getSoldierWeapon());
    }

    @Override
    public void deleteSoldierById(Integer id) throws SQLException {
        String sql = "delete from t_soldier where soldier_id=?";
        update(sql,id);
    }

    @Override
    public Soldier findSoldierById(Integer id) throws SQLException {
        String sql = "select soldier_id soldierId,soldier_name soldierName,soldier_weapon soldierWeapon from t_soldier where soldier_id=?";

        return getBean(Soldier.class,sql,id);
    }

    @Override
    public void updateSoldier(Soldier soldier) throws SQLException {
        String sql = "update t_soldier set soldier_name=?, soldier_weapon=? where soldier_id=?";
        update(sql,soldier.getSoldierName(),soldier.getSoldierWeapon(),soldier.getSoldierId());
    }
}
