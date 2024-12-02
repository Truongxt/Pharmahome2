package gui.QuenMatKhau.textfield;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class PasswordField extends JPasswordField {

    private final Image eye;
    private final Image eye_hide;
    private boolean hide = true;
    private boolean showAndHide = true;
    private boolean mouseOver = false;
    private Color lineColor = new Color(3, 155, 216);

    public PasswordField() {
        // Đặt kích thước mặc định với chiều cao là 42
        setPreferredSize(new java.awt.Dimension(200, 42)); // Đặt chiều rộng là 200, chiều cao là 42
        setBorder(new EmptyBorder(10, 10, 10, 30)); // Đảm bảo viền phù hợp với kích thước mới
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

            @Override
            public void mousePressed(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 30;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        hide = !hide;
                        setEchoChar(hide ? '*' : (char) 0);
                        repaint();
                    }
                }
            }
        });

        // Xử lý sự kiện di chuyển chuột
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 30;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    } else {
                        setCursor(new Cursor(Cursor.TEXT_CURSOR));
                    }
                }
            }
        });

        // Tải ảnh cho biểu tượng hiển thị/ẩn mật khẩu
        eye = new ImageIcon(getClass().getResource("/gui/QuenMatKhau/textfield/eye.png")).getImage();
        eye_hide = new ImageIcon(getClass().getResource("/gui/QuenMatKhau/textfield/eye_hide.png")).getImage();
    }

    public boolean isShowAndHide() {
        return showAndHide;
    }

    public void setShowAndHide(boolean showAndHide) {
        this.showAndHide = showAndHide;
        repaint();
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
        int arc = 15; // Bo tròn góc nhỏ hơn để phù hợp kích thước nhỏ

        // Vẽ nền
        if (isFocusOwner()) {
            g2.setColor(new Color(230, 240, 255)); // Màu nền khi được focus
        } else {
            g2.setColor(Color.WHITE); // Màu nền mặc định
        }
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, arc, arc));

        // Vẽ viền
        if (mouseOver || isFocusOwner()) {
            g2.setColor(lineColor);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, arc, arc));

        // Vẽ biểu tượng hiển thị/ẩn mật khẩu
        if (showAndHide) {
            createShowHide(g2);
        }

        super.paintComponent(g);
    }

    private void createShowHide(Graphics2D g2) {
        int x = getWidth() - 25; // Đặt gần góc phải
        int y = (getHeight() - 20) / 2;
        g2.drawImage(hide ? eye_hide : eye, x, y, null);
    }
}
