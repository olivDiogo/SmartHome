package smart_home.value_object;

import smart_home.ddd.IDomainID;
import smart_home.ddd.IValueObject;

public class ModelPath implements IValueObject, IDomainID {
    private String path;

    public ModelPath(String path) {
        validatePath(path);
    }

    private void validatePath(String path) throws IllegalArgumentException {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid path.");
        } else {
            this.path = path;
        }
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public String getID() {
        return this.path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelPath modelPath = (ModelPath) o;
        return path.equals(modelPath.path);
    }

    /**
     * @return the hash code of the object.
     */
    @Override
    public int hashCode() {
        return path.hashCode();
    }


}
