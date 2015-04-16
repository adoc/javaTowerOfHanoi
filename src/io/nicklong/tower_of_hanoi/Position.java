package io.nicklong.tower_puzzle;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;

public class Position {
    private Puzzle puzzle; // Passing puzzle seems weird here. Possible refactor.
                            // Need to pass numDiscs at least, so possible passing the puzzle state isn't terrible.

    private ArrayList<Disc> discs = new ArrayList<>();

    private class MaxDiscSize extends Disc {
        @Override
        public int getSize() {
            return puzzle.getNumDiscs() + 1;
        }
    }

    private Disc getSmallestDisc() {
        Disc smallestDisc = new MaxDiscSize();
        for(Disc disc: discs) {
            if (disc.getSize() <= smallestDisc.getSize())
                smallestDisc = disc;
        }
        return smallestDisc;
    }

    public int getNumDiscs() {
        return discs.size();
    }

    public boolean hasDiscs() {
        return getNumDiscs() > 0;
    }

    public Disc topDisc() {
        assert hasDiscs();
        return discs.get(discs.size() - 1);
    }

    public boolean stackDisc(Disc disc) {
        if (!hasDiscs() || disc.getSize() < topDisc().getSize()) {
            discs.add(disc);
            return true;
        }
        return false;
    }

    public void removeDisc() {
        Disc disc = topDisc();
        discs.remove(disc);
    }

    public ArrayList<String> toList() {
        ArrayList<String> output = new ArrayList<>();
        for(int i = 0; i < getNumDiscs(); i++)
            output.add(discs.get(i).toString());
        return output;
    }

    public Position(Puzzle puzzle) {
        this.puzzle = puzzle;

        for(int i = this.puzzle.getNumDiscs() - 1; i >= 0; i--)
            discs.add(new Disc(i + 1));
    }

    public Position(Puzzle puzzle, boolean noDiscs) {
        this.puzzle = puzzle;

        if (!noDiscs)
            for(int i = this.puzzle.getNumDiscs() - 1; i >= 0; i--)
                discs.add(new Disc(i + 1));
    }

    public Position() {
        /*
        For polymorphed nested subclasses in Landscape class
         */
    }
}