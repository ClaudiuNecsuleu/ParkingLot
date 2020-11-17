<%-- 
    Document   : editCar
    Created on : 17.11.2020, 13:46:50
    Author     : Clau
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="EditCar">

    <form class="needs-validation" novalidate="" method="POST" action="${pageContext.request.contextPath}/EditCar">

        <div class="row">
            <div class="mb-3">
                <label for="license_plate">License plate</label>
                <input type="text" class="form-control" name="license_plate" id="license_plate" placeholder="License plate" required value="${car.licensePlate}"/>
                <div class="invalid-feedback" style="with:100%">
                    License plate is reuired.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="mb-3">
                <label for="parking_spot">Parking Spot</label>
                <input type="text" class="form-control" name="parking_spot" id="parking_spot" placeholder="Parking Spot" required value="${car.parkingSpot}"/>
                <div class="invalid-feedback" style="with:100%">
                    Parking Spot is reuired.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="mb-3">
                <label for="owner_id">Owner</label>
                <select class="custom-select d-block w-100" name="owner_id" id="owner_id"  required"/>
                <option value=""">Chose...</option>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <option value="${user.id}" ${car.username eq user.username ? 'selected' : ''}> ${user.username}</option>
                </c:forEach>
          
                </select>
            </div>
        </div>
        <input type="hidden" name="car_id" value="${car.id}"/>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>

    </form>
</t:pageTemplate>
