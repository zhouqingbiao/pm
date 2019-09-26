package com.zhouqb.pm.inspection;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Inspection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate xjrq;
    @Column
    private String xjry;
    @Column
    private String xjnr;
    @Column
    private String xjjg;
    @Column
    private Long physicalMachineId;
    @Column
    private Long virtualMachineId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getXjrq() {
        return xjrq;
    }

    public void setXjrq(LocalDate xjrq) {
        this.xjrq = xjrq;
    }

    public String getXjry() {
        return xjry;
    }

    public void setXjry(String xjry) {
        this.xjry = xjry;
    }

    public String getXjnr() {
        return xjnr;
    }

    public void setXjnr(String xjnr) {
        this.xjnr = xjnr;
    }

    public String getXjjg() {
        return xjjg;
    }

    public void setXjjg(String xjjg) {
        this.xjjg = xjjg;
    }

    public Long getPhysicalMachineId() {
        return physicalMachineId;
    }

    public void setPhysicalMachineId(Long physicalMachineId) {
        this.physicalMachineId = physicalMachineId;
    }

    public Long getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(Long virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }
}
