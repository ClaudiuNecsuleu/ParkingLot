/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot.ejb;

import com.park.parkinglot.common.CarDetails;
import com.park.parkinglot.common.PhotoDetails;
import com.park.parkinglot.entity.Car;
import com.park.parkinglot.entity.Photo;
import com.park.parkinglot.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Clau
 */
@Stateless
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;
    
    public CarDetails findById(Integer carID)
    {
    Car car = entityManager.find(Car.class,carID);
    return new CarDetails((car.getId()), car.getLicensePlate(), car.getParkingSpot(), car.getUser().getUsername());
    }

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
    
    public void createCar(String licensePlace ,String parkingSpot,Integer userID)
    {
    LOG.info("createCar");
    Car car=new Car();
    car.setLicensePlate(licensePlace);
    car.setParkingSpot(parkingSpot);
   
    User user = entityManager.find(User.class,userID);
    user.getCarList().add(car);
    car.setUser(user);
    //entityManager.merge(user);
    entityManager.persist(car);
    }
    
    public void updateCar(Integer carID , String licensePlace ,String parkingSpot, Integer userID){
    LOG.info("updateCar");
    Car car= entityManager.find(Car.class, carID);
    car.setLicensePlate(licensePlace);
    car.setParkingSpot(parkingSpot);
    
    User oldUser = car.getUser();
    oldUser.getCarList().remove(car);
    
    User user=entityManager.find(User.class, userID);
    user.getCarList().add(car);
    car.setUser(user);
    
    entityManager.merge(car);
    }
    
    public void deleteCarsByIds(List<Integer> ids){
    LOG.info("deleteCarsByIds");
    for(Integer id : ids)
    {
    Car car = entityManager.find(Car.class, id);
    entityManager.remove(car);
    }
    }
    
    public void addPhotoToCar(Integer carId,String filename, String fileType,byte[] fileContent){
    LOG.info("addPhotoToCar");
    Photo photo = new Photo();
    photo.setFilename(filename);
    photo.setFileType(fileType);
    photo.setFileContent(fileContent);
    
    Car car = entityManager.find(Car.class, carId);
    car.setPhoto(photo);
    photo.setCar(car);
    entityManager.persist(photo);
    }
    
    public PhotoDetails findPhotoByCarId(Integer carId)
    {
    
        TypedQuery<Photo> typedQuery=entityManager.createQuery("select p from Photo p where p.car.id= :id",Photo.class).setParameter("id", carId);
        List<Photo> photos = typedQuery.getResultList();
        if(photos.isEmpty())
        {
        return null;
        }
        Photo photo = photos.get(0);
        return new PhotoDetails(photo.getId(), photo.getFilename(), photo.getFileType(), photo.getFileContent());
                }
}
