package gaur.himanshu.imagesearchapp.domain.useCase

import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import javax.inject.Inject

class GetDeliverersFromRemoteMediator @Inject constructor(
    private val delivererRepository: DelivererRepository
) {

    operator fun invoke(name:String) = delivererRepository.getRemoteMediatorDeliverers(name)
}