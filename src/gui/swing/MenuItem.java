/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.swing;

import gui.event.EventMenu;
import gui.event.EventMenuSelected;
import gui.model.ModelMenu;
import gui.swing.MenuButton;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author User
 */
public class MenuItem extends javax.swing.JPanel {

    public ModelMenu getMenu() {
        return menu;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public EventMenuSelected getEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(EventMenuSelected eventSelected) {
        this.eventSelected = eventSelected;
    }

    public int getIndex() {
        return index;
    }

    public void setCustomPaintEnabled(boolean enabled) {
        
        this.customPaintEnabled = enabled;
        repaint();
    }

    private float alpha;
    private ModelMenu menu;
    private boolean open;
    private EventMenuSelected eventSelected;
    private int index;
    private boolean customPaintEnabled = true;

    public MenuItem(ModelMenu menu, EventMenu event, EventMenuSelected eventSelected, int index) {
        initComponents();
        this.menu = menu;
        this.eventSelected = eventSelected;
        this.index = index;
        setOpaque(false);
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, 30!]0[fill, 35!]"));
        MenuButton firstItem = new MenuButton(menu.getIcon(), "      " + menu.getMenuName());
        firstItem.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 17));
        // hiệu ứng đống mở
        firstItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (menu.getSubMenu().length > 0) {
                    if (event.menuPressed(MenuItem.this, !open)) {
                        open = !open;

                    }
                }
                eventSelected.menuSelected(index, -1);
            }
        });
        add(firstItem);
        int subMenuIndex = -1;
        //add subitem
        for (String st : menu.getSubMenu()) {
            MenuButton item = new MenuButton(st);
            item.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    eventSelected.menuSelected(index, item.getIndex());
                }
            });
            add(item);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!customPaintEnabled) {
            int width = getWidth();
            int height = getPreferredSize().height;
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.decode("#2c3e50"));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.fillRect(0, 65, width, 38);
            g2.setComposite(AlphaComposite.SrcOver);
            g2.fillRect(0, 100, width, height - 100);
            g2.setColor(new Color(100, 100, 100));
            // chứa menu con thì mới vẽ đường line và vẽ mũi tên xuống cho các component cha
            if (menu.getSubMenu().length > 0) {
                g2.drawLine(30, 100, 30, height - 10);
                createArrowButton(g2);

            }
            // vẽ đường line ngang cho các submenu
            for (int i = 0; i < menu.getSubMenu().length; i++) {
                int y = ((i + 1) * 35 + 100) - 10;
                g2.drawLine(30, y, 38, y);
            }
            super.paintComponent(g);
            return;
        }
        int width = getWidth();
        int height = getPreferredSize().height;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode("#11998e"));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.fillRect(0, 65, width, 38);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillRect(0, 100, width, height - 100);
        g2.setColor(new Color(100, 100, 100));
        // chứa menu con thì mới vẽ đường line và vẽ mũi tên xuống cho các component cha
        if (menu.getSubMenu().length > 0) {
            g2.drawLine(30, 100, 30, height - 10);
            createArrowButton(g2);

        }
        // vẽ đường line ngang cho các submenu
        for (int i = 0; i < menu.getSubMenu().length; i++) {
            int y = ((i + 1) * 35 + 100) - 10;
            g2.drawLine(30, y, 38, y);
        }
        super.paintComponent(g);
    }

    // hàm vẽ đường line cho menu con(mũi tên lên xuống)
    public void createArrowButton(Graphics2D g2) {
        int size = 4;
        int y = 80;
        int x = 205;
        g2.setColor(new Color(250, 250, 250));
        float ay = alpha * size;
        float ay1 = (1f - alpha) * size;
        g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
        g2.drawLine((x + 4), (int) (y + ay1), x + 8, (int) (y + ay));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();

        lblIcon.setForeground(new java.awt.Color(255, 255, 255));

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblIcon)
                .addGap(18, 18, 18)
                .addComponent(lblName)
                .addContainerGap(252, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}
