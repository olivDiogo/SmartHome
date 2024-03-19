package SmartHomeDDD.ddd;

public interface DomainEntity<ID extends DomainID> {

	ID getID();

	boolean equals(Object o);
}
