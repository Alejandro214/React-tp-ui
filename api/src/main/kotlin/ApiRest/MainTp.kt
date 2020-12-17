package guia2.model

import ApiRest.Controllers.MainController
import ApiRest.JWTAccessManager
import ar.com.unqui.api.TokenJWT
import data.getUNQFlix
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role

enum class Roles : Role {
    ANYONE, USER
}

fun main(args: Array<String>) {
    val tokenJWT = TokenJWT()
    val unqFlix = getUNQFlix()
    val jwtAccessManager = JWTAccessManager(tokenJWT, unqFlix)
    val app = Javalin.create {
        it.defaultContentType = "application/json"
        it.accessManager(jwtAccessManager)
        it.enableCorsForAllOrigins()
    }
    app.before {
        it.header("Access-Control-Expose-Headers", "*")
        it.header("Access-Control-Allow-Origin", "*")
    }

    app.start(7000)
    val mC = MainController(tokenJWT, unqFlix, jwtAccessManager)


    app.routes {
        path("/register") {
            post(mC.userController::createUser, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("/login") {
            post(mC.userController::loginUser, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("/user") {
            get(mC.userController::getUser, mutableSetOf<Role>(Roles.USER))
            path("/fav") {
                post(mC.userController::postFav, mutableSetOf<Role>(Roles.USER))
            }
            path("/lastSeen") {
                post(mC.userController::addLastSeen, mutableSetOf<Role>(Roles.USER))
            }
        }
        path("/content") {
            get(mC.contentController::getAllContent, mutableSetOf<Role>(Roles.USER))
            path("/:contentId") {
                get(mC.contentController::getContentById, mutableSetOf<Role>(Roles.USER))
            }
        }
        path("/banners") {
            get(mC.contentController::getAllBanners, mutableSetOf<Role>(Roles.USER))
        }
        path("/search") {
            get(mC.searchController::searchContentByText, mutableSetOf<Role>(Roles.USER))
        }
    }
}

