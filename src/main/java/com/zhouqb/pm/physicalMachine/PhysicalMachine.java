package com.zhouqb.pm.physicalMachine;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class PhysicalMachine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String computerName;
    @Column
    private String status;
    @Column
    private String mac;
    @Column
    private String ip;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String operatingSystem;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate mtime;
    @Column
    private String description;
    @Column
    private String maintenancePersonnel;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public LocalDate getMtime() {
        return mtime;
    }

    public void setMtime(LocalDate mtime) {
        this.mtime = mtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaintenancePersonnel() {
        return maintenancePersonnel;
    }

    public void setMaintenancePersonnel(String maintenancePersonnel) {
        this.maintenancePersonnel = maintenancePersonnel;
    }
}
