import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Modal {
    JFrame frame;

    JTextField N;
    JTextField bufferSize;
    JTextField outFile;

    JLabel largestPrime;
    JLabel numbersGen;
    JLabel timeElapsed;

    Modal() {
        this.constructFrame();
        this.constructInputsPanel();
        this.constructInfoPanel();

        this.frame.setVisible(true);
    }

    private void constructFrame() {
        JFrame frame = new JFrame();
        frame.setSize(400, 500);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        this.frame = frame;
    }

    private void constructInputsPanel() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridLayout layout = new GridLayout(4, 1, 10, 10);

        panel.setLayout(layout);

        JPanel N = this.constructInputPanel("N");
        JPanel bufferSize = this.constructInputPanel("Buffer Size");
        JPanel outFile = this.constructInputPanel("Output File");

        panel.add(N);
        panel.add(bufferSize);
        panel.add(outFile);

        JButton startButton = new JButton("Start Procedure");

        startButton.addActionListener(e -> {
            Integer num = this.N.getText().trim().isEmpty() ? 0 : Integer.parseInt(this.N.getText());
            Integer bs = this.bufferSize.getText().trim().isEmpty() ? 8 : Integer.parseInt(this.bufferSize.getText());
            String out = this.outFile.getText().trim().isEmpty() ? "out.txt" : this.outFile.getText();

            Runner runner = new Runner(num, bs, out, this.largestPrime, this.numbersGen, this.timeElapsed);

            runner.run();
        });

        panel.add(startButton);

        panel.setBackground(Color.LIGHT_GRAY);

        this.frame.getContentPane().add(BorderLayout.NORTH, panel);
    }

    private JPanel constructInputPanel(String label) {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(10);

        panel.setLayout(layout);

        JLabel inputLabel = new JLabel(label);

        JTextField input = new JTextField(20);

        panel.add(input);
        panel.add(inputLabel);

        switch (label) {
            case "N":
                this.N = input;
            case "Buffer Size":
                this.bufferSize = input;
            case "Output File":
                this.outFile = input;
        }

        return panel;
    }

    private void constructInfoPanel() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        layout.setVgap(10);

        panel.setLayout(layout);

        JPanel largestPrice = this.constructInfoPiecePanel("The largest prime number");
        JPanel numbersGen = this.constructInfoPiecePanel("# of prime numbers generated");
        JPanel timeElapsed = this.constructInfoPiecePanel("Time elapsed");

        panel.add(BorderLayout.NORTH, largestPrice);
        panel.add(BorderLayout.CENTER, numbersGen);
        panel.add(BorderLayout.SOUTH, timeElapsed);

        panel.setBackground(Color.LIGHT_GRAY);

        this.frame.getContentPane().add(BorderLayout.SOUTH, panel);
    }

    private JPanel constructInfoPiecePanel(String label) {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(20);

        panel.setLayout(layout);

        JLabel value = new JLabel("-");

        JLabel text = new JLabel(label);

        panel.add(text);
        panel.add(value);

        switch (label) {
            case "The largest prime number":
                this.largestPrime = value;
            case "# of prime numbers generated":
                this.numbersGen = value;
            case "Time elapsed":
                this.timeElapsed = value;
        }

        return panel;
    }
}
