package br.pro.hashi.ensino.desagil.aps.model;

public class TripleInputAndGate extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nandFinal;

    public TripleInputAndGate () {
        super("TripleInputAnd", 3);

        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
        nandFinal = new NandGate();

        nand2.connect(0, nand1);
        nand2.connect(1, nand1);

        nand3.connect(0, nand2);

        nandFinal.connect(0, nand3);
        nandFinal.connect(1, nand3);


    }

    @Override
    public boolean read (int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandFinal.read();
    }

    @Override
    public void connect (int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nand1.connect(0,emitter);
                break;
            case 1:
                nand1.connect(1,emitter);
                break;
            case 2:
                nand3.connect(1,emitter);

        }
    }
}