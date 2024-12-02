package gui.QuenMatKhau.textfield;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Timer;

public class RoundedButton extends JButton {

    private Color borderColor = new Color(3, 155, 216); // Màu viền mặc định
    private Color backgroundColor = new Color(0, 150, 136); // Màu nền mặc định
    private boolean isClicked = false; // Kiểm tra xem nút có được nhấn không
    private Timer glowTimer; // Timer cho hiệu ứng phát sáng

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false); // Tắt chế độ tự vẽ nền của JButton
        setFocusPainted(false); // Tắt viền khi focus
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Đổi con trỏ khi hover

        // Thiết lập Timer cho hiệu ứng phát sáng
        glowTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isClicked) {
                    setBackgroundColor(backgroundColor.darker());
                    setBorderColor(borderColor.brighter());
                } else {
                    setBackgroundColor(backgroundColor);
                    setBorderColor(borderColor);
                }
                repaint();
            }
        });

        // Ngừng phát sáng khi người dùng thả chuột
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isClicked = !isClicked;
                glowTimer.start(); // Bắt đầu hiệu ứng phát sáng khi nhấn
            }
        });
    }

    // Thiết lập màu nền
    private void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    // Thiết lập màu viền
    private void setBorderColor(Color color) {
        borderColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền bo tròn
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bo tròn góc với bán kính 30

        // Vẽ viền bo tròn
        g2.setColor(borderColor);
        g2.setStroke(new java.awt.BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Bo tròn góc với bán kính 30

        // Vẽ chữ trên nút
        g2.setColor(Color.WHITE);
        g2.drawString(getText(), getWidth() / 2 - g2.getFontMetrics().stringWidth(getText()) / 2, getHeight() / 2 + g2.getFontMetrics().getHeight() / 4);
    }
    
    @Override
    public void paintBorder(Graphics g) {
        // Không vẽ viền mặc định của JButton
    }
    
   
     
}
