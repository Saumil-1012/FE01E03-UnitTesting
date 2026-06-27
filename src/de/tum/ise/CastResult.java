package de.tum.ise;

import java.util.Objects;

public class CastResult {
    private final boolean success;
    private final double damageDealt;
    private final int actualManaCost;

    public CastResult(boolean success, double damageDealt, int actualManaCost) {
        this.success = success;
        this.damageDealt = damageDealt;
        this.actualManaCost = actualManaCost;
    }

    public boolean isSuccess() {
        return success;
    }

    public double getDamageDealt() {
        return damageDealt;
    }

    public int getActualManaCost() {
        return actualManaCost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CastResult other = (CastResult) obj;
        return success == other.success &&
                Double.compare(other.damageDealt, damageDealt) == 0 &&
                actualManaCost == other.actualManaCost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, damageDealt, actualManaCost);
    }

    @Override
    public String toString() {
        return "CastResult{" +
                "success=" + success +
                ", damageDealt=" + damageDealt +
                ", actualManaCost=" + actualManaCost +
                '}';
    }
}
