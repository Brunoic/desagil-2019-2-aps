package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class View extends JPanel implements ActionListener {
    private final JComboBox<Gate> menu;
    private GateView view;

    public View(LinkedList<Gate> model) {
        menu = new JComboBox<>();
        for (Gate gate : model) {
            menu.addItem(gate);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(menu);
        addGateView(0);

        menu.addActionListener(this);
    }

    private void addGateView(int index) {
        Gate gate = menu.getItemAt(index);
        view = new GateView(gate);
        add(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        remove(view);
        int index = menu.getSelectedIndex();
        addGateView(index);

        // Mantenha esta linha, mas não precisa entendê-la.
        // É necessária para evitar bugs em alguns sistemas.
        ((JFrame) SwingUtilities.getRoot(this)).pack();
    }
}
