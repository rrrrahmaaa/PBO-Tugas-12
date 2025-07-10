import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KalkulatorLayout extends JFrame {
    private JTextField textField;
    private StringBuilder input = new StringBuilder();


    public KalkulatorLayout() {
        setTitle("Kalkulator Layout");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panel atas untuk text field
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Times new Roman", Font.PLAIN, 34));
        add(textField, BorderLayout.NORTH);

        //Panell bawah untuk tomhol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout((new GridLayout(3, 6, 5,  5)));

        String[] tombol = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", "*", "/", "=", "%", "Mod", "Exit"
        };

        for (String t : tombol) {
            JButton btn = new JButton(t);
            btn.setFont(new Font("Times New Roman", Font.BOLD, 15));
            panelTombol.add(btn);

            // Event handler keluar
            if (t.equals("Exit")) {
                btn.addActionListener(e -> System.exit(0));
            } else if (t.equals("=")) {
                btn.addActionListener(e -> {
                try {
                    String result = evaluate(input.toString());
                    textField.setText(result);
                    input.setLength(0);
                } catch (Exception ex) {
                textField.setText("Error");
                input.setLength(0);
            }
        });
            } else {
                btn.addActionListener(e -> {
                    input.append(t);
                    textField.setText(input.toString());
                });
            }
        }
        add(panelTombol, BorderLayout.CENTER);
    }

    // Fungsi evaluasi sederhana
    private String evaluate(String expression) {
        // Sangat sederhana: hanya dukung angka dan + - * /
        // Untuk benar-benar akurat, gunakan skrip engine (optional)
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object result = engine.eval(expression.replace("Mod", "%"));
            return result.toString();
        } catch (Exception e) {
            return "Error";
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KalkulatorLayout().setVisible(true));
    } 
}