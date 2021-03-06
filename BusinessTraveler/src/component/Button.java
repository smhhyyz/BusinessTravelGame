/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Yun_c
 */
public class Button extends javax.swing.JPanel implements MouseListener {

    /**
     * Creates new form Button
     */
    public Button() {
        initComponents();
        addMouseListener(this);
    }

    public Button(String str) {
        initComponents();
        this.setText(str);
        addMouseListener(this);
    }

    public void setText(String text) {
        this.content.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        content.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        content.setText("Button");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(content, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private boolean enabled = true;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.enabled) {
            setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.enabled) {
            setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.enabled) {
            this.setBackground(new java.awt.Color(255, 255, 255));
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.enabled) {
            setBackground(new java.awt.Color(240, 240, 240));
        }

    }

    public String getText() {
        return this.content.getText();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean b) {
        this.enabled = b;
        if (this.enabled) {
            setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            setBackground(new java.awt.Color(240, 240, 240));
        }else{
            setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            setBackground(new java.awt.Color(210, 210, 210));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel content;
    // End of variables declaration//GEN-END:variables
}
