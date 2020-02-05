package maintask;

import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlane() {
        return planes
                .stream()
                .filter(plane -> plane instanceof PassengerPlane)
                .map(plane -> (PassengerPlane) plane)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return planes
                .stream()
                .filter(plane -> plane instanceof MilitaryPlane)
                .map(plane -> (MilitaryPlane) plane)
                .collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return getPassengerPlane()
                .stream()
                .max(Comparator.comparing(PassengerPlane::getPassengersCapacity))
                .orElseThrow(NoSuchElementException::new);
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanes()
                .stream()
                .filter(plane -> plane.getType().equals(MilitaryType.TRANSPORT))
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanes()
                .stream()
                .filter(plane -> plane.getType().equals(MilitaryType.BOMBER))
                .collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return planes
                .stream()
                .filter(plane -> plane instanceof ExperimentalPlane)
                .map(plane -> (ExperimentalPlane) plane)
                .collect(Collectors.toList());
    }

    public Airport sortByMaxDistance() {
        Collections.sort(planes, (Comparator<Plane>) (distanceOne, distanceTwo) ->
                distanceOne.getMaxFlightDistance() - distanceTwo.getMaxFlightDistance());
        return this;
    }

    public Airport sortByMaxSpeed() {
        Collections.sort(planes, (Comparator<Plane>) (speedOne, speedTwo) ->
                speedOne.getMaxSpeed() - speedTwo.getMaxSpeed());
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        Collections.sort(planes, (Comparator<Plane>) (loadCapacityOne, loadCapacityTwo) ->
                loadCapacityOne.getMaxLoadCapacity() - loadCapacityTwo.getMaxLoadCapacity());
        return this;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public void print(Collection<? extends Plane> collection) {
        collection.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "maintask.Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
