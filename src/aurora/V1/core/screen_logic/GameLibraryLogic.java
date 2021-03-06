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
package aurora.V1.core.screen_logic;

import aurora.V1.core.screen_ui.GameLibraryUI;
import aurora.engine.V1.Logic.AuroraScreenHandler;
import aurora.engine.V1.Logic.AuroraScreenLogic;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class GameLibraryLogic implements AuroraScreenLogic {

    private final GameLibraryUI libraryUI;

    private AuroraScreenHandler handler;

    public GameLibraryLogic(GameLibraryUI gamelibraryUi) {
        this.libraryUI = gamelibraryUi;
    }

    @Override
    public void setHandler(AuroraScreenHandler handler) {

        this.handler = handler;

    }
}
