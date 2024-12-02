package gui.QuenMatKhau.textfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextField extends JTextField {

    private boolean mouseOver = false;
    private Color lineColor = new Color(3, 155, 216);

    public TextField() {
        // Đặt kích thước mặc định nhỏ hơn và padding
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setSelectionColor(new Color(76, 204, 255));
        setOpaque(false);

        // Xử lý sự kiện chuột
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                repaint();
            }
        });
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = 15; // Bo góc tròn

        // Vẽ nền
        if (isFocusOwner()) {
            g2.setColor(new Color(230, 240, 255)); // Màu nền khi focus
        } else {
            g2.setColor(Color.WHITE); // Màu nền mặc định
        }
        g2.fill(new RoundRectangle2D.Double(1, 1, width - 2, height - 2, arc, arc));

        // Vẽ viền
        if (mouseOver || isFocusOwner()) {
            g2.setColor(lineColor);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        g2.draw(new RoundRectangle2D.Double(1, 1, width - 2, height - 2, arc, arc));

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Không vẽ border mặc định để tránh ghi đè lên viền tùy chỉnh
    }
}
