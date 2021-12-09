package com.atguigu.bean;

/**
 * 包名:com.atguigu.bean
 *
 * @author Leevi
 * 日期2021-05-13  14:01
 */
public class Soldier {
    private Integer soldierId;
    private String soldierName;
    private String soldierWeapon;

    public Soldier() {
    }

    public Soldier(Integer soldierId, String soldierName, String soldierWeapon) {
        this.soldierId = soldierId;
        this.soldierName = soldierName;
        this.soldierWeapon = soldierWeapon;
    }

    @Override
    public String toString() {
        return "Soldier{" +
                "soldierId=" + soldierId +
                ", soldierName='" + soldierName + '\'' +
                ", soldierWeapon='" + soldierWeapon + '\'' +
                '}';
    }

    public Integer getSoldierId() {
        return soldierId;
    }

    public void setSoldierId(Integer soldierId) {
        this.soldierId = soldierId;
    }

    public String getSoldierName() {
        return soldierName;
    }

    public void setSoldierName(String soldierName) {
        this.soldierName = soldierName;
    }

    public String getSoldierWeapon() {
        return soldierWeapon;
    }

    public void setSoldierWeapon(String soldierWeapon) {
        this.soldierWeapon = soldierWeapon;
    }
}
