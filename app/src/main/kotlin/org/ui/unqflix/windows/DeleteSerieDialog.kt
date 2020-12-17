package org.ui.unqflix.windows

import org.ui.unqflix.appModel.UNQFlixAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class DeleteSerieDialog(owner: WindowOwner, model: UNQFlixAppModel) : Dialog<UNQFlixAppModel>(owner, model) {
    override fun createFormPanel(mainPanel: Panel?) {
        title = "Confirm delete"
        Label(mainPanel) withText "Confirm delete of " + "${modelObject.selectedSerie?.title}"
        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Accept"
                onClick(Action { accept() })
            }
            Button(it) with {
                caption = "Cancel"
                onClick(Action { cancel() })
            }
        }
    }


}