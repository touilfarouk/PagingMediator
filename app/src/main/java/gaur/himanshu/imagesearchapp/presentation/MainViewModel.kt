package gaur.himanshu.imagesearchapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import gaur.himanshu.imagesearchapp.domain.useCase.GetAllDeliverersUseCase
import gaur.himanshu.imagesearchapp.domain.useCase.GetDeliverersFromRemoteMediator
import gaur.himanshu.imagesearchapp.domain.useCase.GetDeliverersFromLocalSearch
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * ViewModel that manages deliverer data flows for the main screen
 * Provides three different data sources:
 * 1. All deliverers from local database (deliverersPaged)
 * 2. Remote search with local caching (deliverers)
 * 3. Local search only (localDeliverers)
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteMediator: GetDeliverersFromRemoteMediator,
    private val getAllDeliverersUseCase: GetAllDeliverersUseCase,
    private val localSearchUseCase: GetDeliverersFromLocalSearch
) : ViewModel() {

    // Search query state
    private val _query = MutableStateFlow("")

    /**
     * Flow that provides all deliverers from local database
     * Used when no search query is active
     */
    val deliverersPaged: Flow<PagingData<Deliverer>> = getAllDeliverersUseCase()
        .cachedIn(viewModelScope)

    /**
     * Remote search flow - searches API and caches results locally
     * Triggered when search query is provided
     * Includes 1-second debounce to avoid excessive API calls
     */
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val deliverers: Flow<PagingData<Deliverer>> = _query
        .filter { it.isNotBlank() }
        .debounce(1000)
        .flatMapLatest { query ->
            remoteMediator.invoke(query)
                .onEach { pagingData ->
                    Log.d("MainViewModel", "Remote search data received for query: $query")
                }
        }
        .cachedIn(viewModelScope)

    /**
     * Local search flow - searches only in local database
     * Useful for offline search or when you want to search cached data only
     * Also includes 1-second debounce for smooth user experience
     */
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val localDeliverers: Flow<PagingData<Deliverer>> = _query
        .filter { it.isNotBlank() }
        .debounce(1000)
        .flatMapLatest { query ->
            localSearchUseCase.invoke(query)
                .onEach { pagingData ->
                    Log.d("MainViewModel", "Local search data received for query: $query")
                }
        }
        .cachedIn(viewModelScope)

    /**
     * Updates the search query which triggers the search flows
     */
    fun updateQuery(name: String) {
        _query.update { name }
    }
}
