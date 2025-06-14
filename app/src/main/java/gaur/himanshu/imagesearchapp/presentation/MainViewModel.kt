package gaur.himanshu.imagesearchapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import gaur.himanshu.imagesearchapp.domain.useCase.GetDeliverersFromRemoteMediator
import gaur.himanshu.imagesearchapp.domain.useCase.GetDeliverersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetDeliverersUseCase,
    private val remoteMediator: GetDeliverersFromRemoteMediator
) : ViewModel() {

    private val _query = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val deliverers: Flow<PagingData<Deliverer>> = _query
        .filter { it.isNotBlank() }
        .debounce(1000)
        .flatMapLatest { query ->
            remoteMediator.invoke(query)
                .onEach { pagingData ->
                    Log.d("MainViewModel", "New paging data received for query: $query")
                    // You can't directly inspect PagingData here, but this confirms data is flowing
                }
        }
        .cachedIn(viewModelScope)

    fun updateQuery(name: String) {
        _query.update { name }
    }
}
