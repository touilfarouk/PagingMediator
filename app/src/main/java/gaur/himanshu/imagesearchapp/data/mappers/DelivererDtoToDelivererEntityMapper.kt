package gaur.himanshu.imagesearchapp.data.mappers

import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.data.model.remote.dto.DelivererDto

/**
 * Mapper for converting API DTOs to database entities
 * Transforms DelivererDto (API response) to DelivererEntity (database storage)
 * Used by RemoteMediator to cache API responses locally
 */
class DelivererDtoToDelivererEntityMapper(
    private val query: String
) : Mapper<DelivererDto, DelivererEntity> {

    /**
     * Maps API DTO to database entity
     * @param from DelivererDto from API response
     * @return DelivererEntity for database storage
     * Note: query field needs to be set separately based on search context
     */
    override fun map(from: DelivererDto): DelivererEntity {
        return DelivererEntity(
            id = from.id.toString(),
            name = from.name,
            query = query
        )
    }
}
