package gaur.himanshu.imagesearchapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.imagesearchapp.presentation.ui.theme.ImageSearchAppTheme

/**
 * Main activity that displays the deliverer search interface
 * Shows all deliverers when no search query is active
 * Shows search results when user types in the search field
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // Search query state
            var query by rememberSaveable { mutableStateOf("") }
            val viewModel = hiltViewModel<MainViewModel>()

            ImageSearchAppTheme {
                Scaffold(
                    topBar = {
                        // Search input field
                        TextField(
                            value = query,
                            onValueChange = {
                                query = it
                                viewModel.updateQuery(it)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { androidx.compose.material3.Text("Search deliverers...") }
                        )
                    },
                    modifier = Modifier
                        .safeContentPadding()
                        .fillMaxSize()
                ) { innerPadding ->
                    Spacer(modifier = Modifier.height(8.dp))

                    if (query.isBlank()) {
                        // Show all deliverers from local database when no search query
                        ListDeliverers(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        // Show remote search results when user types a search query
                        SearchDeliverer(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
