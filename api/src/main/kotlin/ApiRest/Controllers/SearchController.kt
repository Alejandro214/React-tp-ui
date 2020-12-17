package ApiRest.Controllers

import ApiRest.JWTAccessManager
import ar.com.unqui.api.TokenJWT
import domain.NotFoundException
import domain.UNQFlix
import guia2.model.ContentViewMapper
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse

class SearchController(private val mC: MainController) {

    fun searchContentByText(ctx: Context) {
        try {
            val search: String = ctx.queryParam("text")!!
            val resultMovies = mC.toSimpleData(mC.backend.searchMovies(search).toMutableList())
            val resultSeries = mC.toSimpleData(mC.backend.searchSeries(search).toMutableList())

            val contents = mC.unionOfContent(resultMovies, resultSeries)

            ctx.status(200)
            ctx.json(ContentViewMapper(contents))
        } catch (e: NotFoundException) {
            throw NotFoundResponse(e.message.toString())
        }
    }
}