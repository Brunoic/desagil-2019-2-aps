package br.pro.hashi.ensino.desagil.aps.model;

public abstract class Gate implements SignalEmitter, SignalReceiver {
    private final int inputSize;
    private final String name;

    protected Gate(String name,int inputSize) {
        this.name = name;
        this.inputSize = inputSize;
    }

    public String toString() {
        return name;
    }
    public int getInputSize() {
        return inputSize;
    }
}
