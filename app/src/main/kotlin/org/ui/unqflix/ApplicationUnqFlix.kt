package org.ui.unqflix


import org.ui.unqflix.appModel.UNQFlixAppModel
import org.ui.unqflix.windows.WindowsUnqflix
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class ApplicationSerie:Application() {
    override fun createMainWindow(): Window<*> {
        return WindowsUnqflix(this, UNQFlixAppModel())
    }

}

fun main(){
    ApplicationSerie().start()
}