package game.levels;

import java.util.ArrayList;
import java.util.List;

import game.GameFlow;
import game.Velocity;
import game.interfaces.LevelInformation;
import game.interfaces.Sprite;
import game.sprites.Background;
import game.sprites.FilledCircle;
import game.sprites.collidables.Block;
import geometry.Point;
import geometry.Line;
import java.awt.Color;
/**
 * class for a level in the game with wide paddle and 10 balls.
 * @author ozamoyal
 */

public class WideEasyLevel implements LevelInformation {
    private static final int NUMOFBALLS = 10;
    private int numberOfBlocks;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 600;
    private static final String LEVEL_NAME = "Wide Easy";
    private static final int BLOCK_WIDTH = 52;
    private static final int BLOCK_HEIGHT = 20;
/**
 * constructor for the level.
 */
    public WideEasyLevel() {
        numberOfBlocks = 0;
    }

    @Override
    public int numberOfBalls() {
        return NUMOFBALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<Velocity>();
        for (int i = 1; i <= 5; i++) {
            Velocity vel1 = new Velocity(6 - i, -i);
            Velocity vel2 = new Velocity(i - 6, i);
            vList.add(vel1);
            vList.add(vel2);
        }
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {

        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background();
        Block bgBlock = new Block(new Point(0, 0), Color.WHITE, GameFlow.GUI_HEIGHT, GameFlow.GUI_WIDTH);
        background.addToBackground(bgBlock);
        Point cPoint = new Point(130, 150);
        for (int i = 20; i <= 780; i += 10) {
            Line sunLight = new Line(cPoint, new Point(i, 300));
            sunLight.setColor(new Color(255, 204, 0));
            background.addToBackground(sunLight);
        }
        FilledCircle light = new FilledCircle(cPoint, 60);
        light.setColor(new Color(255, 251, 208));
        background.addToBackground(light);
        light = new FilledCircle(cPoint, 50);
        light.setColor(new Color(255, 204, 0));
        background.addToBackground(light);
        light = new FilledCircle(cPoint, 40);
        light.setColor(Color.YELLOW);
        background.addToBackground(light);

        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Block block = new Block(new Point(10, 300), Color.RED, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(62, 300), Color.RED, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(114, 300), Color.ORANGE, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(166, 300), Color.ORANGE, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(218, 300), Color.YELLOW, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(270, 300), Color.YELLOW, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(322, 300), Color.GREEN, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(374, 300), Color.GREEN, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(426, 300), Color.GREEN, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(478, 300), Color.BLUE, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(530, 300), Color.BLUE, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(582, 300), Color.PINK, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(634, 300), Color.PINK, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(686, 300), Color.CYAN, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);
        block = new Block(new Point(738, 300), Color.CYAN, BLOCK_HEIGHT, BLOCK_WIDTH);
        blocks.add(block);

        this.numberOfBlocks = 15;

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }

}
