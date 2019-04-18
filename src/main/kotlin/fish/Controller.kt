package fish

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.reactivex.Maybe

@Controller("/api")
class Controller(private val service: DataService) {

    @Get("/maybe")
    fun getMaybe(@QueryValue count: Int): Maybe<List<Data>> {
        return service.createMaybeObjects(count)
    }

    @Get("/list")
    fun getList(@QueryValue count: Int): List<Data> {
        return service.createListObjects(count)
    }
}