package contaes;


import com.sun.java.swing.Painter;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.UIDefaults;

/**
 *
 * @author McCubo
 */
public class McLaFNimbus extends NimbusLookAndFeel {

    public McLaFNimbus() {
        super();
    }

    @Override
    public UIDefaults getDefaults() {
        UIDefaults defaults = super.getDefaults();
        String closePressed = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.closeButton\"[Pressed].backgroundPainter";
        String closeEnable = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.closeButton\"[Enabled].backgroundPainter";
        String closeEnableNotFocused = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.closeButton\"[Enabled+WindowNotFocused].backgroundPainter";
        String closeRollOverNotFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.closeButton\"[MouseOver+WindowNotFocused].backgroundPainter";
        String closeRollOver = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.closeButton\"[MouseOver].backgroundPainter";

        String iconEnable = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.iconifyButton\"[Enabled].backgroundPainter";
        String iconRollOver = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.iconifyButton\"[MouseOver].backgroundPainter";
        String iconPressed = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.iconifyButton\"[Pressed].backgroundPainter";
        String iconRollOverNotFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.iconifyButton\"[MouseOver+WindowNotFocused].backgroundPainter";
        String iconNotFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.iconifyButton\"[Enabled+WindowNotFocused].backgroundPainter";

        String maximizeEnable = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[Enabled].backgroundPainter";
        String maximizeMaxEnable = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[Enabled+WindowMaximized].backgroundPainter";
        String maximizeRollOverFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[MouseOver].backgroundPainter";
        String maximizeMaxRollOverFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[MouseOver+WindowMaximized].backgroundPainter";
        String maximizePressed = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[Pressed].backgroundPainter";
        String maximizeMaxPressed = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[Pressed+WindowMaximized].backgroundPainter";
        String maximizeNotFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[Enabled+WindowNotFocused].backgroundPainter";
        String maximizeRollOverNotFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[MouseOver+WindowNotFocused].backgroundPainter";
        String maximizeMaxNotFocus = "InternalFrame:InternalFrameTitlePane:\"InternalFrameTitlePane.maximizeButton\"[MouseOver+WindowMaximized+WindowNotFocused].backgroundPainter";

        defaults.put(closeEnableNotFocused, getPainter("src/resources/LookAndFeel/closeNotFocused.png"));
        defaults.put(closeRollOver, getPainter("src/resources/LookAndFeel/FrameCloseRoll.png"));
        defaults.put(closeEnable, getPainter("src/resources/LookAndFeel/FrameClose.png"));
        defaults.put(closePressed, getPainter("src/resources/LookAndFeel/FrameClosePush.png"));
        defaults.put(closeRollOverNotFocus, getPainter("src/resources/LookAndFeel/rollOverNotFocus.png"));

        defaults.put(iconEnable, getPainter("src/resources/LookAndFeel/iconEnable.png"));
        defaults.put(iconRollOver, getPainter("src/resources/LookAndFeel/iconRollOverFocus.png"));
        defaults.put(iconPressed, getPainter("src/resources/LookAndFeel/iconPush.png"));
        defaults.put(iconRollOverNotFocus, getPainter("src/resources/LookAndFeel/iconRollOverNotFocus.png"));
        defaults.put(iconNotFocus, getPainter("src/resources/LookAndFeel/iconNotFocus.png"));

        defaults.put(maximizeEnable, getPainter("src/resources/LookAndFeel/FrameMaximize.png"));
        defaults.put(maximizeMaxEnable, getPainter("src/resources/LookAndFeel/FrameRestore.png"));
        defaults.put(maximizeRollOverFocus, getPainter("src/resources/LookAndFeel/maximizeRollOverFocus.png"));
        defaults.put(maximizeMaxRollOverFocus, getPainter("src/resources/LookAndFeel/FrameRestoreMaxRollOver.png"));
        defaults.put(maximizePressed, getPainter("src/resources/LookAndFeel/FrameRestorePressed.png"));
        defaults.put(maximizeMaxPressed, getPainter("src/resources/LookAndFeel/FrameRestoreMaxPressed.png"));
        defaults.put(maximizeNotFocus, getPainter("src/resources/LookAndFeel/maximizeNotFocus.png"));
        defaults.put(maximizeRollOverNotFocus, getPainter("src/resources/LookAndFeel/maximizeRollOverNotFocus.png"));
        defaults.put(maximizeMaxNotFocus, getPainter("src/resources/LookAndFeel/maximizeMaxNotFocus.png"));
        return defaults;
    }

    public Painter<JComponent> getPainter(final String path) {
        return new Painter<JComponent>() {

            public void paint(Graphics2D g, JComponent object, int width, int height) {
                g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g.setStroke(new BasicStroke(2f));
                g.drawImage(ImageUtils.getScaledImage(path).getImage(), 0, 0, null);
            }

            public void paint(Graphics2D g, Object object, int width, int height) {
                 g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                 g.setStroke(new BasicStroke(2f));
                 g.drawImage(ImageUtils.getScaledImage(path).getImage(), 0, 0, null);
            }
        };
    }
}
class ImageUtils {
public void paint(Graphics2D g, Object object, int width, int height) {
                 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                 g.setStroke(new BasicStroke(2f));
                 g.drawImage(ImageUtils.getScaledImage("thanks8.png").getImage(), 0, 0, null);
            }
    /**
     *public void paint(Graphics2D g, Object object, int width, int height) {
                 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                 g.setStroke(new BasicStroke(2f));
                 g.drawImage(ImageUtils.getScaledImage(path).getImage(), 0, 0, null);
            }
            *    case 0:
                    setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    break;
            case 1:
                    setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    break;
            case 2:
                    setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                    break;
            case 3:
                    setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    break;
            case 4:
                    setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
            case 5:
                    setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel"); 
     * @param URL direccion de la imagen dentro del proyecto
     * @return una Imagen redimensionada a 16 x 16
     */
    public static ImageIcon getScaledImage(String URL){
        ImageIcon sourceIcon = new ImageIcon(URL);
        ImageIcon scaledIcon = new ImageIcon(sourceIcon.getImage().getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING));
        return scaledIcon;
    }

}
