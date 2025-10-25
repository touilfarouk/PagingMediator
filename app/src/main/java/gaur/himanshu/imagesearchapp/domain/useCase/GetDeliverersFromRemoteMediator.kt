package gaur.himanshu.imagesearchapp.domain.useCase

import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import javax.inject.Inject

/**
 * Use case for retrieving deliverers using RemoteMediator pattern
 * Searches API and caches results in local database for offline access
 * Provides seamless online/offline experience
 */
class GetDeliverersFromRemoteMediator @Inject constructor(
    private val delivererRepository: DelivererRepository
) {

    operator fun invoke(name:String) = delivererRepository.getRemoteMediatorDeliverers(name)
}