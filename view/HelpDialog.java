package view;

import javax.swing.*;
import java.awt.*;

public class HelpDialog {
    private Dimension textBoxPrefDim = new Dimension(400, 600);
    private Dimension helpDialogPrefDim = new Dimension(450, 700);
    private Font h1 = new Font("Arial", Font.BOLD, 20);
    private Font p = new Font("Arial", Font.PLAIN, 12);

    private JPanel helpPanel;
    private JDialog helpDialog;
    private JTextArea helpHeaderTeatArea;
    private JTextArea helpTextArea;
    private JButton closeHelpButton;

    HelpDialog(){
        ViewHelper helper = new ViewHelper();
        helpDialog = new JDialog();
        helpPanel = new JPanel();
        helpHeaderTeatArea = new JTextArea();
        helpTextArea = new JTextArea();
        helpPanel.setLayout(new GridBagLayout());

        helpHeaderTeatArea.setText("Help / How to play");
        helpHeaderTeatArea.setFont(helper.getFontH1());
        helpHeaderTeatArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpHeaderTeatArea.setBackground(Color.green);
        helpHeaderTeatArea.setEditable(false);

        helpTextArea.setText("N/A");
        helpTextArea.setPreferredSize(textBoxPrefDim);
        helpTextArea.setFont(helper.getFontP());
        helpTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpTextArea.setEditable(false);

        closeHelpButton = new JButton("Close");
        closeHelpButton.addActionListener(e -> helpDialog.setVisible(false));

        helpPanel.add(helpHeaderTeatArea,
                helper.setGridBagConstraints(GridBagConstraints.PAGE_START,
                        0, 1, 0,0,
                        1,10,0,5));
        helpPanel.add(helpTextArea, helper.setGridBagConstraints(GridBagConstraints.HORIZONTAL,
                0,1,0,1,1,5,5,5));
        helpPanel.add(closeHelpButton, helper.setGridBagConstraints(GridBagConstraints.PAGE_END,
                0,1,0,2,1,10,10,5));

        helpDialog.add(helpPanel);
        helpDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        helpDialog.pack();
        helpDialog.setLocationRelativeTo(null);
        helpDialog.setVisible(false);
    }
    void setVisible(){
        helpDialog.setVisible(true);
    }
}
