/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Clau
 */
@Entity
@Table(name="CARS")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Integer id;

    @NotBlank
    @Column(name="UserKey")
    private int userKey;
    
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name ="LicensePlate")
    private String licensePlate;
    
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name="ParkingSpot")
    private String parkingSpot;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="USER_KEY")
    private User user;

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", userKey=" + userKey + ", licensePlate=" + licensePlate + ", parkingSpot=" + parkingSpot + ", user=" + user + '}';
    }

    
    
}
