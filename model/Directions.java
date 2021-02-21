package model;

/**
 * Enumeration of the directions needed in model.
 *
 * @author Max Malmer, c17mmr@cs.umu.se
 * @version 1.0
 */
public enum Directions {
    WEST(0),
    EAST(1),
    NORTH(2),
    SOUTH(3);
    int value;

    /**
     * Enum constructor. Input 0 for WEST, 1 for EAST, 2 for NORTH
     * and 3 for SOUTH.
     *
     * @param v an int representing a direction.
     */
    Directions(int v) {
        this.value = v;
    }

    /**
     * Gets the current direction.
     *
     * @return this direction.
     */
    public Directions getDirection() {
        return this;
    }

    /**
     * Gets the current direction as a String.
     *
     * @return the current enum as String.
     */
    public String toString() {
        return this.name();
    }
}
