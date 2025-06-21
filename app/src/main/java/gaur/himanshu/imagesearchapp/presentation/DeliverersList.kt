package gaur.himanshu.imagesearchapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import gaur.himanshu.imagesearchapp.presentation.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun DeliverersList(
    items: LazyPagingItems<Deliverer>,
    modifier: Modifier = Modifier
) {
    when (val state = items.loadState.refresh) {
        is LoadState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return
        }

        is LoadState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = { items.retry() }) {
                    Text("Retry")
                }
            }
            return
        }

        is LoadState.NotLoading -> {
            if (items.itemCount == 0) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nothing found")
                }
                return
            }
        }
    }

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items.itemCount) { index ->
            val deliverer = items[index]
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
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        if (items.loadState.append is LoadState.Loading) {
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }
            }
        }

        if (items.loadState.append is LoadState.Error) {
            item {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = { items.retry() }, modifier = Modifier.fillMaxWidth()) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}
