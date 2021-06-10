import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameBlob extends JPanel implements KeyListener {
    Blob playerBlob;
    ArrayList<Blob> evilBlobs;
    int delay;
    int red;
    int green;
    int blue;
    Color theColor;
    boolean gameOver;
    boolean win;
    private BufferedImage YARO;

    public GameBlob() {
        gameOver = false;
        delay = 50;
        evilBlobs = new ArrayList<Blob>();
        this.red = 0;
        this.green = 0;
        this.blue = 255;
        this.theColor = new Color(red, green, blue);
        playerBlob = new Blob(10, theColor, 400, 400, 0, 0);
        for (int i = 0; i < 20; i++) {
            createEvilBlob();
        }
        try {
            YARO = ImageIO.read(new File("YARO.jpg"));

        }
        catch (IOException ex) {
            // handle exception...
            System.out.println("File not found");
        }
        // create playerBlob

    }


    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if ((c == 'a') || (c == 'A')) {
            playerBlob.moveLeft();
        }
        if ((c == 'd') || (c == 'D')) {
            playerBlob.moveRight();
        }
        if ((c == 'w') || (c == 'W')) {
            playerBlob.moveUp();
        }
        if ((c == 's') || (c == 'S')) {
            playerBlob.moveDown();
        }
    }


    public void checkForCollision() {

        if ((playerBlob.getX() < -100) || (playerBlob.getX() > 900)
            || (playerBlob.getY() < -100) || (playerBlob.getY() > 900)) {
            gameOver = true;
            win = false;
        }
        for (int i = 0; i < evilBlobs.size(); i++) {
            if ((evilBlobs.get(i).getX() > 900) || (evilBlobs.get(i)
                .getX() < -100) || (evilBlobs.get(i).getY() > 900) || (evilBlobs
                    .get(i).getY() < -100)) {
                evilBlobs.remove(i);
                createEvilBlob();
            }
        }

        for (int i = 0; i < evilBlobs.size(); i++) {
            if ((distance(playerBlob.getX(), playerBlob.getY(), evilBlobs.get(i)
                .getX(), evilBlobs.get(i).getY())) <= playerBlob.getRadius()) {
                if (playerBlob.getRadius() > evilBlobs.get(i).getRadius()) {
                    evilBlobs.remove(i);
                    createEvilBlob();
                    playerBlob.eat();
                    if (this.red + 20 < 255) {
                        this.red += 20;
                    }
                    this.theColor = new Color(this.red, this.green, this.blue);
                    playerBlob = new Blob(playerBlob.getRadius(), theColor,
                        playerBlob.getX(), playerBlob.getY(), playerBlob
                            .getDeltaX(), playerBlob.getDeltaY());

                }
                else if (playerBlob.getRadius() < evilBlobs.get(i)
                    .getRadius()) {
                    gameOver = true;
                    win = false;
                }
            }
        }
        if (playerBlob.getRadius() >= 100) {
            String letters = "Congratulations! You won!";
            System.out.println(letters);

            gameOver = true;
            win = true;
        }
    }


    public void createEvilBlob() {
        double random = Math.random();
        int xValue = 0;
        int yValue = 0;
        int xDelta = 0;
        int yDelta = 0;
        if ((random >= 0) && (random < 0.25)) {
            xValue = 0;
            yValue = (int)(Math.random() * 801) - 100;
        }
        else if ((random >= 0.25) && (random < 0.50)) {
            xValue = (int)(Math.random() * 801) - 100;
            yValue = 0;
        }
        else if ((random >= 0.50) && (random < 0.75)) {
            xValue = 900;
            yValue = (int)(Math.random() * 801) - 100;
        }
        else if (random >= 0.75) {
            xValue = (int)(Math.random() * 801) - 100;
            yValue = 900;
        }

        xDelta = (int)(Math.random() * 41 - 20);
        yDelta = (int)(Math.random() * 41 - 20);
        Blob evilBlob = new Blob((int)(Math.random() * playerBlob.getRadius()
            + 5), Color.RED, xValue, yValue, xDelta, yDelta);
        evilBlobs.add(evilBlob);

    }


    public static int distance(int x1, int y1, int x2, int y2) {
        int distance = 0;
        distance = (int)(Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2),
            0.5));
        return distance;
    }


    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 800, 800);
        playerBlob.draw(g);
        for (int i = 0; i < evilBlobs.size(); i++) {
            evilBlobs.get(i).draw(g);
        }
        if (gameOver == true) {

            g.setFont(new Font("Courier New", Font.BOLD, 30));
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, 390, 270);
            g.setColor(new Color(123, 175, 222));
            g.fillRect(0, 27, 390, 36);
            g.fillRect(0, 222, 390, 36);
            g.setColor(new Color(123, 175, 222));
            int[] x1 = { 135, 195, 252 };
            int[] y1 = { 177, 75, 177 };
            g.fillPolygon(x1, y1, 3);
            g.setColor(new Color(255, 255, 255));
            int[] x2 = { 150, 195, 237 };
            int[] y2 = { 165, 96, 165 };
            g.fillPolygon(x2, y2, 3);
            g.setColor(new Color(123, 175, 222));
            int[] x3 = { 135, 195, 252 };
            int[] y3 = { 108, 210, 111 };
            g.fillPolygon(x3, y3, 3);
            g.setColor(new Color(255, 255, 255));
            int[] x4 = { 150, 195, 237 };
            int[] y4 = { 117, 192, 120 };
            g.fillPolygon(x4, y4, 3);
            g.setColor(new Color(123, 175, 222));
            int[] x5 = { 159, 168, 183, 159 };
            int[] y5 = { 135, 117, 114, 150 };
            g.fillPolygon(x5, y5, 4);
            g.setColor(new Color(123, 175, 222));
            int[] x6 = { 210, 219, 231, 225 };
            int[] y6 = { 120, 117, 135, 141 };
            g.fillPolygon(x6, y6, 4);
            g.setColor(new Color(123, 175, 222));
            int[] x7 = { 177, 213, 207, 183 };
            int[] y7 = { 168, 168, 177, 177 };
            g.fillPolygon(x7, y7, 4);

            g.setColor(Color.BLACK);
            if (win == true) {
                g.drawString("Congratulations! You won!", 120, 300);
            }
            else if (win == false) {
                g.drawString("You could use more practice. You lost.", 120,
                    300);
            }
            g.drawImage(YARO, 200, 350, null);

        }

    }


    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }


    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }


    public void run() {
        while (!gameOver) {
            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e) {
            }

            playerBlob.move();
            for (int i = 0; i < evilBlobs.size(); i++) {
                evilBlobs.get(i).move();
            }

            // check for collision
            checkForCollision();

            paintImmediately(0, 0, 1000, 1000);

        }
        System.out.println("GAME OVER");
        System.out.println("Final Blob Size - " + playerBlob.getRadius());
    }


    public static void main(String[] arg) {
        JFrame runner = new JFrame("Game Title");
        runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runner.setLocationRelativeTo(null);
        runner.setSize(800, 800);
        runner.setLayout(null);
        runner.setLocation(0, 0);

        GameBlob theGame = new GameBlob();
        theGame.setSize(800, 800);
        theGame.setLocation(0, 0);
        runner.getContentPane().add(theGame);

        runner.setVisible(true);

        runner.addKeyListener(theGame);
        theGame.run();
    }
}
