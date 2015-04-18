package io.nicklong.tower_of_hanoi.algorithm;

import io.nicklong.tower_of_hanoi.model.Position;

/**
 *
 */
public interface IAlgorithm {
    Position selectSource();

    Position selectTarget();

    void makeMove(Position source, Position target);
}
