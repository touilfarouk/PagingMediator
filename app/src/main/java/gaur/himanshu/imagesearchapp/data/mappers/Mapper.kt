package gaur.himanshu.imagesearchapp.data.mappers

/**
 * Generic mapper interface for converting between different data types
 * Used to map between API DTOs, database entities, and domain models
 * Follows clean architecture principles for data transformation
 */
interface Mapper<From, To> {
    /**
     * Maps from one data type to another
     * @param from source object to map from
     * @return mapped object of target type
     */
    fun map(from: From): To
}

fun <F,T> Mapper<F,T>.mapAll(list:List<F>) = list.map { map(it) }