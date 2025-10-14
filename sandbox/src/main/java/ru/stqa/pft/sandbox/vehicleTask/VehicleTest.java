package ru.stqa.pft.sandbox.vehicleTask;

import static ru.stqa.pft.sandbox.vehicleTask.VehicleTest.EngineType.*;
import static ru.stqa.pft.sandbox.vehicleTask.VehicleTest.FuelType.*;

public class VehicleTest {

    public static class Program {
        public static void main(String[] args) {
            Vehicle car = new Car("Toyota", 120,
                    new Engine(GASOLINE_ENGINE, GASOLINE), 1985, "красный");
            Vehicle plane = new Airplane("Boeing 747", 400,
                    new Engine(HYBRID_ENGINE, KEROSENE), 30);
            Vehicle ship = new Ship("Наутилус", 37,
                    new Engine(DIESEL_ENGINE, DIESEL), 20);
            Vehicle bicycle = new Bicycle("Forward", 10,
                    new Engine(EngineType.NONE, FuelType.NONE), "голубой");

            Vehicle[] vehicles = {car, plane, ship, bicycle};

            for (Vehicle vehicle : vehicles) {
                System.out.println("");
                if (vehicle != bicycle) {
                    vehicle.engine.start();
                    showInfo(vehicle);
                    vehicle.engine.stop();
                }
                else {
                    showInfo(vehicle); // если у нас велосипед, то не заводим моторы
                }
            }
        }

        private static void showInfo(Vehicle vehicle) {
            vehicle.info();
            System.out.println(vehicle.getInfo());
            vehicle.drive();
        }
    }

    public static final class Car extends Vehicle {
        final Integer year;
        final String color;

        public Car(String markName, Integer speed, Engine engine, Integer year, String color) {
            super(markName, speed, engine);
            this.year = year;
            this.color = color;
        }

        @Override
        public void drive() {
            System.out.println(markName + " едет по дороге");
        }

        @Override
        public String getInfo() {
            return String.format("Машина: %s, Скорость: %d, Вид двигателя: %s, Тип топлива: %s, Год: %s, Цвет: %s",
                    markName, speed, engine.engineType.name, engine.fuelType.name, year, color);
        }
    }

    public static final class Airplane extends Vehicle {
        final Integer wingsSize;

        public Airplane(String markName, Integer speed, Engine engine, Integer wingsSize) {
            super(markName, speed, engine);
            this.wingsSize = wingsSize;
        }

        @Override
        void drive() {
            System.out.println(markName + " едет по дороге");
        }

        @Override
        String getInfo() {
            return String.format("Самолет: %s, Скорость: %d, Вид двигателя: %s, Тип топлива: %s, Размах крыльев: %s",
                    markName, speed, engine.engineType.name, engine.fuelType.name, wingsSize);
        }
    }

    public static final class Ship extends Vehicle {
        final Integer size;

        public Ship(String markName, Integer speed, Engine engine, Integer size) {
            super(markName, speed, engine);
            this.size = size;
        }

        @Override
        void drive() {
            System.out.println(markName + " едет по дороге");
        }

        @Override
        String getInfo() {
            return String.format("Корабль: %s, Скорость: %d, Вид двигателя: %s, Тип топлива: %s, Размер: %s",
                    markName, speed, engine.engineType.name, engine.fuelType.name, size);
        }
    }

    public static final class Bicycle extends Vehicle {
        final String color;

        public Bicycle(String markName, Integer speed, Engine engine, String color) {
            super(markName, speed, engine);
            this.color = color;
        }

        @Override
        void drive() {
            System.out.println(markName + " едет по дороге");
        }

        @Override
        String getInfo() {
            return String.format("Велосипед: %s, Скорость: %d, Вид двигателя: %s, Тип топлива: %s, Цвет: %s",
                    markName, speed, engine.engineType.name, engine.fuelType.name, color);
        }
    }

    public static sealed abstract class Vehicle permits Car, Airplane, Ship, Bicycle {
        public String markName;
        public Integer speed;
        public Engine engine;

        public Vehicle(String markName, Integer speed, Engine engine) {
            this.markName = markName;
            this.speed = speed;
            this.engine = engine;
        }

        public void info() {
            System.out.println("Показываем информацию о транспортном средстве");
        }

        abstract void drive();

        abstract String getInfo();
    }

    public interface IEngine {
        void start();
        void stop();
    }

    public static class Engine implements IEngine {
        final EngineType engineType;
        final FuelType fuelType;

        public Engine(EngineType engineType, FuelType fuelType) {
            this.engineType = engineType;
            this.fuelType = fuelType;
        }

        @Override
        public void start() {
            System.out.println("Завести мотор!");
        }

        @Override
        public void stop() {
            System.out.println("Заглушить мотор!");
        }
    }

    public enum EngineType {
        ELECTRIC_MOTOR("электромотор"),
        GASOLINE_ENGINE("бензиновый двигатель"),
        DIESEL_ENGINE("дизельный двигатель"),
        STEAM_ENGINE("паровой двигатель"),
        GAS_TURBINES("газовые турбины"),
        HYBRID_ENGINE("гибридный двигатель"),
        NONE("нет двигателя");

        final String name;

        EngineType(String name) {
            this.name = name;
        }
    }

    public enum FuelType {
        GASOLINE("бензин"),
        DIESEL("дизель"),
        KEROSENE("керосин"),
        GAS("газ"),
        ELECTRIC("электричество"),
        AIR("воздух"),
        NONE("нет топлива");

        final String name;

        FuelType(String name) {
            this.name = name;
        }
    }
}

