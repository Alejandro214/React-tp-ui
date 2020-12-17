package ApiRest.Controllers

import ApiRest.JWTAccessManager
import ar.com.unqui.api.TokenJWT
import domain.*
import guia2.model.BannerRelatedData
import guia2.model.SimpleData
import guia2.model.UserController

open class MainController(val tokenJWT: TokenJWT, val backend: UNQFlix, val jwtAccessManager: JWTAccessManager) {

    val userController = UserController(this)
    val searchController = SearchController(this)
    val contentController = ContentController(this)

    // Private Functions------------------------------------------------------------------------------------------------

    fun categoriesToName(categories: MutableList<Category>): List<String> {
        return categories.map { it.name }
    }

    fun searchContentById(contentId: String?): Content {
        return if (contentId!!.startsWith("mov")) {
            backend.movies.find { it.id == contentId }
        } else {
            backend.series.find { it.id == contentId }
        } ?: throw NotFoundException("Content", "id", contentId)
    }

    open fun unionOfContent(
        contentMovie: List<SimpleData>,
        contentSerie: List<SimpleData>
    ): MutableSet<SimpleData> {
        val contents: MutableSet<SimpleData> = mutableSetOf()

        contents.addAll(contentMovie)
        contents.addAll(contentSerie)

        return contents
    }

    private fun evalBool(contentState: ContentState): Boolean {
        return contentState.toString().contains("Av")
    }

    open fun toSimpleData(lista: MutableCollection<Content>): List<SimpleData> {
        return lista.map { SimpleData(it.id, it.description, it.title, evalBool(it.state),it.poster) }
    }

    open fun toBannerData(lista: MutableCollection<Content>): List<BannerRelatedData> {
        return lista.map { BannerRelatedData(it.id, it.description, it.title, evalBool(it.state), it.poster) }
    }
}