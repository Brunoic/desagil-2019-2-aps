// FONTE DAS IMAGENS: https://en.wikipedia.org/wiki/Logic_gate (domínio público)

package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GateView extends FixedPanel implements ItemListener {
    private final Gate gate;
    private final Switch[] switches;
    private final JCheckBox[] in;
    private final JCheckBox out;


    public GateView(Gate gate) {
        super(215, 215);

        this.gate = gate;

        JLabel input = new JLabel("Input");
        JLabel output = new JLabel("Output");

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        in = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            in[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        out = new JCheckBox();

        for (JCheckBox i : in) {
            i.addItemListener(this);
        }

        out.setEnabled(false);

        update();

        add(input, 5, 5, 55, 15);
        for (int i = 0; i < inputSize; i++) {
            add(in[i], 5, 20 + i * 50, 55, 15);
        }
        add(output, 5, 155, 55, 15);
        add(out, 5, 190, 55, 15);

    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (in[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        boolean resultado = gate.read();
        out.setSelected(resultado);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        update();
    }
}