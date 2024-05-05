package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JFrame {
    
    private static final long serialVersionUID = 1L;

    public GameMenu() {
        super("Simulation GARDIEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        initComponents(); 
    }

    private void initComponents() {
    	JLabel background = new JLabel(new ImageIcon("Images/fond.png"));
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.setPreferredSize(new Dimension(900, 300)); 

        JLabel label = new JLabel("GUARDIAN");
        label.setHorizontalAlignment(JLabel.CENTER); 
        label.setFont(new Font("Arial", Font.BOLD, 55));

        panel.add(label, BorderLayout.CENTER); 
        JButton jouerButton = new JButton("Jouer");
        jouerButton.setPreferredSize(new Dimension(200, 50));
        jouerButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                GUISec gui = new GUISec("Simulation"); 
                Thread t = new Thread(gui); 
                t.start(); 
                setVisible(false); 
            }
        });
        
        JButton configurerButton = new JButton("Configurer"); 
        configurerButton.setPreferredSize(new Dimension(200, 50)); 
        configurerButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurerFrame configFrame = new ConfigurerFrame();
                configFrame.setVisible(true); 
                dispose(); 
            }
        });

        JButton quitterButton = new JButton("Quitter"); 
        quitterButton.setPreferredSize(new Dimension(200, 50)); 
        quitterButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        configurerButton.setForeground(Color.BLUE);
        quitterButton.setForeground(Color.RED);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        buttonPanel.add(jouerButton);
        buttonPanel.add(configurerButton);
        buttonPanel.add(quitterButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(background, BorderLayout.CENTER); 

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }
}
