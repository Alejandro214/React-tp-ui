package ApiRest.Controllers

import domain.Movie
import domain.NotFoundException
import domain.Serie
import guia2.model.*
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse

class ContentController(private val mC: MainController) {

    fun getAllContent(ctx: Context) {
        val contentMovie: List<SimpleData> = mC.toSimpleData(mC.backend.movies.toMutableList())
        val contentSerie: List<SimpleData> = mC.toSimpleData(mC.backend.series.toMutableList())

        val contents = mC.unionOfContent(contentMovie, contentSerie).sortedBy { it.title }

        ctx.status(200)
        ctx.json(ContentViewMapper(contents))
    }

    fun getContentById(ctx: Context) {
        try {
            val contentId: String = ctx.pathParam("contentId")

            if (contentId.startsWith("mov")) {
                val content: Movie = mC.searchContentById(contentId) as Movie

                ctx.status(200)
                ctx.json(
                    MovieViewMapper(
                        content.id,
                        content.title,
                        content.description,
                        content.poster,
                        content.video,
                        content.duration,
                        content.actors,
                        content.directors,
                        mC.categoriesToName(content.categories),
                        mC.toBannerData(content.relatedContent)
                    )
                )
            } else {
                val content: Serie = mC.searchContentById(contentId) as Serie

                ctx.status(200)
                ctx.json(
                    SerieViewMapper(
                        content.id,
                        content.title,
                        content.description,
                        content.poster,
                        mC.categoriesToName(content.categories),
                        mC.toBannerData(content.relatedContent),
                        content.seasons
                    )
                )
            }
        } catch (e: NotFoundException) {
            throw NotFoundResponse(e.message.toString())
        }
    }

    fun getAllBanners(ctx: Context) {
        ctx.status(200)
        ctx.json(BannersRelatedViewMapper(mC.toBannerData(mC.backend.banners)))
    }
}