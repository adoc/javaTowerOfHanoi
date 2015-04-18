package io.nicklong.tower_of_hanoi.algorithm;

import io.nicklong.tower_of_hanoi.model.Landscape;
import io.nicklong.tower_of_hanoi.model.Position;

import java.util.ArrayList;

/**
 * Base Algorithm class.
 */
public abstract class Algorithm implements IAlgorithm {
    private Landscape landscape;
    private Position sourcePosition;
    private Position lastTargetPosition;

    protected abstract Position doSelectSource();

    protected abstract Position doSelectTarget();

    /**
     * Get the last successfully selected source Position.
     *
     * @return Position
     */
    protected Position getSourcePosition() {
        return sourcePosition;
    }

    /**
     * Get the last successfully targeted Position.
     *
     * @return Position
     */
    protected Position getLastTargetPosition() {
        return lastTargetPosition;
    }

    /**
     * Get the ordinal of the Position on the Landscape.
     *
     * @param position
     * @return int
     */
    protected int getPositionOrdinal(Position position) {
        return landscape.getPositionOrdinal(position);
    }

    /**
     * Get all eligible source positions available on the Landscape.
     *
     * @return ArrayList<Position>
     */
    protected ArrayList<Position> getEligibleSources() {
        return landscape.getEligibleSources();
    }

    /**
     * Get all eligible targe positions available on the Landscape.
     *
     * @return ArrayList<Position>
     */
    protected ArrayList<Position> getEligibleTargets() {
        return landscape.getEligibleTargets(sourcePosition.topDisc());
    }

    /**
     * Make a move after selectSource and selectTarget are executed.
     *
     * @param sourcePosition
     * @param targetPosition
     */
    @Override
    public void makeMove(Position sourcePosition, Position targetPosition) {
        if (targetPosition.stackDisc(sourcePosition.removeDisc())) {
            return;
        }
        throw new UnsupportedOperationException("The algorithm attempted a move that was not allowed.");
    }

    /**
     * Calls Algorithm implementation of selectSource and stores the Position in the Algorithm state.
     *
     * @return Position
     */
    @Override
    public Position selectSource() {
        sourcePosition = doSelectSource();
        return sourcePosition;
    }

    /**
     * Calls Algorithm implementation of selectTarget and stores the Position in the Algorithm state.
     *
     * @return Position
     */
    @Override
    public Position selectTarget() {
        lastTargetPosition = doSelectTarget();
        return lastTargetPosition;
    }

    /**
     * Algorithm base constructor.
     *
     * @param landscape
     */
    public Algorithm(Landscape landscape) {
        this.landscape = landscape;
    }
}
