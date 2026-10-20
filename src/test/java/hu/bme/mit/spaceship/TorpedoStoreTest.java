package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    public void fire_FailureBecauseOfFailureRate() {
        // Arrange: 1 torpedó, de 100% (1.0) hibaráta
        TorpedoStore store = new TorpedoStore(1, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert: A tüzelés meghiúsul a hibaráta miatt
        assertFalse(result);
    }

    @Test
    public void fire_InvalidNumberOfTorpedosThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act & Assert: Ha 0 vagy negatív torpedót akarunk lőni, IllegalArgumentException-t várunk
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(0);
        });
    }

    @Test
    public void isEmpty_WhenEmpty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);
        store.fire(1); // Kilőjük az egyetlen torpedót

        // Assert
        assertTrue(store.isEmpty());
    }

    @Test
    public void isEmpty_WhenNotEmpty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5);

        // Assert
        assertFalse(store.isEmpty());
    }
}