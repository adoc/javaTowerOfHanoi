package io.nicklong.tower_puzzle;

public class Puzzle {

    public final int MAX_STEPS = 10;

    private int numPositions = 3;
    private int numDiscs = 3;

    private Landscape landscape;

    public int getNumPositions() {
        return numPositions;
    }

    public int getNumDiscs() {
        return numDiscs;
    }

    public void run() {
        //Try right on largest position.
        System.out.println(landscape.toList());
        int count = 0;
        while(!landscape.isDone() && count < MAX_STEPS) {



            // Recursive?
            // 1. We try
            Position source = landscape.largestPositionSmallestDisc();
            Position target = landscape.smallestRightOfSource(source);
            landscape.makeMove(source, target);


            System.out.println(landscape.toList());

            count++;
        }
    }

    public Puzzle(int numPositions, int numDiscs) {
        assert numPositions > 2;
        assert numDiscs > 0;
        this.numPositions = numPositions;
        this.numDiscs = numDiscs;

        landscape = new Landscape(this);
    }
}
