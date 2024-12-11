/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.menu;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import dao.Thuoc_DAO;
import entity.TaiKhoan;
import entity.Thuoc;
import glasspanepopup.DefaultOption;
import gui.menu.mode.ThemeController;
import gui.notifications.Notifications;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import jdk.jshell.Diag;
import main.Main;

/**
 *
 * Trần Xuân Trường
 */
public class Header extends javax.swing.JPanel {

    private JFrame mainFrame;
    private JPopupMenu expiredMedsPopup;
    private Main main;

    public Header(String ten, String vaiTro, JFrame mainFrame) {
        this.mainFrame = mainFrame;
        main = (Main) mainFrame;
        initComponents();
        lbUserName.setText(ten);
        lbRole.setText(vaiTro);
        glasspanepopup.GlassPanePopup.install(mainFrame);
    }

    public void addMenuEvent(ActionListener event) {
        cdmMenu.addActionListener(event);
    }

    public void setTheme() {
        if (ThemeController.isDarkMode()) {
            this.setOpaque(false);
            main.setDarkBackGround();
            main.getMenu().setCustomPaintEnabled(false);
            main.getMenu().setMenuItemCustomPaint(false);
            this.setDarkBackGround();
            ThemeController.applyTheme();
            SwingUtilities.updateComponentTreeUI(mainFrame);
        } else {
            main.getMenu().setCustomPaintEnabled(true);
            main.getMenu().getItemFull().setCustomPaintEnabled(true);

        }
    }

    public void setDarkBackGround() {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatArcDarkIJTheme.setup();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cdmMenu = new gui.swing.Button();
        pic = new gui.swing.ImageAvatar();
        lbUserName = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cmd = new utilities.Button();
        themeToggle = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(127, 127, 127));

        cdmMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.png"))); // NOI18N
        cdmMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdmMenuActionPerformed(evt);
            }
        });

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profile.png"))); // NOI18N

        lbUserName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(127, 127, 127));
        lbUserName.setText("UserName");

        lbRole.setForeground(new java.awt.Color(127, 127, 127));
        lbRole.setText("Admin");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bell.png"))); // NOI18N
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        themeToggle.setText("Chuyển chế độ màn hình");
        themeToggle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                themeToggleStateChanged(evt);
            }
        });
        themeToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeToggleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(cdmMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addComponent(themeToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUserName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRole, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(cdmMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pic, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRole))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(themeToggle)
                            .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cdmMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdmMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cdmMenuActionPerformed

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        glasspanepopup.GlassPanePopup.showPopup(new Notifications(), new DefaultOption() {
            @Override
            public float opacity() {
                return 0;
            }

        });
    }//GEN-LAST:event_cmdActionPerformed

    private void themeToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeToggleActionPerformed
        ThemeController.toggleTheme();
        setTheme();
    }//GEN-LAST:event_themeToggleActionPerformed

    private void themeToggleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_themeToggleStateChanged
    }//GEN-LAST:event_themeToggleStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.Button cdmMenu;
    private utilities.Button cmd;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbUserName;
    private gui.swing.ImageAvatar pic;
    private javax.swing.JToggleButton themeToggle;
    // End of variables declaration//GEN-END:variables
}
