package SmartHome.domain;

import org.junit.jupiter.api.Test;

public class ActuatorTypeFactoryTest {

    /**
     * Tests the instantiation of the ActuatorTypeFactory
     *
     * @throws InstantiationException if the description is null
     */
    @Test
    void shouldReturnObjectWhenValidDescription() throws InstantiationException {
        //Arrange
        String description = "description";

        //Act
        new ActuatorTypeFactory().createActuatorType(description);
    }
}
