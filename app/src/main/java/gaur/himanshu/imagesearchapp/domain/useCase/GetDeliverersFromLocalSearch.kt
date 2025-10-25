package gaur.himanshu.imagesearchapp.domain.useCase

import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import javax.inject.Inject

class GetDeliverersFromLocalSearch @Inject constructor(
    private val delivererRepository: DelivererRepository
) {

    operator fun invoke(searchName: String) = delivererRepository.searchLocalDeliverers(searchName)
}