package org.ui.unqflix.windows

import org.ui.unqflix.appModel.SeasonAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class AddModSeasonDialog(owner: WindowOwner, model: SeasonAppModel) : Dialog<SeasonAppModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {

        Panel(mainPanel) with {
            Panel(it) with {
                Label(it) with {
                    text = "Title:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    bindTo("title")
                }
            }
            Panel(it) with {
                Label(it) with {
                    text = "Description:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    height = 250
                    bindTo("description")
                }
            }
            Panel(it) with {
                Label(it) with {
                    text = "Poster:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    bindTo("poster")
                }
            }
            Panel(mainPanel) with {
                asHorizontal()
                Button(it) with {
                    caption = "Accept"
                    onClick(Action{ accept() })
                }
                Button(it) with {
                    caption = "Cancel"
                    onClick(Action { cancel() })
                }
            }
        }
    }

}