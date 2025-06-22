/*
Exercise 2: Implementing the Factory Method Pattern

SimpleVehicleFactory
It demonstrates how an abstract factory method lets subclasses decide which concrete product to instantiate.
*/
public class SimpleVehicleFactory {
    public interface Vehicle {
        void move(); 
    }

    public static class Car implements Vehicle {
        @Override
        public void move() {
            System.out.println("Car is driving.");
        }
    }

    public static class Bike implements Vehicle {
        @Override
        public void move() {
            System.out.println("Bike is riding.");
        }
    }

    public static abstract class VehicleFactory {
        public abstract Vehicle createVehicle();
    }

    public static class CarFactory extends VehicleFactory {
        @Override
        public Vehicle createVehicle() {
            return new Car();
        }
    }

    public static class BikeFactory extends VehicleFactory {
        @Override
        public Vehicle createVehicle() {
            return new Bike();
        }
    }

    public static void main(String[] args) {
        System.out.println("-- Factory Method Pattern --");

        // Create a Car
        VehicleFactory carCreator = new CarFactory();
        Vehicle myCar = carCreator.createVehicle();
        myCar.move();

        // Create a Bike
        VehicleFactory bikeCreator = new BikeFactory();
        Vehicle myBike = bikeCreator.createVehicle();
        myBike.move();
    }
}


/*
OUTPUT:
-- Factory Method Pattern --
Car is driving.
Bike is riding.
*/