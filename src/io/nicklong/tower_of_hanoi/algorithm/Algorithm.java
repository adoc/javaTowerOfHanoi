package io.nicklong.tower_of_hanoi.algorithm;

import io.nicklong.tower_of_hanoi.model.Disc;
import io.nicklong.tower_of_hanoi.model.Landscape;
import io.nicklong.tower_of_hanoi.model.Position;

import java.util.ArrayList;

/**
 * Base Algorithm Class and state machine.
 */
public abstract class Algorithm implements IAlgorithm {
    private Landscape landscape;
    private Position sourcePosition;
    private Position lastTargetPosition;

    protected abstract Position doSelectSource();
    protected abstract Position doSelectTarget();

    /**
     * Get the last successfully selected source Position.
     * @return Position
     */
    protected Position getSourcePosition() {
        return sourcePosition;
    }

    /**
     * Get the last successfully targeted Position.
     * @return Position
     */
    protected Position getLastTargetPosition() {
        return lastTargetPosition;
    }

    /**
     * Get the ordinal of the Position on the Landscape.
     * @param position Position to get ordinal.
     * @return Ordinal of Position.
     */
    protected int getPositionOrdinal(Position position) {
        return landscape.getPositionOrdinal(position);
    }

    /**
     * Get all eligible source positions available on the Landscape.
     * @return Eligible source Positions ArrayList
     */
    protected ArrayList<Position> getEligibleSources() {
        return landscape.getEligibleSources();
    }

    /**
     * Get all eligible target positions available on the Landscape.
     * @return Eligible targets for this Disc to move
     */
    protected ArrayList<Position> getEligibleTargets() {
        return landscape.getEligibleTargets(sourcePosition.getTopDisc());
    }

    /**
     * Make a move after selectSource and selectTarget are executed.
     * @param throwExc Throw an exception or return false of an invalid move is made.
     * @return If throwExc is false, we return a boolean indicating if the move was made.
     */
    @Override
    public boolean makeMove(boolean throwExc) {
        Disc disc = sourcePosition.removeDisc();
        if (lastTargetPosition.stackDisc(disc))
            return true;
        else if (throwExc)
            throw new UnsupportedOperationException("The algorithm attempted a move that was not allowed.");
        else {
            // Put the disc back.
            assert sourcePosition.stackDisc(disc);
            return false;
        }
    }

    /**
     * Make a move after selectSource and selectTarget are executed.
     */
    @Override
    public void makeMove() {
        this.makeMove(true);
    }

    /**
     * Calls Algorithm implementation of selectSource and stores the Position in the Algorithm state.
     * @return Selected source Position
     */
    @Override
    public Position selectSource() {
        // TODO: There has to be a better factor for this. It would be nice for Subclasses to implement IAlgorithm.
        sourcePosition = doSelectSource();
        return sourcePosition;
    }

    /**
     * Calls Algorithm implementation of selectTarget and stores the Position in the Algorithm state.
     * @return Selected target Position
     */
    @Override
    public Position selectTarget() {
        // TODO: There has to be a better factor for this.
        lastTargetPosition = doSelectTarget();
        return lastTargetPosition;
    }

    /**
     * Algorithm base constructor.
     * @param landscape The beautiful landscape upon which we toil.
     */
    public Algorithm(Landscape landscape) {
        this.landscape = landscape;
    }
}
