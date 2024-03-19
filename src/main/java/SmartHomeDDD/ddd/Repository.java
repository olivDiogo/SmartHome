package SmartHomeDDD.ddd;

import java.util.List;
import java.util.Optional;

public interface Repository<ID extends DomainID, T extends AggregateRoot<ID> > {
  
  T save(T entity);
  
  List<T> findAll();
  
  Optional<T> ofIdentity(ID objectID);
  
  boolean containsOfIdentity(ID objectID);
}