package gaur.himanshu.imagesearchapp.domain.useCase

import gaur.himanshu.imagesearchapp.domain.repository.DelivererRepository
import javax.inject.Inject

/**
 * Use case for searching deliverers only in local database
 * Performs pattern matching search on cached deliverer data
 * Useful for offline search or quick local filtering
 */
class GetDeliverersFromLocalSearch @Inject constructor(
    private val delivererRepository: DelivererRepository
) {

    /**
     * Searches for deliverers in local database using pattern matching
     * @param searchName partial name to search for (supports wildcards)
     * @return Flow of paged deliverer data matching the search pattern
     */
    operator fun invoke(searchName: String) = delivererRepository.searchLocalDeliverers(searchName)
}