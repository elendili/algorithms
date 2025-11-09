package leetcode.medium;

import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://leetcode.com/problems/design-snake-game/description/?envType=study-plan-v2&envId=premium-algo-100
Design a Snake game that is played on a device with screen size height x width. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.

You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.

Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.

When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).

Implement the SnakeGame class:

SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width and the positions of the food.
int move(String direction) Returns the score of the game after applying one direction move by the snake. If the game is over, return -1.
 */
public class SnakeGameDesign {

    class SnakeGame {
        record Position(int row, int col) {
        }

        final LinkedHashSet<Position> body;
        final int width;
        final int height;
        final int[][] food;
        int foodIndex = 0;

        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            Position first = new Position(0, 0);
            body = new LinkedHashSet<>();
            body.addFirst(first);
            this.food = food;
        }

        public int move(String direction) {
            Position head = body.getLast();
            Position p = switch (direction) {
                case "U" -> new Position(head.row - 1, head.col);
                case "D" -> new Position(head.row + 1, head.col);
                case "L" -> new Position(head.row, head.col - 1);
                case "R" -> new Position(head.row, head.col + 1);
                default -> head;
            };
            if (p.row < 0 || p.row >= height || p.col < 0 || p.col >= width) {
                return -1;
            }
            // remove tail if no food found
            if (foodIndex < food.length && p.row == food[foodIndex][0] && p.col == food[foodIndex][1]) {
                // found food
                foodIndex++;
            } else {
                body.removeFirst();
            }
            // check collision with own body
            if (body.contains(p)) {
                return -1;
            }
            body.addLast(p);
            return body.size() - 1;
        }
    }

    @org.junit.jupiter.api.Test
    public void test() {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
        String[] moves = new String[]{"R", "D", "R", "U", "L", "U"};
        int[] expected = new int[]{0, 0, 1, 1, 2, -1};
        for (int i = 0; i < moves.length; i++) {
            int actual = snakeGame.move(moves[i]);
            assertEquals(expected[i], actual, i + ". Move " + moves[i]);
        }
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        // [[2,2,[[0,1]]],["R"],["D"]]
        SnakeGame snakeGame = new SnakeGame(2, 2, new int[][]{{0, 1}});
        String[] moves = new String[]{"R", "D"};
        int[] expected = new int[]{1, 1};
        for (int i = 0; i < moves.length; i++) {
            int actual = snakeGame.move(moves[i]);
            assertEquals(expected[i], actual, i + ". Move " + moves[i]);
        }
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        SnakeGame snakeGame = new SnakeGame(3, 3, new int[][]{
                {2, 0}, {0, 0}, {0, 2}, {2, 2}
        });
        String[] moves = new String[]{"D", "D", "R", "U", "U", "L", "D", "R", "R", "U", "L", "D"};
        int[] expected = new int[]{0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3};
        for (int i = 0; i < moves.length; i++) {
            int actual = snakeGame.move(moves[i]);
            assertEquals(expected[i], actual, i + ". Move " + moves[i]);
        }
    }
}