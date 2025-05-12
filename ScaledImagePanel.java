import javax.swing.*;
import java.awt.*;

public class ScaledImagePanel extends JPanel {
    private Image image;

    public ScaledImagePanel(String imagePath) {
        this.image = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int imgWidth = image.getWidth(this);
        int imgHeight = image.getHeight(this);

        if (imgWidth <= 0 || imgHeight <= 0) return;

        // Calculate aspect ratio
        double imgAspect = (double) imgWidth / imgHeight;
        double panelAspect = (double) panelWidth / panelHeight;

        int drawWidth, drawHeight;

        if (panelAspect > imgAspect) {
            // Panel is wider than image aspect → fit height
            drawHeight = panelHeight;
            drawWidth = (int) (panelHeight * imgAspect);
        } else {
            // Panel is narrower → fit width
            drawWidth = panelWidth;
            drawHeight = (int) (panelWidth / imgAspect);
        }

        int x = (panelWidth - drawWidth) / 2;
        int y = (panelHeight - drawHeight) / 2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        g2d.drawImage(image, x, y, drawWidth, drawHeight, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aspect Ratio Preserved Image");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            ScaledImagePanel panel = new ScaledImagePanel("usa.png");
            frame.add(panel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}