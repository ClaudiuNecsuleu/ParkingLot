/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.dao;

import com.park.parkinglot.entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Clau
 */
@Stateless
public class UserDao implements UserDaoLocal {
      @PersistenceContext
    private EntityManager entityManager;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addUser(User user) {
        try{
        entityManager.persist(user);
        }
        catch(RuntimeException e){
        throw new RuntimeException("add user -> error . Object: "+user.getUsername()+" ,"+ user.getEmail()+ " ,"+user.getPosition());
        }
    }
      
}
