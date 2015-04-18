package io.nicklong.tower_of_hanoi;

import io.nicklong.tower_of_hanoi.algorithm.IAlgorithm;
import io.nicklong.tower_of_hanoi.algorithm.IterativeAlgorithm;
import io.nicklong.tower_of_hanoi.model.Landscape;
import io.nicklong.tower_of_hanoi.model.Position;

public class PuzzleRunner {

    public final int MAX_STEPS = 65536;     // Avoid long games.

    private Landscape landscape;
    private IAlgorithm algorithm;

    public void run() {
        int count = 0;
        long startTime = System.currentTimeMillis();
        while (!landscape.isDone() && count < MAX_STEPS) {
            System.out.printf("Move: %d\n", count);
            landscape.draw();

            Position source = algorithm.selectSource();
            Position target = algorithm.selectTarget();

            algorithm.makeMove(source, target);

            count++;
        }
        long endTime = System.currentTimeMillis();

        if (landscape.isDone()) {
            System.out.printf("Move: %d\n", count);
            landscape.draw();
            System.out.printf("\nSolved in %d moves [Runtime %dms]", count, endTime - startTime);
        } else {
            System.out.println("Algorithm never found a solution. stopped at " + count + "iterations");
        }
    }

    public PuzzleRunner() {
        this(7, 3);
    }

    public PuzzleRunner(int numDiscs, int numPositions) {
        assert numPositions > 2;
        assert numDiscs > 0 && numDiscs <= 16;

        landscape = new Landscape(numDiscs, numPositions);
        algorithm = new IterativeAlgorithm(landscape);
    }
}
