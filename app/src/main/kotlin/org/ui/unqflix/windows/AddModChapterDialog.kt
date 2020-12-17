package org.ui.unqflix.windows

import org.ui.unqflix.appModel.ChapterAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class AddModChapterDialog(owner: WindowOwner?, model: ChapterAppModel) : Dialog<ChapterAppModel>(owner, model) {


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
                    text = "Duration:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    bindTo("duration")
                }
            }
            Panel(it) with {
                Label(it) with {
                    text = "Thumbnail:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    bindTo("thumbnail")
                }
            }
            Panel(it) with {
                Label(it) with {
                    text = "Video:"
                    alignLeft()
                }
                TextBox(it) with {
                    width = 200
                    bindTo("video")
                }
            }
            Panel(mainPanel) with {
                asHorizontal()
                Button(it) with {
                    caption = "Accept"
                    onClick(Action { accept() })
                }
                Button(it) with {
                    caption = "Cancel"
                    onClick ( Action { cancel() })
                }
            }
        }
    }

}

