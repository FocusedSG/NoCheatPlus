package fr.neatmonster.nocheatplus.actions.types.penalty;

import org.bukkit.entity.Player;

/**
 * Convenience implementation for input-specific effects (other than Player).
 * 
 * @author asofold
 *
 * @param <RI>
 *            The input type accepted by this penalty.
 */
public abstract class AbstractGenericPenalty<RI> implements GenericPenalty<RI> {

    /** The input type accepted by this penalty. */
    private final Class<RI> registeredInput;

    public AbstractGenericPenalty(Class<RI> registeredInput) {
        this.registeredInput = registeredInput;
    }

    /**
     * Always has input-specific effects.
     */
    @Override
    public boolean hasInputSpecificEffects() {
        return true;
    }

    @Override
    public Class<RI> getRegisteredInput() {
        return registeredInput;
    }

    /**
     * (Override to use player-specific effects. Consider using
     * AbstractPlayerPenalty instead, for simple player-specific-only
     * penalties.)
     */
    @Override
    public boolean hasPlayerEffects() {
        return false;
    }

    /**
     * Override to use player-specific effects.
     */
    @Override
    public void apply(Player player) {
    }

    /**
     * Implements isAssignableFrom test, to delegate to applyGenericEffects(RI).
     */
    @SuppressWarnings("unchecked")
    @Override
    public void apply(Object input) {
        if (registeredInput.isAssignableFrom(input.getClass())) {
            applyGenericEffects((RI) input);
        }
    }

    /**
     * Override for implementation of input-specific effects.
     * 
     * @param input
     */
    abstract void applyGenericEffects(RI input);

}
