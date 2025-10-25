package gaur.himanshu.imagesearchapp.data.mappers

import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import javax.inject.Inject

/**
 * Mapper for converting database entities to domain models
 * Transforms DelivererEntity (data layer) to Deliverer (domain layer)
 * Used when retrieving cached data from local database
 */
class DelivererEntityToDelivererMapper @Inject constructor() : Mapper<DelivererEntity, Deliverer> {

    /**
     * Maps database entity to domain model
     * @param from DelivererEntity from database
     * @return Deliverer domain model
     */
    override fun map(from: DelivererEntity): Deliverer {
        return Deliverer(
            id = from.id,
            name = from.name
        )
    }
}