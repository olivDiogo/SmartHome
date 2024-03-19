package SmartHomeDDD.ddd;

public interface DomainID extends ValueObject {
    String getId();
    String toString();
    boolean equals(Object o);
    int hashCode();

	
}
