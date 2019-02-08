package game;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Rodrigo Martínez
 */
public class RaffleGame {

    private List<Integer> numberList;
    private JFrame frame;
    private JTextField inputField;
    private JTextField outputField;
    private JButton getNumberButton;

    public void buildGui() {

//        frame = new JFrame(this.getClass().getSimpleName());
        frame = new JFrame(this.getClass().getSimpleName() + " (Modified)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box fieldBox = new Box(BoxLayout.Y_AXIS);
        inputField = new JTextField(6);
        outputField = new JTextField(6);
        outputField.setEditable(false);
        fieldBox.add(inputField);
        fieldBox.add(outputField);

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new GenerateButtonListener());
        buttonBox.add(generateButton);
        getNumberButton = new JButton("Get Number");
        getNumberButton.setEnabled(false);
        getNumberButton.addActionListener(new GetNumberButtonListener());
        buttonBox.add(getNumberButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(fieldBox);
        mainPanel.add(buttonBox);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(210, 150);
        frame.setVisible(true);
    }

    public void initGame(int numbersQuantity) {

        numberList = new ArrayList();
        for (int i = 1; i <= numbersQuantity; i++) {
            numberList.add(i);
        }
        getNumberButton.setEnabled(true);
        System.out.println(numberList);
    }

    private class GenerateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (inputField.getText().length() > 0 && inputField != null) {
                initGame(Integer.valueOf(inputField.getText()));
                outputField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "¡Debe ingresar un número para comenzar!");
            }
        }
    }

    private class GetNumberButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (numberList.size() > 0) {
                Random rand = new Random();
                int randomNumber = rand.nextInt(numberList.size());
                outputField.setText(String.valueOf(numberList.get(randomNumber)));
                System.out.println(numberList.get(randomNumber));
                numberList.remove(randomNumber);
            } else {
                JOptionPane.showMessageDialog(frame, "¡No quedan números!");
            }
        }
    }
}