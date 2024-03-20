package SmartHomeDDD.ddd;

import java.util.List;

public interface Assembler<ID extends AggregateRoot, T extends DTO >{
    T domainToDTO(ID domainEntity);
    List<T> domainToDTO(List<ID> domainEntities);
}
