/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.view;

import com.github.krismorte.repost4insta.util.DataHelper;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author krisnamourtscf
 */
public class DefaultJDialog extends JDialog {

    protected DecimalFormat decimalFormat = new DecimalFormat("#.##");
    protected String datePattern = "dd/MM/yyyy";
    protected SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    public static Image logo;

    public void setup() {
        this.setModal(true);
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/share.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void dialogToMacOS(String title, String msg, int type) {
        JOptionPane jop = new JOptionPane(msg, type);
        JDialog dlog = jop.createDialog(null, title);
        dlog.setAlwaysOnTop(true);//make JDialog on top of other windows
        dlog.setVisible(true);
    }

    public void showValidationMessage(String message) {
        dialogToMacOS("Aviso de Validação", message, JOptionPane.WARNING_MESSAGE);
        //JOptionPane.showMessageDialog(null, message, "Aviso de Validação", JOptionPane.WARNING_MESSAGE);
    }

    public void showInformationMessage(String message) {
        dialogToMacOS("Informativo", message, JOptionPane.INFORMATION_MESSAGE);
        //JOptionPane.showMessageDialog(null, message, , JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(Exception ex) {
        String message = "";
        if (ex == null) {
            message = "Fatal";
        } else {
            message = ex.getMessage();
        }
        //JOptionPane.showMessageDialog(null, message, "Erro de Sistema", JOptionPane.ERROR_MESSAGE);
        dialogToMacOS("Erro de Sistema", message, JOptionPane.ERROR_MESSAGE);
    }

    public boolean verifyEmpytFields(String errorMsg, JTextComponent... txt) {
        boolean retorno = true;
        for (JTextComponent t : txt) {
            if (t.getText().equals("")) {
                retorno = false;
            }
        }
        if (!retorno) {
            showValidationMessage(errorMsg);
        }
        return retorno;
    }

    /* public boolean verifyDateField(Date data) {
        return verifyDateField(DataHelper.dateToLocalDate(data));
    }*/

 /*public boolean verifyDateField(LocalDate data) {
        LocalDate dataAtual = LocalDate.now();
        int anoInicial = dataAtual.getYear() - com.krismorte.clinica.Parametros.getAnoMinimo();
        int anoFinal = dataAtual.getYear() + com.krismorte.clinica.Parametros.getAnoMaximo();
        LocalDate dataInicial = LocalDate.of(anoInicial, Month.JANUARY, 1);
        LocalDate dataFinal = LocalDate.of(anoFinal, Month.JANUARY, 1);
        if (data.isBefore(dataInicial)) {
            throw new RuntimeException("Data anterior a " + anoInicial);
        }
        if (data.isAfter(dataFinal)) {
            throw new RuntimeException("Data posterior a " + anoFinal);
        }
        return true;
    }*/
    public File getDirectoryPath(String file) {
        javax.swing.JFileChooser arquivo = new javax.swing.JFileChooser();
        if (file.equals("")) {
            arquivo.setCurrentDirectory(null);
        } else {
            File f = new File(file);
            if (f.isDirectory()) {
                arquivo.setCurrentDirectory(f);
            } else {
                arquivo.setSelectedFile(f);
            }
        }
        arquivo.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        int resultadoArq = arquivo.showOpenDialog(arquivo);

        if (!(resultadoArq == javax.swing.JFileChooser.CANCEL_OPTION)) {
            File arquivoNome = arquivo.getSelectedFile();

            return arquivoNome;
        } else {
            return null;
        }
    }

    public void confNumberField(JTextField txt) {
        rightOrientation(txt);
        addSelectAllOnFocus(txt);
        txt.setText("0");
    }

    public void confDatePickerField(JXDatePicker datePicker) {
        datePicker.setFormats(dateFormatter);
    }

    public void rightOrientation(JTextField txt) {
        txt.setHorizontalAlignment(JTextField.RIGHT);
    }

    public void addEnterClickEvent(JTextField txt, JButton btn) {
        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn.doClick();
            }
        });
    }

    public void addSelectAllOnFocus(JTextField txt) {
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e); //To change body of generated methods, choose Tools | Templates.
                txt.selectAll();
            }
        });
    }

    public String formatDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return dateFormatter.format(Date.from(instant));
    }

    public String formatDecimal(Double number) {
        if (number == null) {
            return "0.0";
        } else {
            return decimalFormat.format(number).replace(",", ".");
        }
    }

    public void setDateField(JTextField txt, LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        txt.setText(dateFormatter.format(Date.from(instant)));
    }

    public void setDecimalField(JTextField txt, Double number) {
        if (number == null) {
            txt.setText("0.0");
        } else {
            txt.setText(decimalFormat.format(number).replace(",", "."));
        }
    }

    public Double getDoubleValue(JTextField txt) {
        if (txt.getText().equals("")) {
            return new Double(0);
        } else {
            return new Double(txt.getText());
        }
    }

    public void setRateValue(JTextField txt, Double number) {
        number = number * 100;
        txt.setText(decimalFormat.format(number).replace(",", "."));
    }

    public Double getRateValue(JTextField txt) {
        Double number = 0.0;
        if (!txt.getText().equals("")) {
            number = new Double(txt.getText());
            number = number / 100;
        }
        return number;
    }

    public boolean clear(Object container) {
        Component componentes[] = null;
        if (container instanceof JFrame) {
            componentes = ((JPanel) ((JFrame) container).getContentPane()).getComponents();
        } else if (container instanceof JDialog) {
            componentes = ((JPanel) ((JDialog) container).getContentPane()).getComponents();
        } else if (container instanceof JInternalFrame) {
            componentes = ((JPanel) ((JInternalFrame) container).getContentPane()).getComponents();
        } else if (container instanceof JPanel) {
            componentes = ((JPanel) container).getComponents();
        }
        if (componentes != null) {
            for (Component c : componentes) {
                if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                    ((JTextComponent) c).setText("");
                } else if (c instanceof JComboBox) {
                    ((JComboBox) c).setSelectedItem(null);
                } else if (c instanceof JList) {
                    ((JList) c).removeAll();
                } else if (c instanceof JCheckBox) {
                    ((JCheckBox) c).setSelected(false);
                } else if (c instanceof JRadioButton) {
                    if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                        ((JRadioButton) c).setSelected(true);
                    }
                } else if (c instanceof JPanel) {
                    clear((JPanel) c);
                } else if (c instanceof JScrollPane) {
                    clear((JScrollPane) c);
                } else if (c instanceof JScrollBar) {
                    clear((JScrollBar) c);
                }
            }
        }
        return true;
    }

    public boolean clear(Component... componentes) {
        if (componentes != null) {
            for (Component c : componentes) {
                if (c instanceof JTextField || c instanceof JTextArea || c instanceof JEditorPane) {
                    ((JTextComponent) c).setText("");
                } else if (c instanceof JComboBox) {
                    ((JComboBox) c).setSelectedItem(null);
                } else if (c instanceof JList) {
                    ((JList) c).removeAll();
                } else if (c instanceof JCheckBox) {
                    ((JCheckBox) c).setSelected(false);
                } else if (c instanceof JRadioButton) {
                    if (((JRadioButton) c).getActionCommand().equals("Limpa")) {
                        ((JRadioButton) c).setSelected(true);
                    }
                } else if (c instanceof JPanel) {
                    clear((JPanel) c);
                } else if (c instanceof JScrollPane) {
                    clear((JScrollPane) c);
                } else if (c instanceof JScrollBar) {
                    clear((JScrollBar) c);
                }
            }
        }
        return true;
    }

    public void showImage(JPanel panel, Image image) {
        Image imageResized = image.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_DEFAULT);
        panel.removeAll();
        JLabel label = new JLabel(new ImageIcon(imageResized));
        panel.setLayout(new GridLayout(1, 1));
        panel.add(label);
        //panelImage.add(label, BorderLayout.CENTER);
        panel.validate();
    }
}
