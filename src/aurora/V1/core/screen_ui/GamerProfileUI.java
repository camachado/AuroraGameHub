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

/**
 *
 * @author Sammy
 * @version 0.2
 */
public class GamerProfileUI extends AuroraApp {

    private final DashboardUI dashboardUI;

    private final AuroraCoreUI coreUI;

    public GamerProfileUI(DashboardUI dahsboardUi, AuroraCoreUI auroraCoreUi) {

        this.dashboardUI = dahsboardUi;
        this.coreUI = auroraCoreUi;
    }

    @Override
    public void loadUI() {
    }

    @Override
    public void buildUI() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSize() {
    }

    @Override
    public DashboardUI getDashboardUI() {
        return dashboardUI;
    }

    @Override
    public AuroraCoreUI getCoreUI() {
        return coreUI;
    }

    @Override
    public void addToCanvas() {

    }
}
