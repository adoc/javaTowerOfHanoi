package io.nicklong.tower_of_hanoi.algorithm;

import io.nicklong.tower_of_hanoi.model.Position;

/**
 * Interface for an Algorithm state machine.
 */
public interface IAlgorithm {
    Position selectSource();
    Position selectTarget();

    boolean makeMove(boolean throwExc);

    void makeMove();
}
