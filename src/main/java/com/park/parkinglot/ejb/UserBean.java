/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.UserDetails;
import com.park.parkinglot.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Clau
 */
@Stateless
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    private List<UserDetails> getAllUsers() {

        LOG.info("getAllUsers");
        try {
            Query q = entityManager.createQuery("select u from User u");
            List<User> usersList = (List<User>) q.getResultList();
            return copyUsersToDetails(usersList);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    private List<UserDetails> copyUsersToDetails(List<User> usersList) {
        List<UserDetails> detailsList = new ArrayList<UserDetails>();
        for (User user : usersList) {
            UserDetails userDetails = new UserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPosition());
            detailsList.add(userDetails);
        }
        return detailsList;
    }

}
