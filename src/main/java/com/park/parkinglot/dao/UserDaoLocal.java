/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.dao;

import com.park.parkinglot.entity.User;
import javax.ejb.Local;

/**
 *
 * @author Clau
 */
@Local
public interface UserDaoLocal {

    void addUser(User user);
    
}
