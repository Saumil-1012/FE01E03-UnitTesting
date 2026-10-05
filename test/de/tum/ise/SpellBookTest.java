package de.tum.ise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpellBookTest {

    @Test
    void testSuccessfulCastSameSchool() {
        SpellBook book = new SpellBook(100, SpellSchool.FIRE, 10);

        CastResult result = book.castSpell(SpellSchool.FIRE, 20);

        assertTrue(result.isSuccess(), "Spell of own school should succeed");
        assertEquals(20, result.getActualManaCost(), "Same school: standard cost");
        assertEquals(80, book.getMana(), "100 - 20 = 80 mana left");
        assertEquals(300.0, result.getDamageDealt(), 0.001, "20 * 10 * 1.5 = 300 (50% bonus)");
    }

    @Test
    void testSuccessfulCastDifferentSchool() {
        SpellBook book = new SpellBook(100, SpellSchool.FIRE, 10);

        CastResult result = book.castSpell(SpellSchool.FROST, 20);

        assertTrue(result.isSuccess(), "Spell of other school should succeed with enough mana");
        assertEquals(40, result.getActualManaCost(), "Different school: double cost 20 * 2");
        assertEquals(60, book.getMana(), "100 - 40 = 60 mana left");
        assertEquals(200.0, result.getDamageDealt(), 0.001, "20 * 10 = 200 (no bonus)");
    }

    @Test
    void testFailedCastInsufficientMana() {
        // Same school: cost 20 > mana 10
        SpellBook book = new SpellBook(10, SpellSchool.FIRE, 10);
        CastResult result = book.castSpell(SpellSchool.FIRE, 20);
        assertFalse(result.isSuccess());
        assertEquals(10, book.getMana(), "Mana must stay unchanged");
        assertEquals(0.0, result.getDamageDealt(), 0.001);
        assertEquals(0, result.getActualManaCost());

        // Boundary: different school doubles the cost. Base cost 30 <= mana 50,
        // but actual cost 60 > 50 -> must fail.
        SpellBook book2 = new SpellBook(50, SpellSchool.FIRE, 10);
        CastResult result2 = book2.castSpell(SpellSchool.SHADOW, 30);
        assertFalse(result2.isSuccess(), "Double cost 60 exceeds mana 50");
        assertEquals(50, book2.getMana(), "Mana must stay unchanged");
        assertEquals(0.0, result2.getDamageDealt(), 0.001);
        assertEquals(0, result2.getActualManaCost());
    }

    @Test
    void testRechargeManaNormal() {
        SpellBook book = new SpellBook(50, SpellSchool.HOLY, 10); // cap = 100

        int newMana = book.rechargeMana(30);

        assertEquals(80, newMana, "Return value is the new mana");
        assertEquals(80, book.getMana(), "50 + 30 = 80 (below cap 100)");
    }

    @Test
    void testRechargeManaCap() {
        SpellBook book = new SpellBook(90, SpellSchool.HOLY, 10); // cap = 100

        int newMana = book.rechargeMana(50);

        assertEquals(100, newMana, "90 + 50 = 140 is capped at intellect * 10 = 100");
        assertEquals(100, book.getMana());
    }
}
