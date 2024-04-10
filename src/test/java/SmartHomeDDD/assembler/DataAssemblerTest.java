package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.SensorDataDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataAssemblerTest {

    /**
     * Test to check if the convertSensorDataToParameters method returns an array of parameters, when given four of them.
     */
    @Test
    void shouldConvertSensorDataToParameters_WhenGivenFourParameters() {
        // Given
        SensorDataDTO sensorDataDTO = new SensorDataDTO("deviceID", "sensorModelPath", "sensorName", "sensorTypeID", new ArrayList<Double>(), new ArrayList<LocalDateTime>());

        // When
        Object[] result = DataAssembler.convertSensorDataToParameters(sensorDataDTO);

        // Then
        assertNotNull(result);
        assertEquals(4, result.length);
    }

    /**
     * Test to check if the convertSensorDataToParameters method returns an array of parameters, when given six of them.
     */
    @Test
    void shouldConvertSensorDataToParameters_WhenGivenSixParameters() {
        // Given
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(1.0);
        coordinates.add(2.0);
        ArrayList<LocalDateTime> dateTimes = new ArrayList<>();
        dateTimes.add(LocalDateTime.now());
        dateTimes.add(LocalDateTime.now().plusDays(1));
        SensorDataDTO sensorDataDTO = new SensorDataDTO("deviceID", "sensorModelPath", "sensorName", "sensorTypeID", coordinates, dateTimes);

        // When
        Object[] result = DataAssembler.convertSensorDataToParameters(sensorDataDTO);

        // Then
        assertNotNull(result);
        assertEquals(6, result.length);
    }
}
