package view;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
public class AboutDialog {
    private Dimension textPrefDim = new Dimension(500, 200);
    private Dimension aboutBoxPrefDim = new Dimension(550, 300);
    private JDialog aboutDialog;
    private JPanel aboutPanel;
    private JTextPane aboutHeaderPane;
    private JTextPane aboutInfoPane;


    /**
     * Builds the About-popup dialog.
     * Text is added from the main gui class later.
     * Dialog is hidden until shown
     * @return
     */
    AboutDialog(){
        //SimpleAttributeSet center = new SimpleAttributeSet();
        //StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

        ViewHelper helper = new ViewHelper();
        aboutDialog = new JDialog();
        aboutPanel = new JPanel();
        aboutPanel.setLayout(new GridBagLayout());
        aboutHeaderPane = new JTextPane();
        aboutInfoPane = new JTextPane();

        aboutHeaderPane.setText("ABOUT");
        aboutHeaderPane.setFont(helper.getFontH1());
        aboutHeaderPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutHeaderPane.setBackground(Color.green);
        aboutHeaderPane.setEditable(false);
        //StyledDocument headDoc = aboutHeaderPane.getStyledDocument();
        //headDoc.setParagraphAttributes(0, headDoc.getLength(), center, false);
        GridBagConstraints aboutHeaderPaneConstraints = helper.setGridBagConstraints(
                GridBagConstraints.PAGE_START, 0, 0,0,0,1,
                5,0,0
        );

        aboutInfoPane.setText("N/A");
        aboutInfoPane.setBackground(Color.red);
        aboutInfoPane.setPreferredSize(textPrefDim);
        aboutInfoPane.setFont(helper.getFontP());
        aboutInfoPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutInfoPane.setEditable(false);
        //aboutInfoPane.setLineWrap(true);


        //StyledDocument infoDoc = aboutInfoPane.getStyledDocument();
        //infoDoc.setParagraphAttributes(0, infoDoc.getLength(), center, false);
        GridBagConstraints aboutInfoPaneConstraints = helper.setGridBagConstraints(
                GridBagConstraints.NONE, 1,0,0,1,1,
                10,5, 0
        );

        JButton closeAboutButton = new JButton("Close");
        closeAboutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagConstraints closeAboutButtonConstraints = helper.setGridBagConstraints(
                GridBagConstraints.PAGE_END, 0,0,0,2,1,
                10,10,0
        );
        closeAboutButton.addActionListener(e -> aboutDialog.setVisible(false));

        aboutPanel.add(aboutHeaderPane, aboutHeaderPaneConstraints);
        aboutPanel.add(aboutInfoPane, aboutInfoPaneConstraints);
        aboutPanel.add(closeAboutButton, closeAboutButtonConstraints);
        aboutPanel.setPreferredSize(aboutBoxPrefDim);
        aboutDialog.add(aboutPanel);
        aboutDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        //aboutDialog.setPreferredSize(aboutBoxPrefDim);

        //aboutDialog.setPreferredSize(aboutBoxPrefDim);
        aboutDialog.pack();
        aboutDialog.setLocationRelativeTo(null);
        aboutDialog.setVisible(false);

    }

    /**
     * Method used to set the text in the About-popup
     * @param text The text to be shown in the about-popup
     */
    void setAboutText(String text){
        aboutInfoPane.setText(text);
    }
    void setVisible(){
        aboutDialog.setVisible(true);
    }


}
