import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame{

    private JButton roflanDovolenButton;
    private JButton roflanEbaloButton;
    private JPanel panel1;

    public MainWindow() {
        setContentPane(panel1);
        setSize(300, 300);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        roflanDovolenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_dialog dial = new m_dialog();
                dial.setRoflanText("roflanDovolen");
                dial.setModal(true);
                dial.setVisible(true);

            }
        });

        roflanEbaloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_dialog dial = new m_dialog();
                dial.setRoflanText("roflanEbalo");
                dial.setModal(true);
                dial.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new MainWindow();

    }

}
