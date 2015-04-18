package io.nicklong.tower_of_hanoi.model;

import io.nicklong.utils.StringUtils;

import java.util.ArrayList;

/**
 * The scene of our Tower construction.
 */
public class Landscape {
    private ArrayList<Position> positions = new ArrayList<>();
    private int numPositions;           // or Poles in the PuzzleRunner
    private int numDiscs;               // Number of discs at the starting position.

    public int getNumPositions() {
        return numPositions;
    }

    public int getNumDiscs() {
        return numDiscs;
    }

    public int getPositionOrdinal(Position position) {
        return positions.indexOf(position);
    }

    public Position getRightmostPosition() {
        return positions.get(positions.size() - 1);
    }

    public ArrayList<Position> getEligibleSources() {
        ArrayList<Position> eligibleSources = new ArrayList<>();
        for (Position position : positions) {
            if (position.hasDiscs()) {
                eligibleSources.add(position);
            }
        }
        return eligibleSources;
    }

    public ArrayList<Position> getEligibleTargets(Disc sourceDisc) {
        ArrayList<Position> eligibleTargets = new ArrayList<>();
        for (Position position : positions) {
            if (position.canStack(sourceDisc)) {
                eligibleTargets.add(position);
            }
        }
        return eligibleTargets;
    }

    public boolean isDone() {
        return getRightmostPosition().getNumDiscs() == getNumDiscs();
    }

    public ArrayList<ArrayList> toList() {
        ArrayList<ArrayList> output = new ArrayList<>();
        for (Position position : positions)
            output.add(position.toList());
        return output;
    }

    public void draw() {
        int numDiscs = getNumDiscs();
        int numPositions = getNumPositions();
        ArrayList<ArrayList> landscapeRep = toList();
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
     * Build the vast game landscape.
     */
    public Landscape(int numDiscs, int numPositions) {
        this.numDiscs = numDiscs;
        this.numPositions = numPositions;

        positions.add(new Position(getNumDiscs()));
        for (int i = 1; i < getNumPositions(); i++)
            positions.add(new Position());
    }
}