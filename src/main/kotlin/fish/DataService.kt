package fish

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.reactivex.Maybe
import javax.inject.Singleton

interface DataService {
    fun createMaybeObjects(count: Int): Maybe<List<Data>>
    fun createListObjects(count: Int): List<Data>
}

@Singleton
class DefaultDataService(private val repo: DataRepository) : DataService {

    override fun createMaybeObjects(count: Int): Maybe<List<Data>> {
        return Maybe.fromCallable { repo.createList(count) }
    }

    override fun createListObjects(count: Int): List<Data> {
        return Maybe.fromCallable { repo.createList(count) }.blockingGet()
    }
}

@Singleton
class DataRepository {
    private val mapper = jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun createList(num: Int): List<Data> {
        val data = mapper.readValue<Data>("bad data")
        return MutableList(num) { data }
    }
}

data class Data(
        val name: String,
        val size: Int
)
