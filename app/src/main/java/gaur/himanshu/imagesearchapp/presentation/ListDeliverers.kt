package gaur.himanshu.imagesearchapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

/**
 * Composable that displays all deliverers from the local database
 * Used when no search query is active - shows cached deliverer data
 * Handles loading states and pagination automatically
 */
@Composable
fun ListDeliverers(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    // Collect paged deliverers from the viewModel
    val deliverersPaged = viewModel.deliverersPaged.collectAsLazyPagingItems()

    when {
        // Show loading indicator during initial load
        deliverersPaged.loadState.refresh is LoadState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // Show empty state when no deliverers are found
        deliverersPaged.itemCount == 0 -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No deliverers found")
            }
        }

        // Show the list of deliverers
        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                // Render each deliverer item
                items(deliverersPaged.itemCount) { index ->
                    val deliverer = deliverersPaged[index]
                    if (deliverer != null) {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = deliverer.name,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Show loading indicator when loading more items
                if (deliverersPaged.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
