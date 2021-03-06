/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.UserDetails;
import com.park.parkinglot.entity.User;
import java.util.ArrayList;
import java.util.Collection;
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

    public List<UserDetails> getAllUsers() {

        LOG.info("getAllUsers");
        try {
            Query q = entityManager.createQuery("select u from User u");
            List<User> usersList = (List<User>) q.getResultList();
            return copyUsersToDetails(usersList);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }
      
    public void createUser(String username , String email  , String passwordSha256 , String positin)
    {
     User user= new User();
     user.setUsername(username);
     user.setEmail(email);
     user.setPassword(passwordSha256);
     user.setPosition(positin);
     
     entityManager.persist(user);
    }
    
    public List<UserDetails> copyUsersToDetails(List<User> usersList) {
        List<UserDetails> detailsList = new ArrayList<UserDetails>();
        for (User user : usersList) {
            UserDetails userDetails = new UserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPosition());
            detailsList.add(userDetails);
        }
        return detailsList;
    }
    
    public Collection<String> findUsernames(Collection<Integer> userIds)
    {
    LOG.info("findUsernames");
    List<String> usernames=(List<String>) entityManager.createQuery("select u.username from User u where u.id IN ?1")
            .setParameter(1, userIds)
            .getResultList();
      return usernames;
    }

}
