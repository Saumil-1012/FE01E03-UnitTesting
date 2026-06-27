package de.tum.ise;

public class SpellBook {

    private int mana;
    private final SpellSchool school;
    private final int intellect;

    public SpellBook(int mana, SpellSchool school, int intellect) {
        this.mana = mana;
        this.school = school;
        this.intellect = intellect;
    }

    public int getMana() {
        return mana;
    }

    public SpellSchool getSchool() {
        return school;
    }

    public int getIntellect() {
        return intellect;
    }

    public CastResult castSpell(SpellSchool spellSchool, int baseManaCost) {
        // TODO: Implement this method
        return null;
    }

    public int rechargeMana(int amount) {
        // TODO: Implement this method
        return 0;
    }
}
