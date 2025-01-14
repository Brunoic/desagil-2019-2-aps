// FONTE DAS IMAGENS: https://en.wikipedia.org/wiki/Logic_gate (domínio público)

package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener {
    private static final int BORDER = 10;
    private static final int SWITCH_SIZE = 18;
    private static final int GATE_WIDTH = 90;
    private static final int GATE_HEIGHT = 60;

    private final Switch[] switches;
    private final Gate gate;
    private final JCheckBox[] inputBoxes;
    private final JCheckBox outputBox;

    public GateView(Gate gate) {
        super(BORDER + SWITCH_SIZE + GATE_WIDTH + SWITCH_SIZE + BORDER, GATE_HEIGHT);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        JLabel inputLabel = new JLabel("Input:");
        JLabel outputLabel = new JLabel("Output:");

        outputBox = new JCheckBox();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        int x, y, step;
        x = BORDER;
        y = -(SWITCH_SIZE / 2);
        step = (GATE_HEIGHT / (inputSize + 1));

        add(inputLabel);
        for (JCheckBox inputBox : inputBoxes) {
            y += step;
            add(inputBox, x, y, SWITCH_SIZE, SWITCH_SIZE);
            inputBox.addItemListener(this);

        }

        add(outputBox, BORDER + SWITCH_SIZE + GATE_WIDTH, (GATE_HEIGHT - SWITCH_SIZE) / 2, SWITCH_SIZE, SWITCH_SIZE);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        add(outputLabel);
        add(outputBox);
        outputBox.setEnabled(false);

        update();
    }
    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        boolean result = gate.read();

        outputBox.setSelected(result);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

}
