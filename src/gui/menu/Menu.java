/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.menu;

import com.formdev.flatlaf.ui.FlatUIUtils;
import gui.event.EventMenu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.menu.mode.ToolBarAccentColor;
import gui.model.ModelMenu;
import gui.swing.MenuAnimation;
import gui.swing.MenuItem;
import gui.swing.ScrollBarCustom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author User
 */
public class Menu extends javax.swing.JPanel {

    //kiểm soát vẽ components
    private boolean customPaintEnabled = true;

    public void setCustomPaintEnabled(boolean enabled) {
        this.customPaintEnabled = enabled;
        repaint();
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    /**
     * @return the enableMenu
     */
    public boolean isEnableMenu() {
        return enableMenu;
    }

    /**
     * @param enableMenu the enableMenu to set
     */
    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    /**
     * @return the showMenu
     */
    public boolean isShowMenu() {
        return showMenu;
    }

    /**
     * @param showMenu the showMenu to set
     */
    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }
    private final MigLayout layout;
//    private final Animator animator;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;
    private MenuItem menuItem;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]-25[]");
        panel.setLayout(layout);

    }

    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/business.png")), "Bán hàng"));

        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/drugs.png")), "Thuốc"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/checklist.png")), "Hóa đơn","Tạo hóa đơn đổi trả", "Lịch sử hóa đơn", "Lịch sử hóa đơn đổi trả"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/analysis.png")), "Thống kê", "Thuốc", "Khách hàng", "Doanh thu"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/computer.png")), "Báo cáo", "Phiếu kiểm tiền", "Kết toán", "Danh sách phiếu kiểm tiền", "Danh sách kết toán"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/end.png")), "Khách hàng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/employee.png")), "Nhân viên"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/transportation.png")), "Nhà cung cấp"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/img/log-out.png")), "Đăng xuất"));

    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 100!");
    }

    // ẩn tất cả menu
    public void hideAllMenu() {
        for (Component c : panel.getComponents()) {
            MenuItem item = (MenuItem) c;
            if (item.isOpen()) {
                new MenuAnimation(layout, c, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (showMenu) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    //update menu cho darkmode hoac lightmode
    public void updateMenuColors(boolean isDarkMode) {
        if (isDarkMode) {
            this.setBackground(new Color(45, 45, 45)); // Màu nền tối
            this.setForeground(Color.WHITE); // Màu chữ trắng
        } else {
            this.setBackground(Color.WHITE); // Màu nền sáng
            this.setForeground(Color.BLACK); // Màu chữ đen
        }
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile2 = new gui.menu.Profile();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        profile2.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        if (!customPaintEnabled) {
            super.paintComponent(g); // Sử dụng vẽ mặc định
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#11998e"), getWidth(), 0, Color.decode("#38ef7d"));
        g2.setPaint(gp);

        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private gui.menu.Profile profile2;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
