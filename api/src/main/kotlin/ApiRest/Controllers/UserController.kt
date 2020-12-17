package guia2.model


import ApiRest.Controllers.MainController
import ApiRest.JWTAccessManager
import ar.com.unqui.api.TokenJWT
import data.idGenerator
import domain.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse

class UserController(private val mC: MainController) {

    //Registro al usuario, lo agrego al modelo
    fun createUser(ctx: Context) {
        try {
            val newUser = ctx.bodyValidator<UserRegisterMapper>()
                .check(
                    { it.name != null && it.email != null && it.password != null && it.image != null && it.creditCard != null }
                    , "Invalid body : name , email, password, image and crediCard is required"
                )
                .get()
            val user = User(
                idGenerator.nextUserId(), newUser.name!!, newUser.creditCard!!, newUser.image!!, newUser.email!!,
                newUser.password!!, mutableListOf(), mutableListOf()
            )
            mC.backend.addUser(user)
            ctx.status(201)
            ctx.json(OkResultMapper("ok"))
        } catch (e: ExistsException) {
            throw BadRequestResponse(e.message.toString())
        }
    }

    fun loginUser(ctx: Context) {
        try {
            val userLogin = ctx.bodyValidator<UserLogin>()
                .check(
                    { it.email != null && it.password != null },
                    "Invalid body : email and password is required"
                )
                .get()
            val user = this.validarUser(userLogin.email!!, userLogin.password!!)
            ctx.header("Authorization", mC.tokenJWT.generateToken(user))
            ctx.status(200)
            ctx.json(OkResultMapper("ok"))
        } catch (e: NotFoundException) {
            ctx.status(404)
            ctx.json(ErrorViewMapper("error", "User not found"))
        }
    }

    private fun validarUser(email: String, password: String): User {
        val users = mC.backend.users
        return users.firstOrNull { it.email == email && it.password == password } ?: throw NotFoundException(
            "usuario",
            "email",
            "User not found"
        )
    }

    fun getUser(ctx: Context) {
        try {
            val token = ctx.header("Authorization")!!
            val user = mC.jwtAccessManager.getUser(token)
            ctx.json(
                UserViewMapper(
                    user.name,
                    user.image,
                    mC.toSimpleData(user.favorites),
                    mC.toSimpleData(user.lastSeen)
                )
            )
        } catch (e: NotFoundException) {
            throw NotFoundResponse("User not found")
        }
    }

    fun postFav(ctx: Context) {
        try {
            val token = ctx.header("Authorization")!!
            val contentId: String = ctx.bodyValidator<IdMapperValidator>()
                .check({ it.id != null }, "Invalid Body").get().id
            val user: User = mC.jwtAccessManager.getUser(token)

            mC.backend.addOrDeleteFav(user.id, contentId)

            ctx.status(200)
            ctx.json(mC.toSimpleData(user.favorites))
        } catch (e: NotFoundException) {
            throw NotFoundResponse(e.message.toString())
        }
    }

    fun addLastSeen(ctx: Context) {
        try {
            val token = ctx.header("Authorization")!!
            val contentId: String = ctx.bodyValidator<IdMapperValidator>()
                .check({ it.id != null }, "Invalid Body").get().id

            val content: Content = mC.searchContentById(contentId)
            val user: User = mC.jwtAccessManager.getUser(token)

            user.addLastSeen(content)

            ctx.status(200)
            ctx.json(mC.toSimpleData(user.lastSeen))
        } catch (e: NotFoundException) {
            throw NotFoundResponse(e.message.toString())
        }
    }
}