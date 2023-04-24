package com.company.oop.dealership.models.contracts;

import com.company.oop.dealership.models.enums.UserRole;

import java.util.List;

public interface User {

    String getUsername();

    String getFirstName();

    String getLastName();

    String getPassword();

    UserRole getRole();

    List<Vehicle> getVehicles();

    void addVehicle(Vehicle vehicle);

    void removeVehicle(Vehicle vehicle);

    void addComment(Comment commentToAdd, Vehicle vehicleToAddComment);

    void removeComment(Comment commentToRemove, Vehicle vehicleToRemoveComment);

    String printVehicles();

    boolean isAdmin();

}
