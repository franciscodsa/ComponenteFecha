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
        return null;
    }

    @Override
    public void setDate(int dia, int mes, int year) {

    }
}
