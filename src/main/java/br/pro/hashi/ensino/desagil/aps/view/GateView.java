package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate display;
    private final Light rgb;
    private final JCheckBox[] checkBox;

    private final int r = 15;
    private final int X = 225+r;
    private final int Y = 95+r;
    private final Image image;
    private Color color;

    public GateView(Gate display) {
        super(300, 215);

        this.display = display;

        int inputSize = display.getInputSize();

        rgb = new Light();
        switches = new Switch[inputSize];
        checkBox = new JCheckBox[inputSize];

        rgb.connect(0, display);
        rgb.setR(255);
        //é necessário setar apenas o R, pois o default é sempre 0, logo, não precisamos mudar G e B

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            checkBox[i] = new JCheckBox();

            display.connect(i, switches[i]);
        }

        String name = display.toString().toLowerCase() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);


        int positionY = 65;
        for (JCheckBox inputBox : checkBox) {
            if (inputSize ==1){
                add(inputBox,25,85,45,45);}
            else {
                add(inputBox, 30,positionY,45,45);
                positionY +=65;
            }
        }



        for (JCheckBox inputBox : checkBox) {
            inputBox.addItemListener(this);
        }

        addMouseListener(this);
        update();
    }

    private void update() {

        for (int i = 0; i < display.getInputSize(); i++) {
            if (checkBox[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }


        int r = rgb.getR();
        int g = rgb.getG();
        int b = rgb.getB();
        color = new Color(r,g,b);
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 20, 45, 250, 145, this);


        g.setColor(color);
        g.fillOval(X-r, Y-r, 3*r, 3*r);


        getToolkit().sync();
    }
    @Override
    public void itemStateChanged(ItemEvent event) {

        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x0 = e.getX();
        int y0 = e.getY();
        Color temp;
        double pointC = Math.pow((x0-X),2) + Math.pow((y0-Y),2);
        if (display.read() ) {
            if (pointC <r*r) {
                temp= JColorChooser.showDialog(this, null, color);
                try {
                    rgb.setG(temp.getGreen());
                    rgb.setB(temp.getBlue());
                    rgb.setR(temp.getRed());
                    repaint();
                    update();
                } catch (Exception exception){
                    System.err.println("Erro de RGB.");
                }



            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
