package maintask;

import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlane() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlane();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane:passengerPlanes) {
            if (passengerPlane.getPassengersCapacity()>planeWithMaxCapacity.getPassengersCapacity()){
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane militaryPlane : militaryPlanes){
            if (militaryPlane.getType().equals(MilitaryType.TRANSPORT)){
                transportMilitaryPlanes.add(militaryPlane);
            }
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane militaryPlane : militaryPlanes){
            if (militaryPlane.getType().equals(MilitaryType.BOMBER)) {
                bomberMilitaryPlanes.add(militaryPlane);
            }
        }
        return bomberMilitaryPlanes;
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof ExperimentalPlane) {
                experimentalPlanes.add((ExperimentalPlane) plane);
            }
        }
        return experimentalPlanes;
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
