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
package aurora.V1.core.screen_ui;

import aurora.V1.core.AuroraApp;
import aurora.V1.core.AuroraCoreUI;
import aurora.V1.core.AuroraStorage;
import aurora.V1.core.Game;
import aurora.V1.core.StartLoader;
import aurora.V1.core.screen_handler.DashboardHandler;
import aurora.V1.core.screen_logic.DashboardLogic;
import aurora.engine.V1.Logic.aXAVI;
import aurora.engine.V1.UI.aButton;
import aurora.engine.V1.UI.aCarousel;
import aurora.engine.V1.UI.aCarouselPane;
import aurora.engine.V1.UI.aCarouselTitle;
import aurora.engine.V1.UI.aCarouselTitle.Title;
import aurora.engine.V1.UI.aImage;
import aurora.engine.V1.UI.aImagePane;
import aurora.engine.V1.UI.aInfoFeed;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * .------------------------------------------------------------------------.
 * | DashboardUI :: Aurora App Class
 * .------------------------------------------------------------------------.
 * |
 * | This class contains the UI attached to an appropriate *Handler*
 * | and * *Logic* class which handle the actions caused by the UI
 * | components found here
 * |
 * | This class must follow the rules stated in the AuroraScreenUI
 * | Interface. The *Handler* and *Logic* classes
 * | The Handler class is called: DashboardHandler
 * | The Logic class is called: DashboardLogic
 * |
 * |
 * |
 * .........................................................................
 *
 * @author sammy <sguergachi@gmail.com> carlos <camachado@gmail.com>
 *
 */
public class DashboardUI extends AuroraApp {

    /**
     * The main carousel used to navigate AuroraApps.
     */
    private aCarousel carousel;

    /**
     * Button to make carousel move left.
     */
    private aButton btnCarouselLeft;

    /**
     * Button to make carousel move right.
     */
    private aButton btnCarouselRight;

    /**
     * Image with Glow state of the Profile Title.
     */
    private aImage titleProfileGlow;

    /**
     * Image with Normal state of the Profile Title.
     */
    private aImage titleProfileNorm;

    /**
     * Image with Glow state of the Settings Title.
     */
    private aImage titleSettingGlow;

    /**
     * Image with Normal state of the Settings Title.
     */
    private aImage titleSettingNorm;

    /**
     * Image with Glow state of the Library Title.
     */
    private aImage titleLibraryGlow;

    /**
     * Image with Normal state of the Library Title.
     */
    private aImage titleLibraryNorm;

    /**
     * Image with Glow state of the AuroraNet Title.
     */
    private aImage titleAuroraNetGlow;

    /**
     * Image with Normal state of the AuroraNet Title.
     */
    private aImage titleAuroraNetNorm;

    /**
     * Title component containing both Glow and Normal state of Profile item.
     */
    private aCarouselTitle titleProfile;

    /**
     * Title component containing both Glow and Normal state of Settings item.
     */
    private aCarouselTitle titleSetting;

    /**
     * Title component containing both Glow and Normal state of Library item.
     */
    private aCarouselTitle titleLibrary;

    /**
     * Title component containing both Glow and Normal state of AuroraNet item.
     */
    private aCarouselTitle titleAuroraNet;

    /**
     * Panel Containing Title and Icon representing the Library in Carousel.
     */
    private aCarouselPane paneLibrary;

    /**
     * Panel Containing Title and Icon representing the Library in Settings.
     */
    private aCarouselPane paneSettings;

    /**
     * Panel Containing Title and Icon representing the Library in Profile.
     */
    private aCarouselPane paneProfile;

    /**
     * Panel Containing Title and Icon representing the Library in AuroraNet.
     */
    private aCarouselPane paneNet;

    /**
     * Image of Icon representing AuroraNet Carousel pane.
     */
    private aImage icoNet;

    /**
     * Image of Icon representing Profile Carousel pane.
     */
    private aImage icoProfile;

    /**
     * Image of Icon representing Settings Carousel pane.
     */
    private aImage icoSetting;

    /**
     * Image of Icon representing Library Carousel pane.
     */
    private aImagePane icoLibrary;

    /**
     * Game component which generates the icon for the Library carousel pane.
     */
    private Game randomGame;

    /**
     * Image of Keyboard Arrows indicating ability to use keyboard to navigate.
     */
    private aImage keyArrows;

    /**
     * Label describing what the Keyboard Icon will do.
     */
    private JLabel lblKeyAction;

    /**
     * A Scrolling Information ticker bar.
     */
    private aInfoFeed infoFeed;

    /**
     * An array list containing all of the info being fed to the infoFeed.
     */
    private ArrayList<String> infoArray;

    /**
     * The Panel that contains the InfoFeed component.
     */
    private JPanel pnlInfoFeed;

    /**
     * Button in frame controls to go back to Dashboard.
     */
    private aButton btnBack;

    /**
     * Size Constant.
     */
    private int btnLogoutWidth;

    /**
     * Size Constant.
     */
    private int btnLogoutHeight;

    /**
     * Size Constant.
     */
    private double carouselWidth;

    /**
     * Size Constant.
     */
    private int carouselHeight;

    /**
     * Size Constant.
     */
    private int gameCoverHeight;

    /**
     * Size Constant.
     */
    private int gameCoverWidth;

    /**
     * Size Constant.
     */
    private int carouselImageWidth;

    /**
     * Size Constant.
     */
    private int carouselImageHeight;

    /**
     * Size Constant.
     */
    private int logoHeight;

    /**
     * Size Constant.
     */
    private int infobarWidth;

    /**
     * Size Constant.
     */
    private int infobarHeight;

    /**
     * Size Constant.
     */
    private int framePadding;

    /**
     * Size Constant.
     */
    private int bottomPaneHeightAdjust;

    /**
     * Size Constant.
     */
    private int topPaneHeighAdjust;

    /**
     * Size Constant.
     */
    private int topHeight;

    /**
     * Size Constant.
     */
    private int logoWidth;

    /**
     * Size Constant.
     */
    private int carouselButtonWidth;

    /**
     * Size Constant.
     */
    private int carouselButtonHeight;

    /**
     * The loader that does the transition animation and loads the DashboardUI.
     */
    private StartLoader loader;

    /**
     * This is the Local Storage Instance.
     */
    private AuroraStorage storage;

    /**
     * This is the Previous Screen, which contains the Local Storage Instance.
     */
    private final StartScreenUI startUI;

    /**
     * This is the CoreUI Canvas.
     */
    private AuroraCoreUI coreUI;

    /**
     * This is the Handler for the DashboardUIs Actions.
     */
    private DashboardHandler handler;

    /**
     * This is the Logic for the DashboardUIs Processing.
     */
    private final DashboardLogic logic;

    /**
     * .-----------------------------------------------------------------------.
     * | DashboardUI()
     * .-----------------------------------------------------------------------.
     * |
     * | This is the Constructor of the Dashboard UI class.
     * |
     * | The Constructor of Screen Classes must initialize/create both a
     * | Handler and a Logic object which should contain the UI as a parameter
     * |
     * .........................................................................
     *
     * @param auroraCoreUi  AuroraCoreUI
     * @param startScreenUi StartScreenUI
     *
     */
    public DashboardUI(final AuroraCoreUI auroraCoreUi,
                       final StartScreenUI startScreenUi) {


        // Core UI Canvas //
        this.coreUI = auroraCoreUi;


        // The Dashboard Handler + Logic //
        this.handler = new DashboardHandler(this);
        this.logic = new DashboardLogic(this);

        // All other core objects //
        this.startUI = startScreenUi;
        this.storage = startUI.getAuroraStorage();


    }

    @Override
    public final void loadUI() {

        //* Indicate to User DashboardUI is loading. *//
        coreUI.getInfoLabel().setText(".: Loading :.");

        //Initialize Sizes
        setSizes();

        btnBack = new aButton("Aurora_Logout_normal.png",
                "Aurora_Logout_down.png", "Aurora_Logout_over.png",
                btnLogoutWidth, btnLogoutHeight);

        keyArrows = new aImage("KeyboardKeys/arrows.png", coreUI.
        		getKeyIconWidth(), coreUI.getKeyIconHeight());
        lblKeyAction = new JLabel(" Move ");

        ///.......Titles To The carousel Panels
        titleSettingGlow = new aImage("settings_glow.png");
        titleSettingNorm = new aImage("settings_normal.png");

        titleLibraryGlow = new aImage("gamelibrary_glow.png");
        titleLibraryNorm = new aImage("gamelibrary_normal.png");

        titleProfileGlow = new aImage("gamerprofile_glow.png");
        titleProfileNorm = new aImage("gamerprofile_normal.png");

        titleAuroraNetGlow = new aImage("auroranet_glow.png");
        titleAuroraNetNorm = new aImage("auroranet_normal.png");

        icoProfile = new aImage("Aurora_Profile.png");
        icoSetting = new aImage("Aurora_Settings.png");
        icoNet = new aImage("ComingSoon.png");
        icoLibrary = logic.getLibraryIcon();

        titleProfile = new aCarouselTitle(titleProfileNorm, titleProfileGlow);
        titleSetting = new aCarouselTitle(titleSettingNorm, titleSettingGlow);
        titleLibrary = new aCarouselTitle(titleLibraryNorm, titleLibraryGlow);
        titleAuroraNet = new aCarouselTitle(titleAuroraNetNorm,
                titleAuroraNetGlow);

        carousel = new aCarousel(carouselWidth, carouselHeight,
                Toolkit.getDefaultToolkit().getScreenSize().width);

        paneSettings = new aCarouselPane("HexPane.png", (int) carouselWidth + 25,
                carouselHeight - 25, true, titleSetting, "Setting Pane");
        paneProfile = new aCarouselPane("HexPane.png", (int) carouselWidth + 25,
                carouselHeight - 25, true, titleProfile, "gamer pane");
        paneLibrary = new aCarouselPane("HexPane.png", (int) carouselWidth + 25,
                carouselHeight - 25, true, titleLibrary, "library pane");
        paneNet = new aCarouselPane("HexPane.png", (int) carouselWidth + 25,
                carouselHeight - 25, true, titleAuroraNet, "auroranet");
    }

    @Override
    public final void buildUI() {


        //* Configure CoreUI *//

        //Remove KeyListener attached to frame.
        coreUI.getFrame().removeKeyListener(startUI.getStartKeyHandler());
        //Re-add background panel

        coreUI.getLogoImage().setImgURl("Aurora_Header2.png");
        coreUI.getLogoImage().setImageSize(logoWidth, logoHeight);

        coreUI.getBottomImagePane().setPreferredSize(new Dimension(coreUI.
        		getBottomImagePane().getWidth(), bottomPaneHeightAdjust));
        coreUI.getBottomImagePane().setImageHeight(bottomPaneHeightAdjust);

        coreUI.getTopImagePane().setImageHeight(topHeight);
        coreUI.getTopImagePane().setPreferredSize(new Dimension(coreUI.getTopImagePane().
                getWidth(), coreUI.getTopImagePane().getImageHeight() + coreUI.
                getFrameControlImagePane().getHeight()));



        btnBack.setToolTipText("Back");

        coreUI.getFrameControlImagePane().removeAll();

        coreUI.getFrameControlImagePane().add(btnBack);
        coreUI.getFrameControlImagePane().add(coreUI.getMinimizeButton());
        coreUI.getFrameControlImagePane().add(coreUI.getExitButton());
        coreUI.getFrameControlImagePane().setImage("Aurora_FrameButton2.png");
        
        coreUI.getSouthFromTopPanel().setPreferredSize(new Dimension(coreUI.
        		getSouthFromTopPanel().getWidth(), coreUI.getFrameControlImagePane().
                getHeight()));
        coreUI.getTopImagePane().setPreferredSize(new Dimension(coreUI.getTopImagePane().
                getWidth(), coreUI.getTopImagePane().getImageHeight() + coreUI.
                getFrameControlImagePane().getHeight()));
        coreUI.getSouthFromTopPanel().revalidate();

        //Press Enter Icons
        coreUI.getKeyToPressPanel().add(coreUI.getKeyIconImage());
        coreUI.getKeyToPressPanel().add(coreUI.getKeyActionLabel());

        //Use Arrow Keys Icons
        coreUI.getKeyToPressPanel().add(keyArrows);
        coreUI.getKeyToPressPanel().add(lblKeyAction);



        ////........ Create new Components

        //Key Actions Panel (lower left)



        lblKeyAction.setFont(coreUI.getDefaultFont().deriveFont(Font.PLAIN,
                coreUI.getKeysFontSize()));
        lblKeyAction.setForeground(Color.YELLOW);




        //Images inside carousels

        icoSetting.setImageSize(carouselImageWidth,
                carouselImageHeight);
        icoProfile.setImageSize(carouselImageWidth,
                carouselImageHeight);
        icoNet.setImageSize(carouselImageWidth, carouselImageHeight);

        icoLibrary.setPreferredSize(new Dimension(gameCoverWidth,
                gameCoverHeight));


//////////////CAROUSEL/////





        paneSettings.setName("settingsPane");
        paneSettings.addKeyListener(handler.new CarouselKeyListener());



        paneProfile.setName("profilePane");
        paneProfile.addKeyListener(handler.new CarouselKeyListener());


        paneLibrary.setName("libraryPane");
        paneLibrary.addKeyListener(handler.new CarouselKeyListener());


        paneNet.setName("auroraNetPane");

        paneSettings.addContent(icoSetting, Title.NORMAL);
        paneProfile.addContent(icoProfile, Title.NORMAL);
        paneLibrary.addContent(icoLibrary, Title.GLOW);
        paneNet.addContent(icoNet, Title.GLOW);

        carousel.addPane(paneSettings);
        carousel.addPane(paneLibrary);
        carousel.addPane(paneProfile);
        carousel.addPane(paneNet);
        carousel.addKeyListener(handler.new CarouselKeyListener());


        ///.....Check for the Enter Button Press OR Mouse Click

        paneProfile.addMouseListener(handler.new CarouselPaneMouseListener());
        paneSettings.addMouseListener(handler.new CarouselPaneMouseListener());
        paneLibrary.addMouseListener(handler.new CarouselPaneMouseListener());

        paneNet.addMouseListener(handler.new CarouselPaneMouseListener());
        carousel.
                addMouseWheelListener(
                handler.new carouselPaneMouseWheelListener());

        //Carousel Buttons

        btnCarouselLeft = new aButton("Aurora_left_normal.png",
                "Aurora_left_down.png", "Aurora_left_over.png",
                carouselButtonWidth, carouselButtonHeight);
        btnCarouselLeft.addActionListener(handler.new LeftListener());
        btnCarouselLeft.addKeyListener(handler.new CarouselKeyListener());

        btnCarouselRight = new aButton("Aurora_right_normal.png",
                "Aurora_right_down.png", "Aurora_right_over.png",
                carouselButtonWidth, carouselButtonHeight);
        btnCarouselRight.addActionListener(handler.new RightListener());
        btnCarouselRight.addKeyListener(handler.new CarouselKeyListener());

        //Info Bar

        infoFeed = new aInfoFeed("InfoBar.png", infobarWidth,
                infobarHeight, createFeed(null));
        infoFeed.go();

        pnlInfoFeed = new JPanel(new BorderLayout());
        pnlInfoFeed.add(infoFeed, BorderLayout.SOUTH);
        pnlInfoFeed.setOpaque(false);
        
        coreUI.getCenterPanel().add(BorderLayout.CENTER, carousel);

        coreUI.getCenterFromBottomPanel().add(BorderLayout.EAST, btnCarouselRight);
        coreUI.getCenterFromBottomPanel().add(infoFeed, BorderLayout.CENTER);
        coreUI.getCenterFromBottomPanel().add(BorderLayout.WEST, btnCarouselLeft);

        //Load GameCover Cover
        if (randomGame != null) {
            try {
                randomGame.update();
            } catch (MalformedURLException ex) {
                Logger.getLogger(DashboardUI.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            randomGame.removeInteraction();
            randomGame.getInteractivePane().
                    addMouseListener(handler.new CarouselLibraryMouseListener());
        }

        //Finished loading so change text
        coreUI.getInfoLabel().setText(" Dashboard ");

        //Finalize
        coreUI.getFrame().getContentPane().
                addKeyListener(handler.new CarouselKeyListener());
        coreUI.getFrame().addKeyListener(handler.new CarouselKeyListener());
        coreUI.getBackgroundImagePane().
        		addKeyListener(handler.new CarouselKeyListener());

        coreUI.getFrame().repaint();
        coreUI.getFrame().requestFocus();

    }

    /**
     * .-----------------------------------------------------------------------.
     * | createFeed(ArrayList<String>) --> ArrayList <String>
     * .-----------------------------------------------------------------------.
     * |
     * | This method takes an array and fills it up with field for the info
     * | feed to output.
     * |
     * | An ArrayList which should contain nothing is required and in the output
     * | An ArrayList filled with latest info is given. This ArrayList should go
     * | into the InfoFeed component.
     * |
     * | If No ArrayList is provided (null) then this method will be super smart
     * | and not crash and totally be nice by creating one for you then
     * | offering it to you filled with sweet info totally for free
     * |
     * .........................................................................
     *
     * @param array ArrayList
     * <p/>
     * @return an ArrayList with info
     */
    private ArrayList<String> createFeed(final ArrayList<String> array) {

        ArrayList<String> Array = null;

        if (array == null) {
            Array = new ArrayList<String>();
        } else {
            Array = array;
        }

        Array.add(coreUI.getVi().VI(aXAVI.inx_Welcome) + ", ");
        Array.
                add(
                "How are you doing Today " + coreUI.getVi().VI(aXAVI.inx_User) +
                " We hope you enjoy this Alpha release of the Aurora Game Manager");
        Array.add("Make Sure You Check out the Improved Game Library!");
        Array.add("It can totally do stuff now!");
        Array.add("It only took a year or so...");
        Array.add("Checkout our website at auroragm.sourceforge.net ");
        Array.add("Please feel free to contact me personally via e-mail");
        Array.add("> sguergachi@gmail.com < ");
        Array.add("Let me know if you find any bugs ");
        Array.add("I will personally attend to it that it is exterminated");
        Array.add("You can also contact me regarding feedback ");
        Array.add(
                "I will feed on your feedback, if you know what i'm saying ");
        Array.add(
                "Just so you know, I didn't put this bar here to annoy you ");
        Array.
                add(
                "I plan on having it show you a live feed of breaking gaming news ");
        Array.add("As well as tracking information from your profile ");
        Array.add("When ever the heck that gets done... ");
        Array.add("Its gonna be awesome and super useful, I promise ");
        Array.
                add(
                "Just to demonstrate how useful it's going to be, why don't I teach you the alphabet? ");
        Array.add("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ");
        Array.add("There you go, I just thought you the ABCs! ");
        Array.
                add(
                "Anyways, you don't have to hang around here, just click on the library to start! ");
        Array.
                add(
                "Just press Enter, or, Move your mouse and click on the big thing that says 'Library' ");
        Array.add("I can do this all day ");
        Array.add("And all night ");
        Array.add("Ohh, Breaking News! ");
        Array.add("There is a new Call of Duty game coming out Next Year!!");
        Array.add("Totally did not see that comming...");
        Array.add("I wonder what's going to be in it");
        Array.
                add(
                "I'm going to guess it has something to do with shooting guys with guns");
        Array.
                add(
                "Well, i'm tired, keep checking the Sourceforge page for new updates");
        Array.add("Have fun!");

        return Array;
    }

    private void setSizes() {

        int Ratio = (coreUI.getFrame().getHeight() / coreUI.getFrame().
                getWidth());

        if (coreUI.isLargeScreen()) {
            topHeight = coreUI.getCenterPanel().getHeight() / 8;
            btnLogoutWidth = 0;
            btnLogoutHeight = 0;
            carouselWidth = (int) (coreUI.getFrame().getWidth() / 42) * 16;
            carouselHeight = coreUI.getFrame().getHeight() - (coreUI.
            		getFrame().getWidth() / 6);
            gameCoverHeight = carouselHeight - (2 * carouselHeight / 6);
            gameCoverWidth = (int) carouselWidth - (int) (carouselWidth / 4);
            carouselImageWidth = carouselHeight - (2 * carouselHeight / 6) - (Ratio / 8);
            carouselImageHeight = (int) carouselWidth - (int) (carouselWidth / 4) - 20;
            logoHeight = topHeight / 2 + 20;
            logoWidth = coreUI.getFrame().getWidth() / 2 + 20;

            bottomPaneHeightAdjust = coreUI.getBottomPanelSize() / 2 + coreUI.
            		getFrame().getHeight() / 50 + 25;
            topPaneHeighAdjust = coreUI.getCenterPanel().getHeight() / 5 - Ratio / 10;
            carouselButtonWidth = coreUI.getFrame().getWidth() / 12 + 10;
            carouselButtonHeight = coreUI.getFrame().getHeight() / 15 + 10;
            infobarWidth = coreUI.getFrame().getSize().width - (carouselButtonWidth * 2 + 65);
            infobarHeight = 75;


        } else {
            topHeight = coreUI.getCenterPanel().getHeight() / 8;
            btnLogoutWidth = 30;
            btnLogoutHeight = 35;
            carouselWidth = (int) (coreUI.getFrame().getWidth() / 40) * 16;
            carouselHeight = coreUI.getFrame().getHeight() - (coreUI.
            		getFrame().getWidth() / 6);
            gameCoverHeight = carouselHeight - (2 * carouselHeight / 6);
            gameCoverWidth = (int) carouselWidth - (int) (carouselWidth / 4);
            carouselImageWidth = (int) carouselWidth - (int) (400 / 2) - (Ratio * 2);
            carouselImageHeight = (int) carouselHeight - (450 / 2) - (Ratio * 2) - 55;
            logoHeight = topHeight / 2 + 20;
            logoWidth = coreUI.getFrame().getWidth() / 2 + 20;

            bottomPaneHeightAdjust = coreUI.getBottomPanelSize() / 2 + coreUI.
            		getFrame().getHeight() / 90 + 25;
            topPaneHeighAdjust = coreUI.getCenterPanel().getHeight() / 5 - Ratio / 10;
            carouselButtonWidth = coreUI.getFrame().getWidth() / 12;
            carouselButtonHeight = coreUI.getFrame().getHeight() / 15;
            infobarWidth = coreUI.getFrame().getSize().width - (carouselButtonWidth * 2 + 60);
            infobarHeight = carouselButtonHeight - bottomPaneHeightAdjust / 18;


            System.out.println("WIDTH " + carouselWidth);
        }

    }

    public aImage getTitleGamerGlow() {
        return titleProfileGlow;
    }

    public final StartScreenUI getStartUI() {
        return startUI;
    }

    public void setTitleGamerGlow(aImage titleGamerGlow) {
        this.titleProfileGlow = titleGamerGlow;
    }

    public aImage getTitleimgGamerNorm() {
        return titleProfileNorm;
    }

    public void setTitleimgGamerNorm(aImage titleimgGamerNorm) {
        this.titleProfileNorm = titleimgGamerNorm;
    }

    public aImage getTitleSettingGlow() {
        return titleSettingGlow;
    }

    public void setTitleSettingGlow(aImage titleSettingGlow) {
        this.titleSettingGlow = titleSettingGlow;
    }

    public aImage getTitleSettingNorm() {
        return titleSettingNorm;
    }

    public void setTitleSettingNorm(aImage titleSettingNorm) {
        this.titleSettingNorm = titleSettingNorm;
    }

    public aImage getTitleLibraryGlow() {
        return titleLibraryGlow;
    }

    public void setTitleLibraryGlow(aImage titleLibraryGlow) {
        this.titleLibraryGlow = titleLibraryGlow;
    }

    public aImage getTitleLibraryNorm() {
        return titleLibraryNorm;
    }

    public void setTitleLibraryNorm(aImage titleLibraryNorm) {
        this.titleLibraryNorm = titleLibraryNorm;
    }

    public aImage getTitleAuroraNetGlow() {
        return titleAuroraNetGlow;
    }

    public void setTitleAuroraNetGlow(aImage titleAuroraNetGlow) {
        this.titleAuroraNetGlow = titleAuroraNetGlow;
    }

    public aImage getImgProfile() {
        return icoProfile;
    }

    public void setImgProfile(aImage imgProfile) {
        this.icoProfile = imgProfile;
    }

    public aImage getImgSetting() {
        return icoSetting;
    }

    public void setImgSetting(aImage imgSetting) {
        this.icoSetting = imgSetting;
    }

    public aImagePane getImgGame() {
        return icoLibrary;
    }

    public void setImgGame(aImagePane imgGame) {
        this.icoLibrary = imgGame;
    }

    public aImage getImgNet() {
        return icoNet;
    }

    public void setImgNet(aImage imgNet) {
        this.icoNet = imgNet;
    }

    public aImage getImgKeyIco() {
        return keyArrows;
    }

    public void setImgKeyIco(aImage imgKeyIco) {
        this.keyArrows = imgKeyIco;
    }

    public JLabel getLblKeyAction() {
        return lblKeyAction;
    }

    public void setLblKeyAction(JLabel lblKeyAction) {
        this.lblKeyAction = lblKeyAction;
    }

    public aInfoFeed getInfo() {
        return infoFeed;
    }

    public void setInfo(aInfoFeed info) {
        this.infoFeed = info;
    }

    public ArrayList<String> getInfoArray() {
        return infoArray;
    }

    public void setInfoArray(ArrayList<String> infoArray) {
        this.infoArray = infoArray;
    }

    public int getBtnLogoutWidth() {
        return btnLogoutWidth;
    }

    public void setBtnLogoutWidth(int btnLogoutWidth) {
        this.btnLogoutWidth = btnLogoutWidth;
    }

    public int getBtnLogoutHeight() {
        return btnLogoutHeight;
    }

    public void setBtnLogoutHeight(int btnLogoutHeight) {
        this.btnLogoutHeight = btnLogoutHeight;
    }

    public double getCarouselWidth() {
        return carouselWidth;
    }

    public void setCarouselWidth(double carouselWidth) {
        this.carouselWidth = carouselWidth;
    }

    public int getCarouselHeight() {
        return carouselHeight;
    }

    public void setCarouselHeight(int carouselHeight) {
        this.carouselHeight = carouselHeight;
    }

    public int getGameCoverHeight() {
        return gameCoverHeight;
    }

    public void setGameCoverHeight(int gameCoverHeight) {
        this.gameCoverHeight = gameCoverHeight;
    }

    public int getGameCoverWidth() {
        return gameCoverWidth;
    }

    public void setGameCoverWidth(int gameCoverWidth) {
        this.gameCoverWidth = gameCoverWidth;
    }

    public int getCarouselImageWidth() {
        return carouselImageWidth;
    }

    public void setCarouselImageWidth(int carouselImageWidth) {
        this.carouselImageWidth = carouselImageWidth;
    }

    public int getCarouselImageHeight() {
        return carouselImageHeight;
    }

    public void setCarouselImageHeight(int carouselImageHeight) {
        this.carouselImageHeight = carouselImageHeight;
    }

    public int getImageHeight() {
        return logoHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.logoHeight = imageHeight;
    }

    public int getInfobarWidth() {
        return infobarWidth;
    }

    public void setInfobarWidth(int infobarWidth) {
        this.infobarWidth = infobarWidth;
    }

    public int getInfobarHeight() {
        return infobarHeight;
    }

    public void setInfobarHeight(int infobarHeight) {
        this.infobarHeight = infobarHeight;
    }

    public int getFramePadding() {
        return framePadding;
    }

    public void setFramePadding(int framePadding) {
        this.framePadding = framePadding;
    }

    public int getBottomPaneHeightAdjust() {
        return bottomPaneHeightAdjust;
    }

    public void setBottomPaneHeightAdjust(int bottomPaneHeightAdjust) {
        this.bottomPaneHeightAdjust = bottomPaneHeightAdjust;
    }

    public int getTopPaneHeighAdjust() {
        return topPaneHeighAdjust;
    }

    public void setTopPaneHeighAdjust(int topPaneHeighAdjust) {
        this.topPaneHeighAdjust = topPaneHeighAdjust;
    }

    public aCarouselTitle getTitleGamer() {
        return titleProfile;
    }

    public void setTitleGamer(aCarouselTitle titleGamer) {
        this.titleProfile = titleGamer;
    }

    public aCarouselTitle getTitleSetting() {
        return titleSetting;
    }

    public void setTitleSetting(aCarouselTitle titleSetting) {
        this.titleSetting = titleSetting;
    }

    public aCarouselTitle getTitleLibrary() {
        return titleLibrary;
    }

    public void setTitleLibrary(aCarouselTitle titleLibrary) {
        this.titleLibrary = titleLibrary;
    }

    public aCarouselTitle getTitleAuroraNet() {
        return titleAuroraNet;
    }

    public void setTitleAuroraNet(aCarouselTitle titleAuroraNet) {
        this.titleAuroraNet = titleAuroraNet;
    }

    public aCarouselPane getLibraryPane() {
        return paneLibrary;
    }

    public void setLibraryPane(aCarouselPane libraryPane) {
        this.paneLibrary = libraryPane;
    }

    public aCarouselPane getSettingsPane() {
        return paneSettings;
    }

    public void setSettingsPane(aCarouselPane settingsPane) {
        this.paneSettings = settingsPane;
    }

    public aCarouselPane getProfilePane() {
        return paneProfile;
    }

    public void setProfilePane(aCarouselPane profilePane) {
        this.paneProfile = profilePane;
    }

    public aCarouselPane getAuroraNetPane() {
        return paneNet;
    }

    public void setAuroraNetPane(aCarouselPane auroraNetPane) {
        this.paneNet = auroraNetPane;
    }

    public StartLoader getLoader() {
        return loader;
    }

    public void setLoader(StartLoader loader) {
        this.loader = loader;
    }

    public aImage getTitleAuroraNetNorm() {
        return titleAuroraNetNorm;
    }

    public void setTitleAuroraNetNorm(aImage titleAuroraNetNorm) {
        this.titleAuroraNetNorm = titleAuroraNetNorm;
    }

    public int getTopHeight() {
        return topHeight;
    }

    public void setTopHeight(int topHeight) {
        this.topHeight = topHeight;
    }

    public int getImageWidth() {
        return logoWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.logoWidth = imageWidth;
    }

    public int getCarouselButtonWidth() {
        return carouselButtonWidth;
    }

    public void setCarouselButtonWidth(int carouselButtonWidth) {
        this.carouselButtonWidth = carouselButtonWidth;
    }

    public int getCarouselButtonHeight() {
        return carouselButtonHeight;
    }

    public void setCarouselButtonHeight(int carouselButtonHeight) {
        this.carouselButtonHeight = carouselButtonHeight;
    }

    public JPanel getPnlInfo() {
        return pnlInfoFeed;
    }

    public void setPnlInfo(JPanel pnlInfo) {
        this.pnlInfoFeed = pnlInfo;
    }

    public aCarousel getCarousel() {
        return carousel;
    }

    public aurora.V1.core.Game getGame() {
        return randomGame;
    }

    public aButton getBtnLogout() {
        return btnBack;
    }

    public AuroraStorage getStorage() {
        return storage;
    }

    public AuroraCoreUI getUi() {
        return coreUI;
    }

    public aButton getBtnCarouselLeft() {
        return btnCarouselLeft;
    }

    public aButton getBtnCarouselRight() {
        return btnCarouselRight;
    }

    public AuroraCoreUI getCoreUI() {
        return this.coreUI;
    }
}