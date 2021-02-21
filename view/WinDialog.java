package view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener
;

public class WinDialog {

    private Dimension tableSize = new Dimension(200, 150);
    private Dimension winDialogPrefDim = new Dimension(300,400);

    JDialog winDialog;
    JPanel winPanel;
    JTextArea winDialogHeaderText;
    JTextArea winDialogHighscoreHeader;
    JTable winDialogHighscore;
    JButton replayButton;
    JButton exitButton;
    JButton nextLevelButton;

    /**
     *
     */
    WinDialog(){
            ViewHelper helper = new ViewHelper();

            winDialog = new JDialog();
            winPanel = new JPanel();
            winDialogHeaderText = new JTextArea();
            winDialogHighscoreHeader = new JTextArea();
            winDialogHighscore = new JTable(new view.WinDialog.CustomTableModel());
            replayButton = new JButton("Play Again");
            exitButton = new JButton("Exit Game");
            nextLevelButton = new JButton("Next Level");

            winPanel.setLayout(new GridBagLayout());

            winDialogHeaderText.setText("You Win!");
            winDialogHeaderText.setFont(helper.getFontH1());
            winDialogHeaderText.setAlignmentX(Component.CENTER_ALIGNMENT);
            winDialogHeaderText.setBackground(Color.green);
            winDialogHeaderText.setEditable(false);

            winDialogHighscoreHeader.setText("Highscore");
            winDialogHighscoreHeader.setFont(helper.getFontH2());
            winDialogHighscoreHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
            winDialogHighscoreHeader.setBackground(Color.cyan);
            winDialogHighscoreHeader.setEditable(false);

            winDialogHighscore.setPreferredSize(tableSize);
            winDialogHighscore.setRowHeight(15);
            winDialogHighscore.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            winDialogHighscore.setFont(helper.getFontP());

            winPanel.add(winDialogHeaderText,
                    helper.setGridBagConstraints(GridBagConstraints.PAGE_START,
                            0,1,0,0,1,
                            10,10,5));
            winPanel.add(winDialogHighscoreHeader,
                    helper.setGridBagConstraints(GridBagConstraints.CENTER,
                            0,1,0,1,1,
                            5,5,5));
            winPanel.add(winDialogHighscore,
                    helper.setGridBagConstraints(GridBagConstraints.CENTER,
                            0,1,0,2,1,
                            10,10,5));
            JPanel bottomButtonPanel = new JPanel(new BorderLayout());
            bottomButtonPanel.add(replayButton, BorderLayout.WEST);
            bottomButtonPanel.add(exitButton, BorderLayout.CENTER);
            bottomButtonPanel.add(nextLevelButton, BorderLayout.EAST);
            winPanel.add(bottomButtonPanel,
                    helper.setGridBagConstraints(GridBagConstraints.PAGE_END,
                            0,1,0,3,1,
                            5,10,5));
            winDialog.add(winPanel);
            winDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            winDialog.pack();
            winDialog.setLocationRelativeTo(null);
            winDialog.setVisible(false);

        }
        void setVisible(){
            winDialog.setVisible(true);
        }
        void setExitButtonListener(ActionListener listener){
            exitButton.addActionListener(listener);
        }
        void setPlayAgainListener(ActionListener listener){
            replayButton.addActionListener(listener);
        }
        void setNextLevelButtonListener(ActionListener listener){
            nextLevelButton.addActionListener(listener);
        }

        private class CustomTableModel extends AbstractTableModel {
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

