/*
 * Created by JFormDesigner on Wed Oct 10 14:34:25 BST 2018
 */

package gui;

import settings.Settings;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class gui {

    private Settings settings;

    public gui(Settings settings) {
        initComponents();
        this.settings = settings;
    }

    public void main() {
        gui gui = new gui(this.settings);
        gui.this.mainFrame.setVisible(true);
    }

    private void startActionPerformed(ActionEvent e) {
        if (houseTeleport.isSelected()) {
            settings.setUseHouseTeleport(true);
        }
        settings.setStarted(true);
        gui.this.mainFrame.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        houseTeleport = new JRadioButton();
        faladorTeleport = new JRadioButton();
        start = new JButton();

        //======== mainFrame ========
        {
            mainFrame.setTitle("Ragnars Giant Mole");
            Container mainFrameContentPane = mainFrame.getContentPane();
            mainFrameContentPane.setLayout(new GridBagLayout());
            ((GridBagLayout)mainFrameContentPane.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)mainFrameContentPane.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)mainFrameContentPane.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)mainFrameContentPane.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //======== mainPanel ========
            {
                mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
                mainPanel.setLayout(new GridBagLayout());
                ((GridBagLayout)mainPanel.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)mainPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)mainPanel.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
                ((GridBagLayout)mainPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                //---- houseTeleport ----
                houseTeleport.setText("House Teleport");
                mainPanel.add(houseTeleport, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- faladorTeleport ----
                faladorTeleport.setText("Falador Teleport");
                mainPanel.add(faladorTeleport, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- start ----
                start.setText("Start");
                start.addActionListener(e -> startActionPerformed(e));
                mainPanel.add(start, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            mainFrameContentPane.add(mainPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(mainFrame.getOwner());
        }

        //---- teleportMode ----
        ButtonGroup teleportMode = new ButtonGroup();
        teleportMode.add(houseTeleport);
        teleportMode.add(faladorTeleport);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JRadioButton houseTeleport;
    private JRadioButton faladorTeleport;
    private JButton start;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
