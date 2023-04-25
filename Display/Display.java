package Display;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener{

    private Dimension size = new Dimension(1000,600);

    public Display(){
        this.setting();
        this.getContentPane().add(new Game());
    }


    private void setting() {
        this.setTitle("Dino game");
        this.setSize(size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(280,100);
        this.setVisible(true);
    }

    private void removeContent() {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    public void endGame(long point) {
        removeContent();
        this.getContentPane().add(new Menu(point,this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("restart")) {
            removeContent();
            Game game = new Game();
            this.getContentPane().add(game);
            game.requestFocus();
        }
    }


    public static void main(String[] args)
    {
        Display display = new Display();
    }
}
