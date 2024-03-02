package SmartHome.domain;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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

            try (MockedConstruction<ActuatorType> mocked = Mockito.mockConstruction(ActuatorType.class, (mock, context) -> {
                when(mock.getDescription()).thenReturn(description);
            })){;
            {
                //Act
                ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
                ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(description);
                //Assert
                assertEquals(description, actuatorType.getDescription());

            }
        }
    }


}

