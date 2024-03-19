package SmartHomeDDD.ddd;

import java.util.List;

public interface Assembler <T> {
    T domainToDTO(T domainObject);
    List<T> domainToDTO(List<T> domainObjects);
}
