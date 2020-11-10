/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.CarDetails;
import com.park.parkinglot.common.UserDetails;
import com.park.parkinglot.entity.Car;
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
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<Car> getAllCars() {
        LOG.info("getAllCars");
        try {
            List<Car> cars = (List<Car>) entityManager.createQuery("select c from Car c").getResultList();
            return cars;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<CarDetails> getAllCars2() {
        LOG.info("getAllCars");
        try {
            Query q = entityManager.createNamedQuery("Car.FindAll");
            List<Car> carsList = q.getResultList();
            return copyCarsToDetails(carsList);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    private List<CarDetails> copyCarsToDetails(List<Car> carsList) {
        List<CarDetails> detailsList = new ArrayList<CarDetails>();
        for (Car car : carsList) {
            CarDetails carDetails = new CarDetails(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getUser().getUsername());
            detailsList.add(carDetails);
        }
        return detailsList;
    }
}
