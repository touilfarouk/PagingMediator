package gaur.himanshu.imagesearchapp.data.mappers

import gaur.himanshu.imagesearchapp.data.model.remote.dto.DelivererDto
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import jakarta.inject.Inject

class DelivererDotToDelivererMapper  @Inject constructor() : Mapper<DelivererDto, Deliverer> {
    override fun map(from: DelivererDto): Deliverer {
        return Deliverer(
            name = from.name,
            id = from.id.toString()

        )
    }
}