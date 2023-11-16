package org.example;

import org.example.exceptions.FechaException;
import org.example.exceptions.FechaImposibleException;
import org.example.exceptions.FechaIncompletaException;
import org.example.exceptions.FechaIncorrectaException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ComponenteFechaClase extends JPanel implements ComponenteFecha {

    private JComboBox<Integer> diaComboBox;
    private JComboBox<Integer> mesComboBox;
    private JTextField yearTextField;
    public ComponenteFechaClase() {
        setLayout(new FlowLayout());

        Integer[] dias = new Integer[31];
        Integer[] meses = new Integer[12];

        for (int i = 1; i <= 31; i++) {
            dias[i - 1] = i;
            if (i <= 12){
                meses[i-1] = i;
            }
        }

        diaComboBox = new JComboBox<>(dias);
        mesComboBox = new JComboBox<>(meses);

        yearTextField = new JTextField(4);

        add(diaComboBox);
        add(mesComboBox);
        add(yearTextField);
    }

    @Override
    public LocalDate getDate() throws FechaException {
        String dia = diaComboBox.getSelectedItem().toString();
        String mes = mesComboBox.getSelectedItem().toString();
        String year = yearTextField.getText();

        if (dia == null || mes == null || year.isEmpty()){
            throw new FechaIncompletaException("Fecha incompleta");
        }

        int diaInt = Integer.parseInt(dia);
        int mesInt = Integer.parseInt(mes);
        int yearInt = Integer.parseInt(year);


        if (diaInt < 1 || mesInt <1 || mesInt > 12 || yearInt < 1){
            throw new FechaIncorrectaException("Fecha incorrecta");
        }

        if (diaInt > 28 && diaInt > obtenerDiasEnMes(mesInt, yearInt)){
            throw new FechaImposibleException("Fecha imposible");
        }

        return LocalDate.of(yearInt, mesInt, diaInt);
    }


    //get desencadena la comprobacion y set establece la fecha que se mostrara inicialmente
    @Override
    public void setDate(int dia, int mes, int year) {
        diaComboBox.setSelectedItem(dia);
        mesComboBox.setSelectedItem(mes);
        yearTextField.setText(String.valueOf(year));
    }


    private int obtenerDiasEnMes(int mes, int year){
        if (mes == 2){
            return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        }else {
            return 31;
        }
    }
}
