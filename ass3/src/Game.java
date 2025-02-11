import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Game class holds the game, initializes it and runs it.
 * @author ozamoyal
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    static final int GUI_WIDTH = 800;
    static final int GUI_HEIGHT = 600;
    static final int BORDER_SIZE = 20;
    static final Point PADDLE_START_POINT = new Point((GUI_WIDTH - Paddle.PADDLE_WIDTH) / 2,
            (GUI_HEIGHT - BORDER_SIZE - Paddle.PADDLE_HEIGHT));
    static final int BLOCK_WIDTH = 50;
    static final int BLOCK_HEIGHT = 20;
    static final Color BGCOLOR = new Color(0, 0, 128);

    /**
     * constructor for a new Game Object.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

    }

    /**
     * add a Collidable object to the game.
     * @param c - a Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * add a Sprite object to the game.
     * @param s - a Sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * add walls,blocks balls and a paddle to the game.
     * create a gui object and add all the necessary objects to play a game
     */
    public void initialize() {
        //gui object initialize
        this.gui = new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT);
        //add walls to game
        Block leftWall = new Block(new Point(0, 0), Color.DARK_GRAY, GUI_HEIGHT, BORDER_SIZE);
        Block topWall = new Block(new Point(0, 0), Color.DARK_GRAY, BORDER_SIZE, GUI_WIDTH);
        Block rightWall = new Block(new Point(GUI_WIDTH - BORDER_SIZE, 0), Color.DARK_GRAY, GUI_HEIGHT, BORDER_SIZE);
        Block bottomWall = new Block(new Point(0, GUI_HEIGHT - BORDER_SIZE), Color.DARK_GRAY, BORDER_SIZE, GUI_WIDTH);
        this.addCollidable(topWall);
        this.addCollidable(bottomWall);
        this.addCollidable(rightWall);
        this.addCollidable(leftWall);
        this.addSprite(topWall);
        this.addSprite(bottomWall);
        this.addSprite(leftWall);
        this.addSprite(rightWall);
        //add blocks to game
        for (int i = 230; i < (GUI_WIDTH - BORDER_SIZE); i += 50) {
            Block block = new Block(new Point(i, 200), Color.LIGHT_GRAY, BLOCK_HEIGHT, BLOCK_WIDTH);
            this.addCollidable(block);
            this.addSprite(block);
        }
        for (int i = 280; i < (GUI_WIDTH - BORDER_SIZE); i += 50) {
            Block block = new Block(new Point(i, 220), Color.YELLOW, BLOCK_HEIGHT, BLOCK_WIDTH);
            this.addCollidable(block);
            this.addSprite(block);
        }
        for (int i = 330; i < (GUI_WIDTH - BORDER_SIZE); i += 50) {
            Block block = new Block(new Point(i, 240), Color.RED, BLOCK_HEIGHT, BLOCK_WIDTH);
            this.addCollidable(block);
            this.addSprite(block);
        }
        for (int i = 380; i < (GUI_WIDTH - BORDER_SIZE); i += 50) {
            Block block = new Block(new Point(i, 260), Color.BLUE, BLOCK_HEIGHT, BLOCK_WIDTH);
            block.addToGame(this);
        }
        for (int i = 430; i < (GUI_WIDTH - BORDER_SIZE); i += 50) {
            Block block = new Block(new Point(i, 280), Color.PINK, BLOCK_HEIGHT, BLOCK_WIDTH);
            block.addToGame(this);
        }
        for (int i = 480; i < (GUI_WIDTH - BORDER_SIZE); i += 50) {
            Block block = new Block(new Point(i, 300), Color.GREEN, BLOCK_HEIGHT, BLOCK_WIDTH);
            block.addToGame(this);
        }
        //add balls to game
        Ball ball = new Ball(new Point(500, 400), 10, Color.LIGHT_GRAY, this.environment);
        ball.setVelocity(4, 4);
        ball.addToGame(this);
        Ball ball2 = new Ball(new Point(400, 400), 10, Color.LIGHT_GRAY, this.environment);
        ball2.setVelocity(4, 4);
        ball2.addToGame(this);
        //add paddle to game
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(), PADDLE_START_POINT);
        paddle.addToGame(this);

    }

    /**
     * run the game and calculate the time needed to wait for each frame.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        //fps calculation
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {

            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            //background color
            d.setColor(BGCOLOR);
            d.fillRectangle(0, 0, GUI_WIDTH, GUI_HEIGHT);
            //draw objects.
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }
}