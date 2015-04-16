package io.nicklong.tower_puzzle;

import java.util.ArrayList;

public class Landscape {
    private Puzzle puzzle; // Passing puzzle seems weird here. Possible refactor.

    private ArrayList<Position> positions = new ArrayList<>();

    private class MaxPosition extends Position {
        @Override
        public int getNumDiscs() {
            return puzzle.getNumDiscs() + 1;
        }

        @Override
        public Disc getSmallestDisc() {
            return new Landscape.MaxDiscSize();
        }
    }

    private class MinPosition extends Position {
        @Override
        public int getNumDiscs() {
            return -1;
        }

        public Disc getSmallestDisc() {
            return new Landscape.MaxDiscSize();
        }
    }

    private class MaxDiscSize extends Disc {
        @Override
        public int getSize() {
            return puzzle.getNumDiscs() + 1;
        }
    }

    private class MinDiscSize extends Disc {
        @Override
        public int getSize() {
            return -1;
        }
    }

    public int getPositionIndex(Position position) {
        return positions.indexOf(position);
    }

    public Position leftmostPosition() {
        // Probably never used...
        return positions.get(0);
    }

    public Position rightmostPosition() {
        return positions.get(positions.size() - 1);
    }

    public Position smallestRightmostPosition(Position excluding) {
        /*
        Returns the position that has the fewest discs.
         */
        Position smallest = new MaxPosition();
        for(Position position: positions) {
            // equals will ensure we reach the rightmost smallest position.
            // This should be right.
            if (position != excluding &&
                    position.getNumDiscs() <= smallest.getNumDiscs()) {
                smallest = position;
            }
        }
        return smallest;
    }

    public Position smallestRightOfSource(Position source) {
        Position smallest = new MaxPosition();

        for(int i = getPositionIndex(source) + 1; i < positions.size(); i++) {
            Position position = positions.get(i);
            if (position.getNumDiscs() <= position.getNumDiscs())
                smallest = position;
        }
        return smallest;
    }

    public Position largestPosition() {
        // Probably don't need it.
        /*
        Returns the position that has the most discs.
         */
        Position largest = new MinPosition();
        for(Position position: positions) {
            if (position.getNumDiscs() > largest.getNumDiscs()) {
                largest = position;
            }
        }
        return largest;
    }

    public Position largestPositionSmallestDisc() {
        Position largestPosition = new MinPosition();
        for(Position position: positions) {
                if (position.getNumDiscs() >= largestPosition.getNumDiscs() &&
                        position.getSmallestDisc().getSize() < largestPosition.getSmallestDisc().getSize())
                    largestPosition = position;
        }
        return largestPosition; //smallestDisc
    }

    public Position largestLeftmostPosition() {
        /*
        Returns the position that has the most discs.
         */
        Position largest = new MinPosition();

        for(int i = positions.size() - 1; i>=0; i--) {
            Position position = positions.get(i);
            if (position.getNumDiscs() >= largest.getNumDiscs()) {
                largest = position;
            }
        }
        return largest;
    }

    public boolean makeMove(Position source, Position target) {
        if (source.hasDiscs())
            if (target.stackDisc(source.topDisc())) {
                source.removeDisc();
                return true;
            }
        return false;
    }

    public boolean isDone() {
        return rightmostPosition().getNumDiscs() == puzzle.getNumDiscs();
    }

    public ArrayList<ArrayList> toList() {
        ArrayList<ArrayList> output = new ArrayList<>();
        for(Position position: positions)
            output.add(position.toList());
        return output;
    }

    public Landscape(Puzzle puzzle) {
        /*
        Build the game landscape.
         */
        this.puzzle = puzzle;

        positions.add(new Position(puzzle, false));
        for (int i = 1; i < puzzle.getNumPositions(); i++)
            positions.add(new Position(puzzle, true));
    }
}