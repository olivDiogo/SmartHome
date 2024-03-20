package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

public class MeasurementTest {

    @Test
    void shouldReturnExpectedUnitWhenGivenValidParameters(){
        //Arrange
        String unitID = "Unit1";

        //Act
        Measurement unit = new Measurement(unitID);
    }

    @Test
    void shouldThrowException_whenGivenNullUnitID(){
        //Arrange
        String unitID = null;
        String expectedMessage = "Invalid unit ID.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Measurement(unitID)
        );
    }

    @Test
    void shouldThrowException_whenGivenBlankUnitID(){
        //Arrange
        String unitID = " ";
        String expectedMessage = "Invalid unit ID.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Measurement(unitID)
        );
    }

    @Test
    void shouldThrowException_whenGivenEmptyUnitID(){
        //Arrange
        String unitID = "";
        String expectedMessage = "Invalid unit ID.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Measurement(unitID)
        );
    }


}
