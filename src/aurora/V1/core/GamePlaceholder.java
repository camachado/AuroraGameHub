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
import aurora.engine.V1.UI.AImagePane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Sammy
 */
public class GamePlaceholder extends AImagePane {
    //A Place Holder Component similar to the Game Component.

    private AButton button = null;
    private JPanel buttonPane;
    private int allWidth;
    private int allHeight;

    public GamePlaceholder() {
    }

    public void setUp(int Width, int Height, String BGimg) {
        this.allWidth = Width;
        this.allHeight = Height;

        this.setImage(BGimg, allHeight, allWidth);
        this.setPreferredSize(new Dimension(allWidth, allHeight));
        this.revalidate();
        this.repaint();
        this.setOpaque(false);

        this.setLayout(new BorderLayout());

    }

    public void addButton(String up, String down, String over, ActionListener handler) {
        button = new AButton(up, down, over, allWidth, allHeight);
        button.addActionListener(handler);
        buttonPane = new JPanel(new BorderLayout(0, 0)); //Contains the Add Game Button
        buttonPane.setOpaque(false);


        button.setPreferredSize(new Dimension(allWidth, allHeight));
        button.addMouseListener(new ButtonMouseListener());
        this.addMouseListener(new ButtonMouseListener());
        buttonPane.setPreferredSize(new Dimension(allWidth, allHeight));
        buttonPane.addMouseListener(new ButtonMouseListener());


        buttonPane.add(button);

        this.add(buttonPane);


        this.setPreferredSize(new Dimension(allWidth, allHeight));
        this.revalidate();
        buttonPane.revalidate();
        this.repaint();

    }

    public Boolean isContainsButton() {
        if (button != null) {
            return true;
        }
        return false;
    }

    public class ButtonMouseListener implements MouseListener {

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
            //button.setVisible(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //button.setVisible(false);
        }
    }
}
