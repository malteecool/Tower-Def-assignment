package view;

import controller.HighscoreEntry;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LossDialog {
    private Dimension tableSize = new Dimension(200, 150);
    private Dimension lossDialogPrefDim = new Dimension(300,400);

    JDialog lossDialog;
    JPanel lossPanel;
    JTextArea lossDialogHeaderText;
    JTextArea lossDialogHighscoreHeader;
    JTable lossDialogHighscore;
    JButton replayButton;
    JButton exitButton;
    TableModel highSCoreTableModel;
    DefaultTableModel tableModel;

    LossDialog(){
        ViewHelper helper = new ViewHelper();

        lossDialog = new JDialog();
        lossPanel = new JPanel();
        lossDialogHeaderText = new JTextArea();
        lossDialogHighscoreHeader = new JTextArea();
        tableModel = new DefaultTableModel();
        lossDialogHighscore = new JTable(tableModel);
        replayButton = new JButton("Play Again");
        exitButton = new JButton("Exit Game");

        lossPanel.setLayout(new GridBagLayout());

        lossDialogHeaderText.setText("You Lost!");
        lossDialogHeaderText.setFont(helper.getFontH1());
        lossDialogHeaderText.setAlignmentX(Component.CENTER_ALIGNMENT);
        lossDialogHeaderText.setBackground(Color.green);
        lossDialogHeaderText.setEditable(false);

        lossDialogHighscoreHeader.setText("Highscore");
        lossDialogHighscoreHeader.setFont(helper.getFontH2());
        lossDialogHighscoreHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        lossDialogHighscoreHeader.setBackground(Color.cyan);
        lossDialogHighscoreHeader.setEditable(false);

        lossDialogHighscore.setPreferredSize(tableSize);
        lossDialogHighscore.setRowHeight(15);
        lossDialogHighscore.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        lossDialogHighscore.setFont(helper.getFontP());
        lossDialogHighscore.setDefaultEditor(Object.class, null);
        lossDialogHighscore.setRowSelectionAllowed(false);
        lossDialogHighscore.setColumnSelectionAllowed(false);
        tableModel.setDataVector(helper.getTempData(), helper.getColNames());
        tableModel.fireTableDataChanged();


        lossPanel.add(lossDialogHeaderText,
                helper.setGridBagConstraints(GridBagConstraints.PAGE_START,
                        0,1,0,0,1,
                        10,10,5));
        lossPanel.add(lossDialogHighscoreHeader,
                helper.setGridBagConstraints(GridBagConstraints.CENTER,
                        0,1,0,1,1,
                        5,5,5));
        lossPanel.add(lossDialogHighscore,
                helper.setGridBagConstraints(GridBagConstraints.CENTER,
                        0,1,0,2,1,
                        10,10,5));
        JPanel bottomButtonPanel = new JPanel(new BorderLayout());
        bottomButtonPanel.add(replayButton, BorderLayout.WEST);
        bottomButtonPanel.add(exitButton, BorderLayout.EAST);
        lossPanel.add(bottomButtonPanel,
                helper.setGridBagConstraints(GridBagConstraints.PAGE_END,
                        0,1,0,3,1,
                        5,10,5));
        lossDialog.add(lossPanel);
        lossDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        lossDialog.pack();
        lossDialog.setLocationRelativeTo(null);
        lossDialog.setVisible(false);

    }
    void setVisible(){
        lossDialog.setVisible(true);
    }
    void setExitButtonListener(ActionListener listener){
        exitButton.addActionListener(listener);
    }
    void setPlayAgainListener(ActionListener listener){
        replayButton.addActionListener(listener);
    }
    void setHighScoreList(ArrayList<HighscoreEntry> highScoreList){
        if (highScoreList.size() > 5) {
            System.err.println("Highscorelist too long, ignoring");
        }
        for (int i=0; i<highScoreList.size(); i++){
            //lossDialogHighscore.setValueAt(highScoreList.get(i).getPlayerName(), i,0);
            //lossDialogHighscore.setValueAt(highScoreList.get(i).getHighScore(), i,1);
        }
    }
    private class CustomTableModel extends AbstractTableModel{
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
        @Override
        public int getRowCount() {
            return 0;
        }

        @Override
        public int getColumnCount() {
            return 0;
        }

        @Override
        public Object getValueAt(int i, int i1) {
            return null;
        }
    }
}
