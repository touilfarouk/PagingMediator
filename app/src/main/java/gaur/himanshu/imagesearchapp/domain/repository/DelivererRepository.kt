package gaur.himanshu.imagesearchapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import gaur.himanshu.imagesearchapp.domain.model.Deliverer
import kotlinx.coroutines.flow.Flow

interface DelivererRepository {

    fun getDeliverers(name: String):Pager<Int,Deliverer>
    fun getRemoteMediatorDeliverers(q: String): Flow<PagingData<Deliverer>>

}