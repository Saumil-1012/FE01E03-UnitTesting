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
        boolean sameSchool = spellSchool == school;
        int actualManaCost = sameSchool ? baseManaCost : baseManaCost * 2;

        if (mana < actualManaCost) {
            return new CastResult(false, 0.0, 0);   // mana NOT modified
        }

        mana -= actualManaCost;
        double damageDealt = sameSchool
                ? baseManaCost * intellect * 1.5
                : baseManaCost * intellect;
        return new CastResult(true, damageDealt, actualManaCost);
    }

    public int rechargeMana(int amount) {
        if (amount <= 0) {
            return mana;
        }
        int cap = intellect * 10;
        mana = Math.min(mana + amount, cap);
        return mana;
    }
}
