/*
 * Copyright 2012 Sardonix Creative.
 *
 * This work is licensed under the
 * Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * To view a copy of this license, visit
 *
 *      http://creativecommons.org/licenses/by-nc-nd/3.0/
 *
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900,
 * Mountain View, California, 94041, USA.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aurora.V1.core;

import aurora.engine.V1.UI.AButton;
import aurora.engine.V1.UI.AImage;
import aurora.engine.V1.UI.AImagePane;
import aurora.engine.V1.UI.AProgressWheel;
import aurora.engine.V1.UI.ASlickLabel;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AuroraMini {

    private JDialog mini;
    private AuroraCoreUI ui;
    private AImagePane pnlBackground;
    private AImage icon;
    private JPanel pnlIconPane;
    private Point location;
    private MouseEvent pressed;
    private Timer timer;
    public static Boolean isIconHover = true;
    private String mode;
    private int yPos;
    private ASlickLabel lblStatus;
    private JPanel pnlStatus;
    private boolean isDrag = false;
    public static Boolean isMinimode = false;
    public static final String LOADING_MODE = "startup";
    public static final String MINIMIZE_MODE = "minimize";
    private AButton close;
    private boolean firstClick;
    private PaneAnimateHandler animationHander;

    public AuroraMini(AuroraCoreUI ui, String mode) {
        this.ui = ui;
        this.mode = mode;
    }

    public void setStatus(String status) {

        lblStatus.setText(status);
    }

    public void setMode(String mode) {
        this.mode = mode;
        executeMode();
    }

    public void createUI() {
        isMinimode = true;
        if (mini == null) { // retain state

            //SET UP FRAME
            mini = new JDialog();

            mini.setUndecorated(true);
            mini.setBackground(Color.BLACK);
            mini.setResizable(false);
            mini.setSize(300, 80);
            mini.setLocation(ui.getScreenWidth() - 65, ui.getScreenHeight() - 160);

            mini.setAlwaysOnTop(true);

            //SET FRAME ICON
            try {
                mini.setIconImage(new ImageIcon(new URL(ui.getResource().getSurfacePath() + "/aurora/V1/resources/icon.png")).getImage());
            } catch (MalformedURLException ex) {
                try {

                    mini.setIconImage(new ImageIcon(getClass().getResource("/aurora/V1/resources/icon.png")).getImage());

                } catch (Exception exx) {
                    Logger.getLogger(AuroraMini.class.getName()).log(Level.SEVERE, null, exx);
                }
            }

            //SET UP BACKGROUND
            pnlBackground = new AImagePane("Starter.png", new FlowLayout(FlowLayout.LEFT, 0, 0));
            pnlBackground.setPreferredSize(mini.getSize());

            //CREATE
            icon = new AImage("icon.png");
            icon.addMouseListener(new IconHoverHandler());

            pnlIconPane = new JPanel(new BorderLayout(0, 0));
            pnlIconPane.setOpaque(false);
            pnlIconPane.setPreferredSize(new Dimension(icon.getImgWidth() + 5, mini.getHeight()));


            lblStatus = new ASlickLabel();
            lblStatus.setFont(ui.getDefaultFont().deriveFont(Font.BOLD, 30));
            lblStatus.setForeground(Color.LIGHT_GRAY);

            pnlStatus = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            pnlStatus.setOpaque(false);

            close = new AButton("Aurora_Close_normal.png", "Aurora_Close_down.png", "Aurora_Close_over.png");

            //ADD HANDLERS//

            //BACKGROUND IMAGE CHANGE ON HOVER
            pnlBackground.addMouseListener(new PanelHoverHandler());
            icon.addMouseListener(new PanelHoverHandler());
            close.addMouseListener(new PanelHoverHandler());



            //DRAG VERTICAL FUNCTIONALITY
            pnlBackground.addMouseMotionListener(new IconPaneMotionLister());
            pnlBackground.addMouseListener(new IconPaneMouseListener());

            icon.addMouseListener(new IconPaneMouseListener());
            icon.addMouseMotionListener(new IconPaneMotionLister());


            //ANIMATING POP OUT
            animationHander = new PaneAnimateHandler();
            pnlBackground.addMouseListener(animationHander);
            close.addMouseListener(animationHander);

            //CLOSE BUTTON HANDER
            close.addActionListener(new CloseButtonHander());

            //ADD COMPONENTS TO OTHER COMPONENTS
            pnlIconPane.add(icon, BorderLayout.EAST);
            pnlStatus.add(lblStatus);

            pnlBackground.add(pnlIconPane);
            pnlBackground.add(pnlStatus);
            mini.getContentPane().add(pnlBackground);
        }
        mini.setVisible(true);
        executeMode();
        mini.requestFocusInWindow();

    }

    private void executeMode() {
        icon.setImgURl("icon.png");

        if (mode.equals("minimize")) {
            mini.setLocation((ui.getScreenWidth() - 70) - 170, mini.getY()); //pop out
            animateIN();                                                          //Animate in
            lblStatus.setText(" READY");

            //Remove progress wheel if it exists
            if (pnlBackground.getComponentCount() == 4) {
                pnlBackground.remove(3);
                pnlBackground.remove(2);
            } else if (pnlBackground.getComponentCount() == 3) {
                pnlBackground.remove(2);
            }
            mini.repaint();
            pnlBackground.add(close);

        } else if (mode.equals("startup")) {
            mini.setLocation((ui.getScreenWidth() - 70) - 150, mini.getY());
            animateIN();
            lblStatus.setText("LOADING");
            pnlBackground.add(new AProgressWheel("Aurora_wheel.png"));
        }

    }

    //Make Mini Slide in to screen
    private void animateIN() {
        if (timer != null) {
            timer.stop();
        }
        timer = null;
        timer = new Timer(30, new AnimateINFrameListener());
        timer.setInitialDelay(900);
        timer.setRepeats(true);

        lblStatus.setText(" READY");
        firstClick = true;

        timer.start();
    }

    //Make Mini pop out of screen
    private void animateOUT() {
        if (timer != null) {
            timer.stop();
        }
        timer = null;
        timer = new Timer(30, new AnimateOUTFrameListener());
        timer.setInitialDelay(200);
        timer.setRepeats(true);
        timer.start();
    }

    private class CloseButtonHander implements ActionListener {

        public CloseButtonHander() {
            firstClick = true;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (firstClick) {
                firstClick = false;
                lblStatus.setText(" EXIT? ");
            } else {
                System.exit(0);
            }
        }
    }

    /*
     * ANIMAION Aurora Mini Slides Out Of The Screen
     */
    private class AnimateOUTFrameListener implements ActionListener {

        private int count = 0;

        public AnimateOUTFrameListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            count++; //Accelerator

            if (mini.getX() > (ui.getScreenWidth() - 70) - 170) {
                mini.setLocation(mini.getX() - 4 - count, mini.getY());
            } else {
                timer.stop();
            }
        }
    }

    /*
     * ANIMATION Aurora Mini Slides Into The Screen
     */
    private class AnimateINFrameListener implements ActionListener {

        private int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {

            count++; //Accelerator

            if (mini.getX() < (ui.getScreenWidth() - 70)) {                //Stop Here
                mini.setLocation(mini.getX() + 4 + count, mini.getY());         //Animate
            } else {
                timer.stop();

            }

        }
    }

    private class PaneAnimateHandler implements MouseListener {

        public PaneAnimateHandler() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {

            animateOUT();
        }

        @Override
        public void mouseExited(MouseEvent e) {


            animateIN();

        }
    }

    private class IconPaneMotionLister implements MouseMotionListener {

        public IconPaneMotionLister() {
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            location = mini.getLocation(location);
            // int x = location.x - pressed.getX() + me.getX();
            icon.setImgURl("Starter_Drag_normal.png");

            yPos = location.y - pressed.getY() + me.getY();
            mini.setLocation(location.x, yPos);
            isDrag = true;

        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

    private class IconPaneMouseListener implements MouseListener {

        private boolean isMouseExited;

        public IconPaneMouseListener() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
            pressed = me;
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            isDrag = false;
            System.out.println("Mouse Released");
            if (isMouseExited) {
                icon.setImgURl("icon.png");
            } else {
                icon.setImgURl("Starter_Start_normal.png");
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            isMouseExited = false;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            isMouseExited = true;
            icon.setImgURl("icon.png");
        }
    }

    private class PanelHoverHandler implements MouseListener {

        public PanelHoverHandler() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            pnlBackground.setImage("Starter_over.png");


        }

        @Override
        public void mouseExited(MouseEvent e) {

            pnlBackground.setImage("Starter.png");

        }
    }

    //When Click On Icon
    private class IconHoverHandler implements MouseListener {

        public IconHoverHandler() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!isDrag) {

                mini.setVisible(false);
                ui.getFrame().setLocation(0, 0);
                ui.getFrame().requestFocus();
                isMinimode = false;
                ui.getFrame().setVisible(true);
                ui.getFrame().setState(JFrame.NORMAL);
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            icon.setImgURl("icon.png");
        }

        @Override
        public void mouseEntered(MouseEvent e) {


            if (timer != null) {
                timer.stop();
            }

            icon.setImgURl("Starter_Start_normal.png");


        }

        @Override
        public void mouseExited(MouseEvent e) {

            if (timer != null && !timer.isRunning()) {
                timer.start();
            }
            icon.setImgURl("icon.png");
        }
    }
}
