package SmartHomeDDD.ddd;

public interface DomainID extends ValueObject {
    String getId();
    boolean equals(Object o);
    int hashCode();

	
}
