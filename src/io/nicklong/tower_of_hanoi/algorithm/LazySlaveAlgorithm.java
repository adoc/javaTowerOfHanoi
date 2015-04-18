package io.nicklong.tower_of_hanoi.algorithm;

import io.nicklong.tower_of_hanoi.model.Landscape;
import io.nicklong.tower_of_hanoi.model.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * "Lazy Slave" algorithm for solving the Tower of Hanoi.
 *
 * @author Nick Long
 */
public class LazySlaveAlgorithm extends Algorithm {
    /*
    --A lowly unpaid worker that moves giant stone discs around.--

    Algorithm has two rules:
        The slave selects the smallest Disc that the slave did not just move.
        He moves the Disc on top of the next smallest Disc. (He prefers empty spots of course.)

    Postmortem:
        --Unfortunately the poor slave couldn't get the job done.--

        I.e. this Algorithm was a great guess at it, but does not work 100%. Decided to look at
        Wiki and realized we're not far off. We had some right ideas on left and right biasing,
        and focusing on the direction of smallest disc.

        Check out ``IterativeAlgorithm`` which actually inherits from this Class for the ``.selectsource`` method.
     */

    /**
     * Compare the top Disc of Positions.
     */
    protected class compTopDiscSize implements Comparator<Position> {
        @Override
        public int compare(Position pos1, Position pos2) {
            return pos1.topDisc().getSize() - pos2.topDisc().getSize();
        }
    }

    /**
     * Chooses the Position of the smallest eligible Disc that has not just moved.
     *
     * @return Position
     * @throws UnsupportedOperationException
     */
    protected Position doSelectSource() {
        // This is part of the Author's guessed algorithm that was correct!
        ArrayList<Position> eligibleSources = getEligibleSources();
        Collections.sort(eligibleSources, new compTopDiscSize());

        for (Position source : eligibleSources) {
            // Do not allow the former source (lastTargetPosition) to move.
            if (source != getLastTargetPosition()) {
                return source;
            }
        }

        throw new UnsupportedOperationException(this.getClass() + ".selectSource could not find a valid Position " +
                "to move from.");
    }

    /**
     * Chooses the the smallest eligible target Position.
     *
     * @return Position
     */
    protected Position doSelectTarget() {
        // This is where the algorithm fails to solve the puzzle.
        ArrayList<Position> eligibleTargets = getEligibleTargets();
        Collections.sort(eligibleTargets, new compTopDiscSize());
        return eligibleTargets.get(0);
    }

    /**
     * Construct a LazySlaveAlgorithm.
     *
     * @param landscape
     */
    public LazySlaveAlgorithm(Landscape landscape) {
        super(landscape);
    }
}
