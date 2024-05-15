package smarthome.IT;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UnitIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IUnitRepository unitRepository;


  /**
   * Tests successful retrieval of all units.
   */
  @Test
  void shouldReturnAllUnits_WhenRequested() throws Exception {
    // Arrange
    IUnitFactory unitFactory = new UnitFactoryImpl();
    UnitDescription unitDescription = new UnitDescription("Test");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.findAll()).thenReturn(List.of(unit));
    // Act & Assert
    mockMvc.perform(get("/units")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").exists());
  }

  /**
   * Tests the scenario where no units are found.
   */
  @Test
  void shouldReturnNotFound_WhenNoUnitsAvailable() throws Exception {
    // Act & Assert
    mockMvc.perform(get("/units")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").exists());
  }

}
