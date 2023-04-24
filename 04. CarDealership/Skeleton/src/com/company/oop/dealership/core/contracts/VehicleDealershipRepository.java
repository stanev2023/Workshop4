package com.company.oop.dealership.core.contracts;

import com.company.oop.dealership.models.contracts.*;
import com.company.oop.dealership.models.enums.UserRole;

import java.util.List;

public interface VehicleDealershipRepository {

    List<User> getUsers();

    User getLoggedInUser();

    void addUser(User userToAdd);

    User findUserByUsername(String username);

    Car createCar(String make, String model, double price, int seats);

    Motorcycle createMotorcycle(String make, String model, double price, String category);

    Truck createTruck(String make, String model, double price, int weightCapacity);

    User createUser(String username, String firstName, String lastName, String password, UserRole userRole);

    Comment createComment(String content, String author);

    boolean hasLoggedInUser();

    void login(User user);

    void logout();
}
