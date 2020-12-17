package ApiRest

import ar.com.unqui.api.NotFoundToken
import ar.com.unqui.api.TokenJWT
import domain.NotFoundException
import domain.UNQFlix
import domain.User
import guia2.model.Roles
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse

class JWTAccessManager(val tokenJWT: TokenJWT, val unqFlix: UNQFlix) : AccessManager {
    fun getUser(token: String): User {
        try {
            val userId = tokenJWT.validate(token)
            return unqFlix.users.find { it.id == userId }!!
        } catch (e: NotFoundToken) {
            throw  UnauthorizedResponse("Token not found")
        } catch (e: NotFoundException) {
            throw  UnauthorizedResponse("Invalid Token")
        } catch (e: IllegalArgumentException) {
            throw  UnauthorizedResponse("Invalid Token")
        }
    }

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when {
            token == null && roles.contains(Roles.ANYONE) -> handler.handle(ctx) //si tiene o no tiene decide pasarlo igual?
            token == null -> throw UnauthorizedResponse("Token not found")          //tambien lo lanzaria en el validate
            roles.contains(Roles.ANYONE) -> handler.handle(ctx)                 //asociado a la pregunta de la primer linea
            roles.contains(Roles.USER) -> {
                getUser(token)
                handler.handle(ctx)
            }
        }
    }
}