package org.ui.unqflix.windows

import domain.ExistsException
import domain.Serie
import org.ui.unqflix.Excepciones.Exception
import org.ui.unqflix.appModel.*
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.Action

class WindowsUnqflix(owner: WindowOwner, unqflix: UNQFlixAppModel) : SimpleWindow<UNQFlixAppModel>(owner, unqflix) {


    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "UNQFlix"
        Panel(mainPanel) with {
            asHorizontal()
            Label(it) withText "Search:"
            TextBox(it) with {
                bindTo("inputSerie")
                width = 500
            }
            Button(it) with {
                caption = "Apply Search"
                onClick { (modelObject as UNQFlixAppModel).search() }
            }
        }

        Panel(mainPanel) with {
            Label(mainPanel) with {
                text = "Series:"
                alignLeft()
            }
            mainPanel.setLayout(VerticalLayout())
            table<SerieAppModel>(mainPanel) {
                bindItemsTo("series")
                bindSelectionTo("selectedSerie")
                visibleRows = 7
                column {
                    title = "ID"
                    fixedSize = 75
                    bindContentsTo("id")
                }
                column {
                    title = "Title"
                    fixedSize = 455
                    bindContentsTo("title")
                }
                column {
                    title = "#Season"
                    fixedSize = 75
                    bindContentsTo("cantSeaon")
                }
                column {
                    title = "State"
                    fixedSize = 75
                    bindContentsTo("nameState")
                }
            }

            Panel(mainPanel) with {
                asHorizontal()
                Button(it) with {
                    caption = "Add new Serie"
                    onClick(Action {
                        actionAdd()
                    })
                }
                Button(it) with {
                    caption = "Modified Serie"
                    onClick(Action {
                        actionModify()
                    })
                }
                Button(it) with {
                    caption = "Delete Serie"
                    onClick(
                        Action {
                            actionDelete()
                        })
                }
                Button(it) with {
                    caption = "Show Serie"
                    onClick(
                        Action {
                            actionShowSerie()
                        })
                }
            }
        }
    }

    private fun actionShowSerie() {
        Exception.checkSelected(modelObject.selectedSerie, "Serie")
        ShowSerieDialog(owner, modelObject.selectedSerie!!).open()

    }

    private fun addSerie(serie: Serie) {
        try {
            modelObject.addSerie(serie)
        } catch (e: ExistsException) {
            throw UserException(e.message)
        }
    }

    private fun modifySerie(serie: SerieAppModel) {
        modelObject.modifySerie(serie)
    }

    private fun actionAdd() {
        var serieApp = SerieAppModel(modelObject.newSerie())
        AddModSerieDialog(owner, serieApp) with {
            onAccept { addSerie(serieApp.newSerie()) }
            onCancel { close() }
            open()
        }
    }

    private fun actionModify() {
        Exception.checkSelected(modelObject.selectedSerie, "Serie")
        var serieApp = SerieAppModel(modelObject.selectedSerie!!.serie)
        AddModSerieDialog(owner, serieApp) with {
            onAccept { modifySerie(serieApp) }
            onCancel { close() }
            open()
        }

    }

    private fun actionDelete() {
        Exception.checkSelected(modelObject.selectedSerie, "Serie")
        DeleteSerieDialog(owner, modelObject) with {
            onAccept {
                modelObject.deleteSerie(modelObject.selectedSerie)
                (modelObject as UNQFlixAppModel).search()

            }
            onCancel { close() }
            open()
        }
    }
}