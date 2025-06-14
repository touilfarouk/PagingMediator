package gaur.himanshu.imagesearchapp.data.mappers

import gaur.himanshu.imagesearchapp.data.model.local.entities.DelivererEntity
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import java.util.UUID
import javax.inject.Inject

class DelivererEntityToDelivererMapper @Inject constructor() : Mapper<DelivererEntity, Deliverer> {
    override fun map(from: DelivererEntity): Deliverer {
        return Deliverer(
            id = from.id,
            name = from.name,
            uuid = UUID.randomUUID().toString()
        )
    }
}