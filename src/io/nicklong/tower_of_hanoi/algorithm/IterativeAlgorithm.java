package io.nicklong.tower_of_hanoi.algorithm;

import io.nicklong.tower_of_hanoi.model.Landscape;
import io.nicklong.tower_of_hanoi.model.Position;

import java.util.ArrayList;

/**
 * Implemented iterative from Wikipedia. Least number of steps at 2n - 1.
 * @author Nick Long (Implementation)
 * @see "https://en.wikipedia.org/wiki/Tower_of_Hanoi"
 */
public class IterativeAlgorithm extends LazySlaveAlgorithm {
    /*
    "A simple solution for the toy puzzle: Alternate moves between the smallest
    piece and a non-smallest piece. When moving the smallest piece, always move
    it to the next position in the same direction (to the right if the starting
    number of pieces is even, to the left if the starting number of pieces is
    odd). If there is no tower position in the chosen direction, move the piece
    to the opposite end, but then continue to move in the correct direction.
    For example, if you started with three pieces, you would move the smallest
    piece to the opposite end, then continue in the left direction after that.
    When the turn is to move the non-smallest piece, there is only one legal
    move. Doing this will complete the puzzle in the fewest number of moves."
     */

    private boolean even;   // Number of discs is even;

    /**
     * Subclass SelectTarget implementation.
     * @return Position
     */
    protected Position doSelectTarget() {
        ArrayList<Position> eligibleTargets = getEligibleTargets();
        int eligibleTargetsSize = eligibleTargets.size();
        int sourceOrdinal = getPositionOrdinal(getSourcePosition());
        Position position;

        for (int i = 0; i < eligibleTargetsSize; i++) {
            if (even) {
                position = eligibleTargets.get(i);                              // Rightward Bias
                if (sourceOrdinal < getPositionOrdinal(position)) {             //
                    return position;
                }
            } else {
                position = eligibleTargets.get(eligibleTargetsSize - i - 1);    // Leftward Bias
                if (sourceOrdinal > getPositionOrdinal(position)) {             //
                    return position;
                }
            }
        }

        /* We didn't find a move in the direction we wanted to go,
            so choose the far opposite Position.*/
        if (even) {
            return eligibleTargets.get(0);
        } else {
            return eligibleTargets.get(eligibleTargetsSize - 1);
        }
    }

    /**
     * Construct an IterativeAlgorithm
     * @param landscape The Landscape upon which to operate.
     */
    public IterativeAlgorithm(Landscape landscape) {
        super(landscape);
        this.even = landscape.getNumDiscs() % 2 == 0;
    }
}
