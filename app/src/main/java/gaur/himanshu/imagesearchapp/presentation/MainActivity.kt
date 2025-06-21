package gaur.himanshu.imagesearchapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.imagesearchapp.presentation.ui.theme.ImageSearchAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var query by rememberSaveable { mutableStateOf("") }
            val viewModel = hiltViewModel<MainViewModel>()

            ImageSearchAppTheme {
                Scaffold(
                    topBar = {
                        TextField(
                            value = query,
                            onValueChange = {
                                query = it
                                viewModel.updateQuery(it)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    modifier = Modifier
                        .safeContentPadding()
                        .fillMaxSize()
                ) { innerPadding ->
                    Spacer(modifier = Modifier.height(8.dp))

                    if (query.isBlank()) {
                        // ✅ Show local deliverers
                        ListDeliverers(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        // ✅ Show remote search deliverers
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













//
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            var query by rememberSaveable { mutableStateOf("") }
//            val viewModel = hiltViewModel<MainViewModel>()
//            ImageSearchAppTheme {
//                Scaffold(
//                    topBar = {
//                        TextField(value = query, onValueChange = {
//                            query = it
//                            viewModel.updateQuery(query)
//                        }, modifier = Modifier.fillMaxWidth())
//                    },
//                    modifier = Modifier
//                        .safeContentPadding()
//                        .fillMaxSize(),
//                ) { innerPadding ->
//                    Spacer(modifier = Modifier.height(8.dp))
//                    SearchDeliverer(modifier = Modifier.padding(innerPadding), viewModel)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    ListDeliverers(viewModel = viewModel)
//
//                }
//            }
//        }
//    }
//}
//
