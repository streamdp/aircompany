import maintask.Airport;
import planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;
import java.util.function.Predicate;

import static org.testng.Assert.*;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    );

    private static PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    @Test
    public void testGetTransportMilitaryPlanes() {
        assertEquals(new Airport(planes).getTransportMilitaryPlanes().get(0).getType(),MilitaryType.TRANSPORT);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        assertTrue(new Airport(planes).getPassengerPlaneWithMaxPassengersCapacity()
                .equals(planeWithMaxPassengerCapacity));
    }

    @Test
    public void testIsSortingCorrect() {
        List<? extends Plane> planesSortedByMaxLoadCapacity = new Airport(planes).sortByMaxLoadCapacity().getPlanes();
        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            int currentPlaneMaxloadCapacity = planesSortedByMaxLoadCapacity.get(i).getMaxLoadCapacity();
            int nextPlaneMaxLoadCapacity = planesSortedByMaxLoadCapacity.get(i + 1).getMaxLoadCapacity();
            Assert.assertFalse( currentPlaneMaxloadCapacity > nextPlaneMaxLoadCapacity,
                    "Sorting incorrect! currentPlaneMaxloadCapacity > nextPlaneMaxLoadCapacity\n " +
                            "Error position = " + i);
        }
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        new Airport(planes).getBomberMilitaryPlanes().forEach(militaryPlane -> {
            assertEquals(militaryPlane.getType(), MilitaryType.BOMBER);
        });
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified(){
        new Airport(planes).getExperimentalPlanes().forEach(experimentalPlane -> {
            assertTrue(experimentalPlane.getClassificationLevel()
                    .compareTo(ClassificationLevel.UNCLASSIFIED) > 0);
        });
    }
}
