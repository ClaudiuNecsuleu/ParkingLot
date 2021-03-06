<%-- 
    Document   : addPhoto
    Created on : 08.12.2020, 14:33:56
    Author     : Clau
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageTemplate pageTitle="Add Photo">
    
    <form class="needs-validation" novalidate method="POST" enctype="multipart/form-data"
        action="${pageContext.request.contextPath}/Cars/AddPhoto">
        <div class="row">
            <div class="col-md-6 mb-3">
                Licencse plate: ${car.licensePlate}
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="file">Photo</label>
                <input type="file" name="file" required>
                <div class="invalid-feedback">
                    Photo is required.
                </div>
            </div>
        </div>
            <input type="hidden" name="car_id" value="${car.id}" />
            <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
    </form>

</t:pageTemplate>