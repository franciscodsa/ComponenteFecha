import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ComponenteFechaClase extends JPanel implements ComponenteFecha {

    private LocalDate localDate;
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
        String dia = (String) diaComboBox.getSelectedItem();
        String mes = (String) mesComboBox.getSelectedItem();
        String year = yearTextField.getText();

        if (dia == null || mes == null || year.isEmpty()){
            throw new FechaException("Fecha incompleta");
        }

        int diaInt = Integer.parseInt(dia);
        int mesInt = Integer.parseInt(mes);
        int yearInt = Integer.parseInt(year);


        if (diaInt < 1 || mesInt <1 || mesInt > 12 || yearInt < 1){
            throw new FechaException("Fecha incorrecta");
        }

        if (diaInt > 28 && diaInt > obtenerDiasEnMes(mesInt, yearInt)){
            throw new FechaException("Fecha imposible");
        }

        return null;
    }


    //get desencadena la comprobacion y set establece la fecha que se mostrara inicialmente
    @Override
    public void setDate(int dia, int mes, int year) {

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
