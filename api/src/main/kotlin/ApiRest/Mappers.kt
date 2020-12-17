package guia2.model

import domain.Season

data class UserRegisterMapper (val name:String?,val email:String?,val password:String?,val image:String?,val creditCard:String?)

data class UserLogin(val email: String?,val password: String?)

data class UserViewMapper(val name:String, val image: String, val favorites: Collection<SimpleData>, val lastSeen: Collection<SimpleData>)

data class ErrorViewMapper (val result: String?, val message: String?)

data class BannerRelatedData (val id: String, val description: String, val title: String, val state: Boolean, val poster: String)

data class SimpleData (val id: String, val description: String, val title: String, val state: Boolean, val poster :String)

data class MovieViewMapper(val id: String, val title: String, val description: String, val poster: String,
                           val video: String, val duration: Int, val actors: MutableList<String>,
                           val directors: MutableList<String>, var categories: List<String>,
                           val relatedContent: List<BannerRelatedData>)

data class SerieViewMapper(val id: String, val title: String, val description: String, val poster: String,
                           val categories: List<String>, val relatedContent: List<BannerRelatedData>,
                           val seasons: MutableList<Season>)

data class IdMapperValidator(val id: String)

data class ContentViewMapper(val content: Collection<SimpleData>)

data class BannersRelatedViewMapper(val banners: Collection<BannerRelatedData>)

data class OkResultMapper(val result: String)