package gaur.himanshu.imagesearchapp.domain.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import gaur.himanshu.imagesearchapp.data.mappers.DelivererEntityToDelivererMapper
import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case for retrieving all deliverers from local database
 * Returns all cached deliverer data with pagination support
 * Used when no search query is active
 */
class GetAllDeliverersUseCase @Inject constructor(
    private val delivererRepository: DelivererRepository,
    private val delivererEntityToDelivererMapper: DelivererEntityToDelivererMapper
) {
    operator fun invoke(): Flow<PagingData<Deliverer>> = Pager(
        PagingConfig(
            pageSize = 1,
            prefetchDistance = 1,
            enablePlaceholders = true
        )
    ) {
        delivererRepository.getAllDeliverers()
    }.flow.map { pagingData ->
        pagingData.map { delivererEntity: DelivererEntity ->
            delivererEntityToDelivererMapper.map(delivererEntity)
        }
    }
}
