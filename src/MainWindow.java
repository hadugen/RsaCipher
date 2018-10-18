import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame{

    private JPanel panel1;
    private JTextArea textArea_text;
    private JButton encryptButton;
    private JTextArea textArea_encryptedText;
    private JTextArea textArea_encryptedText2;
    private JTextArea textArea_text2;
    private JButton decryptButton;

    RsaEncrypter rsaEncrypter = RsaEncrypter.getInstance();

    public MainWindow() {
        setContentPane(panel1);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea_encryptedText.setText(
                        rsaEncrypter.encrypt(textArea_text.getText()
                        ));
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea_text2.setText(
                        rsaEncrypter.decrypt(textArea_encryptedText2.getText()
                        ));
            }
        });
    }

    public static void main(String[] args) {
        new MainWindow();

    }

}
