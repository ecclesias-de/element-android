/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.onboarding.ftueauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import im.vector.app.core.resources.BuildMeta
import im.vector.app.databinding.FragmentTineServerSelectionBinding
import im.vector.app.features.VectorFeatures
import im.vector.app.features.settings.VectorPreferences
import javax.inject.Inject

@AndroidEntryPoint
// This is the fragment/view for tine login - the interface is defined in FragmentTineServerSelectionBinding / fragment_tine_server_selection.xml
// Every thing is boilerplate code logic it in our function (tineServerSelectSubmit) inside of viewModel and FuetAuthVariant
// have a lock at setupViews() and submit(). they implement the user interface interactions
class TineServerSelectionFragment
    : AbstractFtueAuthFragment<FragmentTineServerSelectionBinding>() {

    @Inject lateinit var vectorPreferences: VectorPreferences
    @Inject lateinit var vectorFeatures: VectorFeatures
    @Inject lateinit var buildMeta: BuildMeta

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTineServerSelectionBinding {
        return FragmentTineServerSelectionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        views.loginSubmit.debouncedClicks { submit() }
    }

    private fun submit() {
        // call submit logic
        // element would use viewModel.handle(customAction), but calling the function directly probably saves patching conflicts
        viewModel.tineServerSelectSubmit(views.loginEditText.text.toString())
    }
    override fun resetViewModel() {
        // Nothing to do
    }
}
