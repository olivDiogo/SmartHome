package smarthome.controller.rest;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.service.IUnitService;
import smarthome.domain.unit.Unit;
import smarthome.utils.dto.UnitDTO;

@RestController
@RequestMapping("/unit")
public class UnitControlller {

  @NotNull
  private final IUnitService unitService;

  @NotNull
  private final IAssembler<Unit, UnitDTO> unitAssembler;

  /**
   * Constructor for UnitController
   *
   * @param unitService   is the service for unit
   * @param unitAssembler is the assembler for unit
   */
  @Autowired
  public UnitControlller(IUnitService unitService, IAssembler<Unit, UnitDTO> unitAssembler) {
    this.unitService = unitService;
    this.unitAssembler = unitAssembler;
  }

  /**
   * Get all units
   *
   * @return ResponseEntity<CollectionModel < UnitDTO>> is the response entity
   */
  @GetMapping("/all")
  public ResponseEntity<CollectionModel<UnitDTO>> getUnits() throws EmptyReturnException {
    List<Unit> unitList = unitService.getAllMeasurementTypes();
    if (unitList.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    List<UnitDTO> unitDTOList = unitAssembler.domainToDTO(unitList);
    CollectionModel<UnitDTO> resource = CollectionModel.of(unitDTOList,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UnitControlller.class).getUnits())
            .withSelfRel());
    return ResponseEntity.ok(resource);
  }
}
