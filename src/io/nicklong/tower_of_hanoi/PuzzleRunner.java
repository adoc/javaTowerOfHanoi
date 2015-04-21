package io.nicklong.tower_of_hanoi;

import io.nicklong.tower_of_hanoi.algorithm.IAlgorithm;
import io.nicklong.tower_of_hanoi.algorithm.IterativeAlgorithm;
import io.nicklong.tower_of_hanoi.model.Landscape;
import io.nicklong.tower_of_hanoi.model.Position;
import io.nicklong.utils.StringUtils;

import java.util.ArrayList;

/**
 * Run the algorithm, and output the results to the console.
 */
public class PuzzleRunner {

    private final int MAX_STEPS = 65536;     // Avoid long games.

    private Landscape landscape;
    private IAlgorithm algorithm;

    /**
     * Output the Landscape state to the console.
     */
    public void drawLandscape() {
        int numDiscs = landscape.getNumDiscs();
        int numPositions = landscape.getNumPositions();
        ArrayList<ArrayList<String>> landscapeRep = landscape.toList();
        String pad;

        for (int iterD = numDiscs - 1; iterD >= 0; iterD--) {
            for (int iterP = 0; iterP < numPositions; iterP++) {

                ArrayList<String> positionRep = landscapeRep.get(iterP);
                int padLength = (2 * numDiscs + 1);

                if (positionRep.size() - iterD > 0) {
                    String discRep = positionRep.get(positionRep.size() - iterD - 1);
                    int discSize = discRep.length();
                    int discPad = (2 * discSize + 1) / 2;

                    pad = StringUtils.repeatChar(' ', (padLength - discPad) / 2);

                    System.out.print(pad + discRep + pad);
                } else {
                    pad = StringUtils.repeatChar(' ', padLength / 2);
                    System.out.print(pad + "|" + pad);
                }
            }
            System.out.print("\n");
        }

        System.out.print(StringUtils.repeatChar('-', 6 * numDiscs + 3) + "\n");
    }

    /**
     * Run the puzzle algorithm.
     */
    public void run() {
        int count = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("Start:\b");
        drawLandscape();
        while (!landscape.isDone() && count < MAX_STEPS) {
            Position source = algorithm.selectSource();
            Position target = algorithm.selectTarget();
            algorithm.makeMove();

            count++;
            System.out.printf("Move: %d\n", count);
            drawLandscape();
        }
        long endTime = System.currentTimeMillis();

        if (landscape.isDone()) {
            System.out.printf("\nSolved in %d moves [Runtime %dms]", count, endTime - startTime);
        } else {
            System.out.println("Algorithm never found a solution. Stopped at " + count + "iterations");
        }
    }

    /**
     * Construct the puzzle with numDiscs.
     *
     * @param numDiscs Number of Discs to put on the Landscape.
     */
    public PuzzleRunner(int numDiscs) {
        this(numDiscs, 3);
    }

    /**
     * Construct the puzzle with numDiscs and numPositions.
     * Be aware that the existing Algorithms do not solve > 3 Positions.
     *
     * @param numDiscs     Number of Discs to put on the Landscape.
     * @param numPositions Number of Positions (though you probably want to keep this at 3)
     */
    public PuzzleRunner(int numDiscs, int numPositions) {
        assert numPositions > 2;
        assert numDiscs > 0 && numDiscs <= 16;

        landscape = new Landscape(numDiscs, numPositions);
        algorithm = new IterativeAlgorithm(landscape);
    }
}