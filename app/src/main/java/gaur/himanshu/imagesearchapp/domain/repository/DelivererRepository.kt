package gaur.himanshu.imagesearchapp.domain.repository

import androidx.paging.Pager
import gaur.himanshu.imagesearchapp.domain.model.Deliverer

interface DelivererRepository {

    fun getDeliverers(name: String):Pager<Int,Deliverer>

}