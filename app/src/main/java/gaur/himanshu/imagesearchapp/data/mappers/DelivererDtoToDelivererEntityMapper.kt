package gaur.himanshu.imagesearchapp.data.mappers

import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.data.model.remote.dto.DelivererDto

class DelivererDtoToDelivererEntityMapper(
    private val query: String
) : Mapper<DelivererDto, DelivererEntity> {

    override fun map(from: DelivererDto): DelivererEntity {
        return DelivererEntity(
            id = from.id.toString(),
            name = from.name,
            query = query
        )
    }
}
