package netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import netology.entity.Country;
import netology.entity.Location;
import netology.geo.GeoService;
import netology.geo.GeoServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTests {


    @ParameterizedTest
    @MethodSource("sourceAdd")
    void testLocationByIp(Location expended, String ip) {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp(ip);
        assertEquals(expended.getCountry(), location.getCountry());
    }

    public static Stream<Arguments> sourceAdd() {
        return Stream.of(Arguments.of(new Location(null, null, null, 0), "127.0.0.1"),
                Arguments.of(new Location("Moscow", Country.RUSSIA, "Lenina", 15), "172.0.32.11"),
                Arguments.of(new Location("New York", Country.USA, " 10th Avenue", 32), "96.44.183.149"),
                Arguments.of(new Location("Moscow", Country.RUSSIA, null, 0), "172."),
                Arguments.of(new Location("New York", Country.USA, null, 0), "96."));

    }


    @Test
    void testExceptionByCoordinates() {

        GeoService geoService = new GeoServiceImpl();
        double a = 544.221, b = 4222.211;
        Class<RuntimeException> expectedExceptionClass = RuntimeException.class;

        Executable executable = () -> geoService.byCoordinates(a,b);

        assertThrows(expectedExceptionClass, executable);



    }
}