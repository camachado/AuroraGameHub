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

import aurora.V1.core.Aurora_GameLibrary.MoveToGirdLastGrid;
import aurora.engine.V1.Logic.aFileManager;
import aurora.engine.V1.UI.aButton;
import aurora.engine.V1.UI.aGridPanel;
import aurora.engine.V1.UI.aHoverButton;
import aurora.engine.V1.UI.aImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Sammy
 */
public class InputHandler {

    private aButton removeSearchButton;

    public InputHandler() {

        removeSearchButton = new aButton("SearchButtonBack_up.png", "SearchButtonBack_down.png", "SearchButtonBack_over.png");
        removeSearchButton.setPreferredSize(new Dimension(70, 51));

    }

    class RemoveSearchHandler implements ActionListener {

        private final Aurora_GameLibrary Library;
        private final JTextField SearchBar;
        private final aButton SearchButton;
        private final GridSearch Search;

        public RemoveSearchHandler(Aurora_GameLibrary Obj_library) {
            this.Library = Obj_library;
            this.SearchBar = Library.getSearchBar();
            this.SearchButton = Library.getSearchButton();
            this.Search = Library.getSearch();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Search.restoreGrid();
            } catch (MalformedURLException ex) {
                Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            Search.resetAppendedName();
            SearchBar.setText("Start Typing To Search...");
            Library.getSearchBar().setForeground(Color.darkGray);
            Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 40));
            Library.getSearchBarBG().setImage("SearchBar_inactive.png");
            Library.getSearchButtonBG().removeAll();
            Library.getSearchButtonBG().add(Library.getSearchButton(), BorderLayout.NORTH);
            Library.ui.getFrame().requestFocus();
            Library.getGameBack().revalidate();
        }
    }

    //////Search Library Bar//////////
    ///What to do if Click on Search Box
    //TODO add aCarousel Handlers
    class searchSelectHandler implements MouseListener {

        private GridSearch Search;
        private Aurora_GameLibrary Library;

        public searchSelectHandler(Aurora_GameLibrary Obj_library) {
            this.Library = Obj_library;
            this.Search = Library.getSearch();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (Library.getSearchBar().getText().equals("Start Typing To Search...")) {
                Library.getSearchBar().setText("");
                Library.getSearchBar().setForeground(Color.darkGray);
                Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 44));
                Library.getSearchBarBG().setImage("SearchBar.png");
                Library.getSearchButtonBG().removeAll();
                Library.getSearchButtonBG().add(removeSearchButton, BorderLayout.NORTH);
                Search.resetAppendedName();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class searchButtonHandler implements ActionListener {
        //Handles the Search Button Besides the Search Box

        private Aurora_GameLibrary Library;
        private GridSearch Search;

        public searchButtonHandler(Aurora_GameLibrary Obj_library) {
            this.Library = Obj_library;
            this.Search = Library.getSearch();
        }

        @Override
        //Simply Requests focus and resets append string
        public void actionPerformed(ActionEvent e) {

            Library.getSearchBar().requestFocus();
            Library.getSearchBar().setText("");
            Library.getSearchBar().setForeground(Color.darkGray);
            Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 44));
            Library.getSearchBarBG().setImage("SearchBar.png");
            Library.getSearchButtonBG().removeAll();
            Library.getSearchButtonBG().add(removeSearchButton, BorderLayout.NORTH);
            removeSearchButton.addActionListener(new RemoveSearchHandler(Library));
        }
    }

    class searchLostFocusHandler implements FocusListener {

        private Aurora_GameLibrary Library;
        private GridSearch Search;
        private JTextField SearchBar;

        public searchLostFocusHandler(Aurora_GameLibrary Obj_library) {
            this.Library = Obj_library;
            this.Search = Library.getSearch();
            this.SearchBar = Library.getSearchBar();
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            Library.getSearchBarBG().setImage("SearchBar.png");
        }
    }

    class searchFocusHandler implements FocusListener {

        private JTextField SearchBar;
        private JButton SearchButton;
        private Aurora_GameLibrary Library;
        private final GridSearch Search;

        public searchFocusHandler(Aurora_GameLibrary Obj_library) {
            this.Library = Obj_library;
            this.SearchBar = Library.getSearchBar();
            this.SearchButton = Library.getSearchButton();
            this.Search = Library.getSearch();

        }

        @Override
        //If Focus was not gained thru the search button, then 
        //reset text and append string
        public void focusGained(FocusEvent e) {
            if (Library.getSearchBar().getText().equals("Start Typing To Search...")) {
                if (e.getOppositeComponent() == SearchButton) {
                    SearchBar.setText("");
                    Search.resetAppendedName();
                    Library.getSearchBar().setForeground(Color.darkGray);
                    Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 44));
                    Library.getSearchBarBG().setImage("SearchBar.png");
                    Library.getSearchButtonBG().removeAll();
                    Library.getSearchButtonBG().add(removeSearchButton, BorderLayout.NORTH);
                    removeSearchButton.addActionListener(new RemoveSearchHandler(Library));
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e) {

            if (Library.getSearchBar().getText().equals("")) {

                //Make sure Search button had no effect
                if (e.getOppositeComponent() != SearchButton) {
                    //if focus lost then searches thru all Grid Panels, then inside each grid
                    try {
                        for (int i = 0; i < Search.getGridManager().getArray().size(); i++) {
                            for (int j = 0; j < Search.getGridManager().getGrid(i).getArray().size(); j++) {
                                //If the focus was not lost due to a GameCover Obj in the Search Grid

                                if (e.getOppositeComponent() instanceof GamePlaceholder) {
                                    if (e.getOppositeComponent() != (Game) Search.getGridManager().getGrid(i).getArray().get(j)) {
                                        System.out.println(e.getOppositeComponent());
                                        //Attempt to restore to GameCover Library Grid
                                        try {
                                            Library.getSearch().restoreGrid();
                                        } catch (MalformedURLException ex) {
                                            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        //reset Search Box and append string
                                        Library.getSearch().resetAppendedName();

                                    }
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        for (int i = 0; i < Library.getGridSplit().getArray().size(); i++) {
                            for (int j = 0; j < Library.getGridSplit().getGrid(i).getArray().size(); j++) {
                                //If the focus was not lost due to a GameCover Obj in the Search Grid

                                if (e.getOppositeComponent() instanceof GamePlaceholder) {
                                    if (e.getOppositeComponent() != (Game) Library.getGridSplit().getGrid(i).getArray().get(j)) {
                                        System.out.println(e.getOppositeComponent());
                                        //Attempt to restore to GameCover Library Grid
                                        try {
                                            Library.getSearch().restoreGrid();
                                        } catch (MalformedURLException exx) {
                                            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, exx);
                                        }
                                        //reset Search Box and append string
                                        Library.getSearch().resetAppendedName();

                                    }
                                }
                            }
                        }

                    }

                    SearchBar.setText("Start Typing To Search...");
                    Library.getSearchBar().setForeground(Color.darkGray);
                    Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 40));
                    Library.getSearchBarBG().setImage("SearchBar_inactive.png");
                    Library.getSearchButtonBG().removeAll();
                    Library.getSearchButtonBG().add(Library.getSearchButton(), BorderLayout.NORTH);
                }
            }
        }
    }

    class searchBoxHandler implements KeyListener {
        //Handles Typing In Search Box, when it is in focus

        private Aurora_GameLibrary Library;
        private GridSearch Search;

        public searchBoxHandler(Aurora_GameLibrary Obj_Library) {
            this.Library = Obj_Library;
            this.Search = Library.getSearch();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //this activates for any letter number or space key

            Library.getSearchBar().setForeground(Color.darkGray);
            Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 44));
            Library.getSearchBarBG().setImage("SearchBar.png");
            if (!Library.isAddGameUI_Visible()) {
                if (e.getKeyCode() == KeyEvent.VK_A
                        || e.getKeyCode() == KeyEvent.VK_B
                        || e.getKeyCode() == KeyEvent.VK_C
                        || e.getKeyCode() == KeyEvent.VK_D
                        || e.getKeyCode() == KeyEvent.VK_E
                        || e.getKeyCode() == KeyEvent.VK_F
                        || e.getKeyCode() == KeyEvent.VK_G
                        || e.getKeyCode() == KeyEvent.VK_H
                        || e.getKeyCode() == KeyEvent.VK_I
                        || e.getKeyCode() == KeyEvent.VK_J
                        || e.getKeyCode() == KeyEvent.VK_K
                        || e.getKeyCode() == KeyEvent.VK_L
                        || e.getKeyCode() == KeyEvent.VK_M
                        || e.getKeyCode() == KeyEvent.VK_N
                        || e.getKeyCode() == KeyEvent.VK_O
                        || e.getKeyCode() == KeyEvent.VK_P
                        || e.getKeyCode() == KeyEvent.VK_Q
                        || e.getKeyCode() == KeyEvent.VK_R
                        || e.getKeyCode() == KeyEvent.VK_S
                        || e.getKeyCode() == KeyEvent.VK_T
                        || e.getKeyCode() == KeyEvent.VK_U
                        || e.getKeyCode() == KeyEvent.VK_V
                        || e.getKeyCode() == KeyEvent.VK_W
                        || e.getKeyCode() == KeyEvent.VK_X
                        || e.getKeyCode() == KeyEvent.VK_Y
                        || e.getKeyCode() == KeyEvent.VK_Z
                        || e.getKeyCode() == KeyEvent.VK_SPACE
                        || e.getKeyCode() == KeyEvent.VK_1
                        || e.getKeyCode() == KeyEvent.VK_2
                        || e.getKeyCode() == KeyEvent.VK_3
                        || e.getKeyCode() == KeyEvent.VK_4
                        || e.getKeyCode() == KeyEvent.VK_5
                        || e.getKeyCode() == KeyEvent.VK_6
                        || e.getKeyCode() == KeyEvent.VK_7
                        || e.getKeyCode() == KeyEvent.VK_8
                        || e.getKeyCode() == KeyEvent.VK_9
                        || e.getKeyCode() == KeyEvent.VK_0
                        || e.getKeyCode() == KeyEvent.VK_QUOTE) {
                    Search.typedChar(e.getKeyChar()); //Sends the key to the search engine to be appended and check for match

                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    // If backspace is pressed tell search engine to search for name - 1 character
                    Search.removeChar(e.getKeyChar());

                }
            }
        }
    }

    class searchRefocusListener implements KeyListener {
        //Handles When User Starts Typing While Components other than the
        //Search Box are in focus.
        //Must get first key typed and put it in the searchbox
        //Then set focus to the searchbox

        private JTextField SearchBar;
        private final Aurora_GameLibrary Library;
        private final GridSearch Search;

        public searchRefocusListener(Aurora_GameLibrary Obj_Library) {
            this.Library = Obj_Library;
            this.SearchBar = Library.getSearchBar();
            this.Search = Library.getSearch();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //pressing any Number or Letter can activate this
            if (!Library.isAddGameUI_Visible()) {
                if (//e.getKeyCode() == KeyEvent.VK_A
                        e.getKeyCode() == KeyEvent.VK_B
                        || e.getKeyCode() == KeyEvent.VK_C
                        // || e.getKeyCode() == KeyEvent.VK_D
                        || e.getKeyCode() == KeyEvent.VK_E
                        || e.getKeyCode() == KeyEvent.VK_F
                        || e.getKeyCode() == KeyEvent.VK_G
                        || e.getKeyCode() == KeyEvent.VK_H
                        || e.getKeyCode() == KeyEvent.VK_I
                        || e.getKeyCode() == KeyEvent.VK_J
                        || e.getKeyCode() == KeyEvent.VK_K
                        || e.getKeyCode() == KeyEvent.VK_L
                        || e.getKeyCode() == KeyEvent.VK_M
                        || e.getKeyCode() == KeyEvent.VK_N
                        || e.getKeyCode() == KeyEvent.VK_O
                        || e.getKeyCode() == KeyEvent.VK_P
                        || e.getKeyCode() == KeyEvent.VK_Q
                        || e.getKeyCode() == KeyEvent.VK_R
                        // || e.getKeyCode() == KeyEvent.VK_S
                        || e.getKeyCode() == KeyEvent.VK_T
                        || e.getKeyCode() == KeyEvent.VK_U
                        || e.getKeyCode() == KeyEvent.VK_V
                        // || e.getKeyCode() == KeyEvent.VK_W
                        || e.getKeyCode() == KeyEvent.VK_X
                        || e.getKeyCode() == KeyEvent.VK_Y
                        || e.getKeyCode() == KeyEvent.VK_Z
                        || e.getKeyCode() == KeyEvent.VK_1
                        || e.getKeyCode() == KeyEvent.VK_2
                        || e.getKeyCode() == KeyEvent.VK_3
                        || e.getKeyCode() == KeyEvent.VK_4
                        || e.getKeyCode() == KeyEvent.VK_5
                        || e.getKeyCode() == KeyEvent.VK_6
                        || e.getKeyCode() == KeyEvent.VK_7
                        || e.getKeyCode() == KeyEvent.VK_8
                        || e.getKeyCode() == KeyEvent.VK_9
                        || e.getKeyCode() == KeyEvent.VK_0
                        || e.getKeyCode() == KeyEvent.VK_QUOTE) {

                    SearchBar.setText(String.valueOf(e.getKeyChar())); //Set first character of Search Box to the key typed 
                    Search.resetAppendedName();//Clear appended text if there is anything still in there
                    Search.clearGameGrid(); //clear and prep for search mode
                    Search.typedChar(e.getKeyChar()); // Pass to search engine first character
                    SearchBar.requestFocus(); // Get focus of Search Box

                    Library.getSearchBar().setForeground(Color.darkGray);
                    Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 44));
                    Library.getSearchBarBG().setImage("SearchBar.png");
                    Library.getSearchButtonBG().removeAll();
                    Library.getSearchButtonBG().add(removeSearchButton, BorderLayout.NORTH);
                    removeSearchButton.addActionListener(new RemoveSearchHandler(Library));
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////
    class addGameSearchBoxHandler implements KeyListener {
        //Handles Typing In Search Box, when it is in focus

        private Aurora_GameLibrary Library;
        private GameSearch Search;

        public addGameSearchBoxHandler(Aurora_GameLibrary Obj_Library) {
            this.Library = Obj_Library;
            this.Search = Library.getGameSearch();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //this activates for any letter number or space key

            Library.getSearchBar().setForeground(Color.darkGray);
            Library.getSearchBar().setFont(Library.ui.getDefaultFont().deriveFont(Font.BOLD, 44));
            Library.getSearchBarBG().setImage("SearchBar.png");

            if (e.getKeyCode() == KeyEvent.VK_A
                    || e.getKeyCode() == KeyEvent.VK_B
                    || e.getKeyCode() == KeyEvent.VK_C
                    || e.getKeyCode() == KeyEvent.VK_D
                    || e.getKeyCode() == KeyEvent.VK_E
                    || e.getKeyCode() == KeyEvent.VK_F
                    || e.getKeyCode() == KeyEvent.VK_G
                    || e.getKeyCode() == KeyEvent.VK_H
                    || e.getKeyCode() == KeyEvent.VK_I
                    || e.getKeyCode() == KeyEvent.VK_J
                    || e.getKeyCode() == KeyEvent.VK_K
                    || e.getKeyCode() == KeyEvent.VK_L
                    || e.getKeyCode() == KeyEvent.VK_M
                    || e.getKeyCode() == KeyEvent.VK_N
                    || e.getKeyCode() == KeyEvent.VK_O
                    || e.getKeyCode() == KeyEvent.VK_P
                    || e.getKeyCode() == KeyEvent.VK_Q
                    || e.getKeyCode() == KeyEvent.VK_R
                    || e.getKeyCode() == KeyEvent.VK_S
                    || e.getKeyCode() == KeyEvent.VK_T
                    || e.getKeyCode() == KeyEvent.VK_U
                    || e.getKeyCode() == KeyEvent.VK_V
                    || e.getKeyCode() == KeyEvent.VK_W
                    || e.getKeyCode() == KeyEvent.VK_X
                    || e.getKeyCode() == KeyEvent.VK_Y
                    || e.getKeyCode() == KeyEvent.VK_Z
                    || e.getKeyCode() == KeyEvent.VK_SPACE
                    || e.getKeyCode() == KeyEvent.VK_1
                    || e.getKeyCode() == KeyEvent.VK_2
                    || e.getKeyCode() == KeyEvent.VK_3
                    || e.getKeyCode() == KeyEvent.VK_4
                    || e.getKeyCode() == KeyEvent.VK_5
                    || e.getKeyCode() == KeyEvent.VK_6
                    || e.getKeyCode() == KeyEvent.VK_7
                    || e.getKeyCode() == KeyEvent.VK_8
                    || e.getKeyCode() == KeyEvent.VK_9
                    || e.getKeyCode() == KeyEvent.VK_0
                    || e.getKeyCode() == KeyEvent.VK_QUOTE) {
                Search.typedChar(e.getKeyChar()); //Sends the key to the search engine to be appended and check for match

            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                // If backspace is pressed tell search engine to search for name - 1 character
                Search.removeChar(e.getKeyChar());

            }
        }
    }

    ////Add Game UI////////
    //For when you select the Textfield in the add Game UI
    class addGameMouseHandler implements MouseListener {

        private GameSearch Search;
        private Aurora_GameLibrary Library;

        public addGameMouseHandler(Aurora_GameLibrary Obj_library) {
            this.Library = Obj_library;
            Search = Library.getGameSearch();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

            if (Library.getSearchText().getText().equals("Search For Game To Add...")) {
                Library.getSearchText().requestFocus();
                Library.getSearchText().setText("");
                Search.resetCover();
                Library.getSearchText().setForeground(Color.black);
                Library.getSearchArrow().setImage("AddGame_SearchArrow_dark.png");
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class addGameFocusHandler implements FocusListener {

        private Aurora_GameLibrary Library;
        private GameSearch Search;

        public addGameFocusHandler(Aurora_GameLibrary Obj_library) {
            this.Library = Obj_library;
            Search = this.Library.getGameSearch();

        }

        @Override
        public void focusGained(FocusEvent e) {
            if (Library.getSearchText().getText().equals("Search For Game To Add...")) {
                Library.getSearchText().setText("");
                Search.resetCover();
                Library.getSearchText().setForeground(Color.black);
                Library.getSearchArrow().setImage("AddGame_SearchArrow_dark.png");
            }

        }

        @Override
        public void focusLost(FocusEvent e) {

            if (e.getOppositeComponent() instanceof JList || e.getOppositeComponent() instanceof JFileChooser == false) {
                try {
                    Library.getSearch().restoreGrid();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (Library.getSearchText().getText().length() <= 1) {
                    Library.getSearchText().setText("Search For Game To Add...");
                    Library.getSearchText().setForeground(Color.DARK_GRAY);
                    Library.getSearchArrow().setImage("AddGame_SearchArrow_light.png");
                }

            }
        }
    }

    class HideGameAddUIHandler implements ActionListener {

        private Aurora_GameLibrary library;

        public HideGameAddUIHandler(Aurora_GameLibrary library) {
            this.library = library;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            library.hideAddGameUI();
        }
    }

    class ExecutableChooserHandler implements ActionListener {

        private Aurora_GameLibrary library;
        private JFileChooser gameLocator;

        public ExecutableChooserHandler(Aurora_GameLibrary library, JFileChooser locator) {
            this.library = library;
            gameLocator = locator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (gameLocator.getSelectedFile() != null) {
                library.setCurrentPath(gameLocator.getSelectedFile().getPath());
                library.getStepTwo().setImgURl("AddGame_step2_green.png");
                library.checkNotifiers();
                System.out.println(library.getCurrentPath());
            } else {
                library.getStepTwo().setImgURl("AddGame_step2_red.png");
            }
        }
    }

    class ExecutableFilterHandler extends FileFilter {

        private AuroraCoreUI ui;

        public ExecutableFilterHandler(AuroraCoreUI ui) {
            this.ui = ui;
        }

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {

                return true;
            }

            String extension = aFileManager.getExtension(file);
            if (extension != null) {
                if (extension.equals("exe")
                        || extension.equals("app")
                        || extension.equals("lnk")) {

                    return true;
                } else {

                    return false;

                }
            } else if (ui.getOS().indexOf("nix") >= 0 || ui.getOS().indexOf("nux") >= 0) {

                return true;
            }

            return false;
        }

        @Override
        public String getDescription() {
            return "Executable Game File";
        }
    }

    class AddToLibraryHandler implements ActionListener {

        private Aurora_GameLibrary library;
        private GridManager GridSplit;
        private JPanel GameBack;
        private GameSearch GameSearch;
        private MoveToGirdLastGrid GridMove;
        private AuroraStorage storage;
        private String currentPath;

        public AddToLibraryHandler(Aurora_GameLibrary library) {
            this.library = library;
            currentPath = library.getCurrentPath();
            GridSplit = library.getGridSplit();
            GameBack = library.getGameBack();
            GameSearch = library.getGameSearch();
            GridMove = library.getGridMove();
            storage = library.getStorage();

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            GameSearch.getFoundGameCover().setGamePath(currentPath);
            GameSearch.getFoundGameCover().setCoverSize(library.getSIZE_GameCoverWidth(), library.getSIZE_GameCoverHeight());
            GameSearch.getFoundGameCover().reAddInteractive();
            if (!GridSplit.isDupicate(GameSearch.getFoundGameCover())) {
                storage.getStoredLibrary().SaveGame(GameSearch.getFoundGameCover());


            }
            GridSplit.addGame(GameSearch.getFoundGameCover());
            GridSplit.finalizeGrid(library.getAddGameHandler(), library.getSIZE_GameCoverWidth(), library.getSIZE_GameCoverHeight());
            library.hideAddGameUI();
            //reset
            GameSearch.resetCover();

            library.setCurrentIndex(GridSplit.getArray().indexOf(GameBack.getComponent(1)));
            //Transition towards to left most grid to see the game added
            GridMove.runMover();
        }
    }

    class SelectListHandler implements ListSelectionListener {

        private Aurora_GameLibrary library;
        private JList gamesList;
        private DefaultListModel listModel;
        private JTextField gameSearchBar;
        private GameSearch gameSearch;

        public SelectListHandler(Aurora_GameLibrary library) {
            this.library = library;
            gamesList = library.getGamesList();
            listModel = library.getListModel();
            gameSearchBar = library.getGameSearchBar();
            gameSearch = library.getGameSearch();
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {

            if (gamesList.getSelectedIndex() != -1) {
                System.out.println();
                String gameSelected = (String) listModel.get(gamesList.getSelectedIndex());
                gameSearchBar.setText(gameSelected);

                gameSearch.searchSpecificGame(gameSelected);
                gameSearch.setAppendedName(gameSelected);

            }
        }
    }

    class AddGameHandler implements ActionListener {

        private Aurora_GameLibrary library;

        public AddGameHandler(Aurora_GameLibrary library) {
            this.library = library;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            library.showAddGameUI();

        }
    }

    //Prevents from clicking Through the Aurora Add Game UI and select Games in the
    //Background
    class EmptyMouseHandler implements MouseListener {

        public EmptyMouseHandler() {
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
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public class HoverButtonLeft implements MouseListener {

        private Aurora_GameLibrary library;
        private AuroraCoreUI ui;
        private GridManager GridSplit;
        private JPanel GameBack;
        private aHoverButton imgGameLeft;
        private aHoverButton imgGameRight;
        private aImage imgFavorite;
        private GridAnimation GridAnimate;

        public HoverButtonLeft(Aurora_GameLibrary library, AuroraCoreUI ui) {
            this.library = library;
            this.ui = ui;
            GridSplit = library.getGridSplit();
            GameBack = library.getGameBack();
            
            
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            imgGameLeft = library.getImgGameLeft();
            imgGameRight = library.getImgGameRight();
            imgFavorite = library.getImgFavorite();
            GridAnimate = library.getGridAnimate();

            GridSplit = library.getGridSplit();



            int currentIndex;

            if (!GridAnimate.getAnimator1().isAnimating() && !GridAnimate.getAnimator2().isAnimating()) {

                ///Get The Index of The Current Panel Being Displayed
                ///Refer too GridManager array of All panels to find it
                currentIndex = GridSplit.getArray().indexOf(GameBack.getComponent(1));

                //Stop from going to far left
                if (currentIndex - 1 == -1) {
                    currentIndex = 1;
                    imgGameLeft.mouseExit();
                }


                if (currentIndex < GridSplit.getArray().size()) {


                    //Clear Panel
                    //GameBack.removeAll();
                    if (currentIndex - 1 <= 0) {
                        //Far Left Image
                        GameBack.remove(0);
                        GameBack.add(imgFavorite, BorderLayout.WEST, 0);

                    } else {
                        //Left Button
                        GameBack.remove(0);
                        GameBack.add(imgGameLeft, BorderLayout.WEST, 0);
                    }
                    //Add GameCover Covers

                    GridAnimate.moveLeft(currentIndex);

                    
                    try {
                        library.load(currentIndex - 1);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Aurora_GameLibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    GameBack.add(BorderLayout.EAST, imgGameRight);
                }

                ui.getPnlCenter().removeAll();
                ui.getPnlCenter().add(BorderLayout.CENTER, GameBack);

                GameBack.repaint();
                GameBack.revalidate();


            }
            imgGameLeft.mouseExit();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            imgGameLeft = library.getImgGameLeft();
            imgGameRight = library.getImgGameRight();
            imgFavorite = library.getImgFavorite();
            GridAnimate = library.getGridAnimate();
            GridAnimate = library.getGridAnimate();
            imgGameLeft = library.getImgGameLeft();

            if (!GridAnimate.getAnimator1().isAnimating() && !GridAnimate.getAnimator2().isAnimating()) {
                imgGameLeft.mouseHover(e);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            imgGameLeft = library.getImgGameLeft();
            imgGameRight = library.getImgGameRight();
            imgFavorite = library.getImgFavorite();
            GridAnimate = library.getGridAnimate();
            imgGameLeft.mouseExit();

        }
    }

    public class HoverButtonRight implements MouseListener {

        private Aurora_GameLibrary library;
        private AuroraCoreUI ui;
        private GridManager GridSplit;
        private JPanel GameBack;
        private aHoverButton imgGameLeft;
        private aHoverButton imgGameRight;
        private aImage imgFavorite;
        private aImage imgBlank;
        private GridAnimation GridAnimate;

        public HoverButtonRight(Aurora_GameLibrary library, AuroraCoreUI ui) {
            this.library = library;
            this.ui = ui;

            //GridSplit = library.getGridSplit();
            GameBack = library.getGameBack();
            imgGameLeft = library.getImgGameLeft();
            imgGameRight = library.getImgGameRight();
            imgFavorite = library.getImgFavorite();
            imgBlank = library.getImgBlank();
            GridAnimate = library.getGridAnimate();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            GridSplit = library.getGridSplit();

            if (!GridAnimate.getAnimator1().isAnimating() && !GridAnimate.getAnimator2().isAnimating()) {

                library.setCurrentIndex(GridSplit.getArray().indexOf(GameBack.getComponent(1)));

                if (library.getCurrentIndex() < GridSplit.getArray().size() - 1) {

                    GameBack.remove(0);
                    GameBack.add(library.getImgGameLeft(), BorderLayout.WEST, 0);

                    GameBack.add(imgGameRight, BorderLayout.EAST, 2);

                    GridAnimate.moveRight(library.getCurrentIndex());

                    //carlos
                    // GridSplit.incrementVisibleGridIndex();


                    try {
                        library.load(library.getCurrentIndex() + 1);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Aurora_GameLibrary.class.getName()).log(Level.SEVERE, null, ex);
                    }



                    //of on last Grid then dont show right arrow button
                    if (!(library.getCurrentIndex() + 1 < GridSplit.getArray().size() - 1)) {

                        GameBack.remove(library.getImgGameRight());
                        GameBack.add(imgBlank, BorderLayout.EAST, 2);
                        imgGameRight.mouseExit();
                    }
                }

                ui.getPnlCenter().removeAll();
                ui.getPnlCenter().add(BorderLayout.CENTER, library.getGameBack());

                GameBack.repaint();
                GameBack.revalidate();

            }
            imgGameRight.mouseExit();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            GridAnimate = library.getGridAnimate();
            imgGameRight = library.getImgGameRight();

            if (!GridAnimate.getAnimator1().isAnimating() && !GridAnimate.getAnimator2().isAnimating()) {
                imgGameRight.mouseHover(e);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            imgGameRight.mouseExit();
        }
    }

    //Handler for the Navigation using Keyboard
    class GameLibraryKeyListener implements KeyListener {

        private Aurora_GameLibrary library;
        private AuroraCoreUI ui;
        private GridManager GridSplit;
        private JPanel GameBack;

        public GameLibraryKeyListener(Aurora_GameLibrary library, AuroraCoreUI ui) {
            this.library = library;
            this.ui = ui;
            //GridSplit = library.getGridSplit();
            GameBack = library.getGameBack();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            int currentIndex;
            GridSplit = library.getGridSplit();

            /* get the index of the grid that is currently displayed */
            int visibleGridIndex = GridSplit.getVisibleGridIndex();
            System.out.println("Initial visible grid = " + visibleGridIndex);
            currentIndex = GridSplit.getArray().indexOf(GameBack.getComponent(1));
            library.setCurrentIndex(currentIndex);
            System.out.println("Current Grid = " + currentIndex);

            /* get the grid that is currently displayed */
            aGridPanel grid = GridSplit.getGrid(currentIndex);

            /* get an array of all the components in the grid */
            ArrayList comp = grid.getArray();

            boolean cursorMoved = false;

            Game game = null;

            boolean selectedGameFound = false;

            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {

                int i = 0;

                while (comp.size() > i && !selectedGameFound && !(comp.get(i) instanceof GamePlaceholder)) {
                    //Check for GamePlaceholder CANT MOVE THERE!
                    game = (Game) comp.get(i);

                    if (game.isSelected()) {
                        selectedGameFound = true;
                        System.out.println(game.getName() + " is selected in the library");
                        int[] columnAndRow = grid.getColumnAndRow(i + 1);
                        int col = columnAndRow[0];
                        int row = columnAndRow[1];

                        System.out.println("Col = " + columnAndRow[0]);
                        System.out.println("Row = " + columnAndRow[1]);

                        if (row > 1) {
                            System.out.println("Cursor is moving up!");
                            //Check for GamePlaceholder CANT MOVE THERE!
                            if (!(comp.get(i - 4) instanceof GamePlaceholder)) {
                                game.hideInteractiveComponents();
                                Game newGame = (Game) comp.get(i - 4);
                                GridSplit.unselectPrevious();
                                newGame.displayInteractiveComponents();
                            }

                        } else if (row == 1) {
                            //Check for GamePlaceholder CANT MOVE THERE!
                            if (!(comp.get(i + (4 * 1)) instanceof GamePlaceholder)) {
                                game.hideInteractiveComponents();
                                Game newGame = (Game) comp.get(i + (4 * 1));
                                GridSplit.unselectPrevious();
                                newGame.displayInteractiveComponents();
                            }
                        } else {
                            System.out.println("Cursor cannot move any further up!");
                        }
                    } else {
                        i++;
                    }

                }

                if (!selectedGameFound && (comp.get(0) instanceof Game)) {
                    game = (Game) comp.get(0);
                    game.displayInteractiveComponents();
                }

                //>>> MOVE DOWN
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {

                int i = 0;

                while (i < comp.size() && !selectedGameFound && !(comp.get(i) instanceof GamePlaceholder)) {
                    //Check for GamePlaceholder CANT MOVE THERE!
                    game = (Game) comp.get(i);

                    if (game.isSelected()) {
                        selectedGameFound = true;
                        System.out.println(game.getName() + " is selected in the library");
                        int[] columnAndRow = grid.getColumnAndRow(i + 1);
                        int col = columnAndRow[0];
                        int row = columnAndRow[1];

                        System.out.println("Col = " + columnAndRow[0]);
                        System.out.println("Row = " + columnAndRow[1]);

                        if (row < grid.getRow()) {
                            System.out.println("Cursor is moving down!");

                            //Check for GamePlaceholder CANT MOVE THERE!
                            if (!(comp.get(i + 4) instanceof GamePlaceholder)) {
                                game.hideInteractiveComponents();
                                Game newGame = (Game) comp.get(i + 4);
                                GridSplit.unselectPrevious();
                                newGame.displayInteractiveComponents();
                            }

                        } else if (row == grid.getRow()) {

                            //Check for GamePlaceholder CANT MOVE THERE!
                            if (!(comp.get(i - (4 * 1)) instanceof GamePlaceholder)) {
                                game.hideInteractiveComponents();
                                Game newGame = (Game) comp.get(i - (4 * 1));
                                GridSplit.unselectPrevious();
                                newGame.displayInteractiveComponents();
                            }
                        } else {
                            System.out.println("Cursor cannot move any further down!");
                        }
                    } else {
                        i++;
                    }

                }

                if (!selectedGameFound && (comp.get(0) instanceof Game)) {
                    game = (Game) comp.get(0);
                    game.displayInteractiveComponents();
                }


                //>>> MOVE LEFT
            } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {

                System.out.println("A key pressed");

                int i = 0;

                while (i < comp.size() && !selectedGameFound && !(comp.get(i) instanceof GamePlaceholder)) {
                    game = (Game) comp.get(i);
                    if (game.isSelected()) {
                        selectedGameFound = true;
                        System.out.println("index = " + i);
                        System.out.println(game.getName() + " is selected in the library");
                    } else {
                        i++;
                    }

                }

                if (!cursorMoved && selectedGameFound) {
                    int[] columnAndRow = grid.getColumnAndRow(i + 1);
                    int col = columnAndRow[0];
                    int row = columnAndRow[1];

                    System.out.println("Col = " + col);
                    System.out.println("Row = " + row);

                    // check to see if the selected game is not the first game in the grid
                    if (col > 1 || (col == 1 && row > 1)) {
                        System.out.println("Cursor is moving left!");
                        visibleGridIndex = GridSplit.getVisibleGridIndex();
                        System.out.println("visible grid after moving right = " + visibleGridIndex);
                        game.hideInteractiveComponents();
                        Game newGame = (Game) comp.get(i - 1);
                        GridSplit.unselectPrevious();
                        newGame.displayInteractiveComponents();
                        cursorMoved = true;
                    } else if (col == 1 && row == 1) {

                        if (GridSplit.getArray().indexOf(GameBack.getComponent(1)) > 0) {
                            library.moveGridLeft();
                            /* get the index of the grid that is currently displayed */
                            visibleGridIndex = GridSplit.getVisibleGridIndex();
                            System.out.println("visible grid after moving right = " + visibleGridIndex);
                            currentIndex = GridSplit.getArray().indexOf(GameBack.getComponent(1));
                            /* get the grid that is currently displayed */
                            grid = GridSplit.getGrid(currentIndex);

                            /* get an array of all the components in the grid */
                            comp = grid.getArray();

                            //Check if GamePlaceholder is to the right.
                            if (!(comp.get(comp.size() - 1) instanceof GamePlaceholder)) {
                                game.hideInteractiveComponents();
                                Game newGame = (Game) comp.get(comp.size() - 1);
                                GridSplit.unselectPrevious();
                                newGame.displayInteractiveComponents();
                            }
                        } else {
                            System.out.println("Cursor cannot move any further left!");
                        }


                    }
                } else if (!selectedGameFound && (comp.get(0) instanceof Game)) {
                    game = (Game) comp.get(0);
                    game.displayInteractiveComponents();
                }

                // >>> MOVE RIGHT
            } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {

                System.out.println("D key pressed");

                int i = 0;
                //      boolean selectedGameFound = false;

                while (i < comp.size() && !selectedGameFound && !(comp.get(i) instanceof GamePlaceholder)) {
                    game = (Game) comp.get(i);
                    if (game.isSelected()) {
                        selectedGameFound = true;
                        System.out.println("index = " + i);
                        System.out.println(game.getName() + " is selected in the library");
                    } else {
                        i++;
                    }

                }

                if (!cursorMoved && selectedGameFound) {
                    int[] columnAndRow = grid.getColumnAndRow(i + 1);
                    int col = columnAndRow[0];
                    int row = columnAndRow[1];
                    System.out.println("Col = " + col);
                    System.out.println("Row = " + row);

                    // check to see if the selected is not the last game in the grid
                    if ((col < grid.getCol()
                            || (col == grid.getCol() && row < grid.getRow()))
                            && comp.size() > i + 1) {
                        System.out.println("Cursor is moving right!");
                        System.out.println(game.getName() + " is Last Game in This Grid!");

                        Game newGame;

                        // get the next object
                        Object obj = comp.get(i + 1);
                        if (obj instanceof Game) {
                            System.out.println("Object is a game");
                            //game.hideInteractiveComponents();
                            newGame = (Game) obj;
                            GridSplit.unselectPrevious();
                            newGame.displayInteractiveComponents();
                            cursorMoved = true;
                        } else {
                            System.out.println("Object is an add game icon");
                        }



                        // else check to see if the selected game is the last game in the grid
                    } else if (col == grid.getCol() && row == grid.getRow()) {
                        System.out.println("Cursor cannot move any further right! Grid needs to move right");

                        // check to see if the the current grid is the last grid
                        if (GridSplit.getVisibleGridIndex() < (GridSplit.getNumberOfGrids()) && !(comp.get(0) instanceof GamePlaceholder)) {
                            System.out.println("This is not the last grid");

                            library.moveGridRight();

                            /* get the index of the grid that is currently displayed */
                            visibleGridIndex = GridSplit.getVisibleGridIndex();
                            System.out.println("visible grid after moving right = " + visibleGridIndex);

                            currentIndex = GridSplit.getArray().indexOf(GameBack.getComponent(1));

                            /* get the grid that is currently displayed */
                            grid = GridSplit.getGrid(currentIndex);

                            /* get an array of all the components in the grid */
                            comp = grid.getArray();

                            Game newGame = (Game) comp.get(0);

                            newGame.requestFocus();
                            newGame.removePreviousSelected();
                            newGame.revalidate();
                            newGame.displayInteractiveComponents();

                        } else {
                            System.out.println("Cannot move to the grid to the right. No more grids!");
                        }

                    }
                } else if (!selectedGameFound && (comp.get(0) instanceof Game)) {
                    game = (Game) comp.get(0);
                    game.displayInteractiveComponents();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                System.out.println("D key pressed");

                int i = 0;

                while (i < comp.size() && !selectedGameFound && !(comp.get(i) instanceof GamePlaceholder)) {
                    game = (Game) comp.get(i);
                    if (game.isSelected()) {
                        selectedGameFound = true;
                        System.out.println("index = " + i);
                        System.out.println(game.getName() + " is selected in the library");
                    } else {
                        i++;
                    }

                }

                if (!cursorMoved && selectedGameFound) {
                    game.getPlayHandler().actionPerformed(null);
                }

            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                ui.showExitDilog();
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    class GridMouseWheelListener implements MouseWheelListener {

        private Aurora_GameLibrary library;
        private GridManager GridSplit;

        public GridMouseWheelListener(Aurora_GameLibrary library) {
            this.library = library;
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            int currentIndex;
            GridSplit = library.getGridSplit();

            int numberClicks = e.getWheelRotation();
            System.out.println("Mouse wheel moved " + numberClicks);

            ///Get The Index of The Current Panel Being Displayed///
            ///Refer too GridManager array of All panels to find it///
            //GameBack is the Panel Containing all the game grids///

            currentIndex = GridSplit.getArray().indexOf(library.getGameBack().getComponent(1));
            library.setCurrentIndex(currentIndex);

            if (numberClicks < 0) {
                if (currentIndex > 0) {
                    library.moveGridLeft();

                }
            } else if (numberClicks > 0) {
                if (currentIndex < (GridSplit.getNumberOfGrids() - 1)) {
                    library.moveGridRight();

                }
            }

        }
    }
}