package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;
import SmartHomeDDD.ddd.ValueObject;

public class ModelPath implements ValueObject, DomainID {
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
    public String getId() {
        return this.path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelPath modelPath = (ModelPath) o;
        return path.equals(modelPath.path);
    }


}
