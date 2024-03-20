package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class SensorName implements ValueObject {
    private String _name;

        /**
        * Class constructor
         * @param name The sensor name to set.
         */
        public SensorName(String name) {
            setSensorName(name);
        }

        /**
         * Sets the sensor name after validating it.
         * @param name The sensor name to set.
         */
        private void setSensorName(String name) {
            if (name == null || name.isEmpty() || name.isBlank()) {
                throw new IllegalArgumentException("The sensor name cannot be null, blank, or empty.");
            }

            if (!name.matches("[a-zA-Z0-9 ]+")) {
                throw new IllegalArgumentException("The sensor name can only contain letters and numbers.");
            }

            _name = name.trim();
        }

        /**
         * Gets the sensor name.
         * @return The sensor name.
         */
        public String getSensorName() {
            return _name;
        }
}
