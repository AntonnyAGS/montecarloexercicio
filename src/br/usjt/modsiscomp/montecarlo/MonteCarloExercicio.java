package br.usjt.modsiscomp.montecarlo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
 
/**
 * This is a test program that draws an image provided by the user and scales
 * the image to fit its parent container (a JLabel).
 *
 * @author www.codejava.net, Adaptado por Elcio A. Dez/2018  
 *  /home/elcio/Documentos/aulas_2020_1/MODSISCOMP/monte_carlo_figura_base.jpg
 * 
 */
public class MonteCarloExercicio extends JFrame implements ActionListener {
     
    private JLabel labelImgFilePath = new JLabel("Entre caminho para a imagem: ");
    private JTextField fieldImgFilePath = new JTextField(20);
    private JButton buttonDisplay = new JButton("Exibir");
     
    private JLabel labelImage = new ScaledImageLabel();
     
    public MonteCarloExercicio() {
        super("Monte Carlo - Exercício");
         
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTHWEST;
         
        constraints.gridy = 0;
        constraints.gridx = 0;
        add(labelImgFilePath, constraints);
         
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
         
        constraints.gridx = 1;
        add(fieldImgFilePath, constraints);
         
        constraints.gridx = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.0;
        add(buttonDisplay, constraints);
         
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        labelImage.setPreferredSize(new Dimension(550, 550));
        add(labelImage, constraints);
 
        buttonDisplay.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
     
    @Override
    public void actionPerformed(ActionEvent event) {
        String filePath = fieldImgFilePath.getText();
        try {
            
            File file= new File(filePath);
            BufferedImage imageB = ImageIO.read(file);
            // Getting pixel color by position x=100 and y=40 

            System.out.println("Ponto: [90,90] --> "+findColor(imageB,90,90));
            System.out.println("Ponto: [200,200] --> "+findColor(imageB,200,200));
            
            labelImage.setIcon(new ImageIcon(imageB));
            
            paintPixel(imageB,90,90);
            
            labelImage.setIcon(new ImageIcon(imageB));
             
        } catch (Exception ex) {
            System.err.println(ex);
        }      
    }
    
    private String findColor(BufferedImage image, int x, int y) {
        int clr=  image.getRGB(x,y); 
        int  red   = (clr & 0x00ff0000) >> 16;
        int  green = (clr & 0x0000ff00) >> 8;
        int  blue  =  clr & 0x000000ff;
        return "R:"+red+" G:"+green+" B:"+blue;
    }
    
    private Image paintPixel(BufferedImage image, int x, int y) {
    	Graphics2D g2d = image.createGraphics();
    	g2d.setColor(Color.RED);
    	g2d.fillRect(x, y, 1, 1);
    	g2d.dispose();
    	return image;
    }
    
 
    
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
             
            @Override
            public void run() {
                new MonteCarloExercicio().setVisible(true);
            }
        });
    }
}