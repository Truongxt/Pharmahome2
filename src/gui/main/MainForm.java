/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.main;

import entity.TaiKhoan;
import gui.KhachHang_GUI;
import gui.NhanVien_GUI;
import gui.LoginForm;
import gui.NhaCungCap_GUI;
import gui.HoaDon_GUI;
import gui.Thuoc_GUI;
import gui.DoiTra_GUI;
import gui.ThongKeDoanhThu;
import gui.ThongKeKhachHang;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.menu.Header;
import gui.menu.Menu;
import gui.swing.MenuItem;
import gui.swing.PopupMenu;
import java.awt.BorderLayout;
import java.awt.Component;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import main.Main;
import net.miginfocom.swing.MigLayout;
import static org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.Ansi.Style.bg;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author User
 */
public class MainForm extends javax.swing.JPanel {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private LoginForm login;

    public MainForm() {
        initComponents();
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    public void showForm(Component form) {
        removeAll();
        add(form);
        repaint();
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
// public void init() {
//        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
//        this.setLayout(layout);
//        menu = new Menu();
//        header = new Header();
//        login = new LoginForm();
//        menu.addEvent(new EventMenuSelected() {
//            @Override
//            public void menuSelected(int menuIndex, int subMenuIndex) {
//                System.out.println("Menu index: " + menuIndex + "Sub Index: " + subMenuIndex);
//                if (menuIndex == 0) {
//                    try {
//                        main.showForm(new Thuoc_GUI());
//                    } catch (UnsupportedLookAndFeelException ex) {
//                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                } else if (menuIndex == 1) {
//                    if (subMenuIndex == 0) {
//                        main.showForm(new HoaDon_GUI());
//                    } else if (subMenuIndex == 1) {
//                        main.showForm(new DoiTra_GUI());
//
//                    }
//                } else if (menuIndex == 2) {
//                    if (subMenuIndex == 0) {
//                        try {
//                            main.showForm(new ThongKeThuoc_GUI());
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                            // Show an error message to the user
//                            JOptionPane.showMessageDialog(MainForm.this, "Error loading ThongKeThuoc_GUI: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                        }
//                    } else if (subMenuIndex == 1) {
//                        main.showForm(new ThongKeKhachHang());
//                    } else if (subMenuIndex == 2) {
//                        main.showForm(new ThongKeDoanhThu());
//                    } else if (subMenuIndex == 3) {
//                        main.showForm(new KetCa());
//                    }
//                } else if (menuIndex == 3) {
//                    main.showForm(new KhachHang_GUI());
//
//                } else if (menuIndex == 4) {
//                    try {
//                        main.showForm(new NhanVien_GUI());
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                } else if (menuIndex == 5) {
//                    main.showForm(new NhaCungCap_GUI());
//                }
//            }
//        });
//        menu.addEventShowPopup(new EventShowPopupMenu() {
//            @Override
//            public void showPopup(Component com) {
//                MenuItem item = (MenuItem) com;
//                PopupMenu popup = new PopupMenu(MainForm.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
//                int x = MainForm.this.getX() + 52;
//                int y = MainForm.this.getY() + com.getY() + 156;
//                popup.setLocation(x, y);
//                popup.setVisible(true);
//            }
//        });
//        menu.initMenuItem();
//        bg.add(menu, "w 230!, spany 2");
//        bg.add(header, "h 50!, wrap");
//        bg.add(main, "w 100%, h 100%");
//        TimingTarget target = new TimingTargetAdapter() {
//            @Override
//            public void timingEvent(float fraction) {
//                double width;
//                if (menu.isShowMenu()) {
//                    width = 50 + (190 * (1f - fraction));
//
//                } else {
//                    width = 50 + (190 * fraction);
//
//                }
//                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
//                menu.revalidate();
//            }
//
//            @Override
//            public void end() {
//                menu.setShowMenu(!menu.isShowMenu());
//                menu.setEnableMenu(true);
//            }
//
//        };
//        animator = new Animator(500, target);
//        animator.setResolution(0);
//        animator.setDeceleration(0.5f);
//        animator.setAcceleration(0.5f);
//
//        header.addMenuEvent(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (!animator.isRunning()) {
//                    animator.start();
//                }
//                menu.setEnableMenu(false);
//                if (menu.isShowMenu()) {
//                    //ẩn menu sau khi thu gọn
//                    menu.hideAllMenu();
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
