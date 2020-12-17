package org.ui.unqflix.windows

import domain.ExistsException
import domain.Season
import org.ui.unqflix.Excepciones.Exception
import org.ui.unqflix.appModel.SeasonAppModel
import org.ui.unqflix.appModel.SerieAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.Action

class ShowSerieDialog(
    owner: WindowOwner,
    model: SerieAppModel
) : Dialog<SerieAppModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {
        Label(mainPanel) with {
            text = "${modelObject.title}"
            alignLeft()
        }
        Label(mainPanel) withText "Seasons:"

        table<SeasonAppModel>(mainPanel) {
            bindItemsTo("seasonsApp")
            bindSelectionTo("selectedSeason")
            visibleRows = 5
            column {
                title = "#"
                fixedSize = 100
                bindContentsTo("id")
            }

            column {
                title = "Title"
                fixedSize = 100
                bindContentsTo("title")
            }

            column {
                title = "#Chapters"
                fixedSize = 100
                bindContentsTo("cantChaps")
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Add new Season"
                onClick(
                    Action {
                        actionAddSeasonOnAccept()

                    })
            }
            Button(it) with {
                caption = "Modified Season"


                onClick(Action {
                    actionModifyOnAccept()
                })
            }
            Button(it) with {
                caption = "Show Chapters"
                onClick(
                    Action {
                        openDialogShowChapter()
                    })
            }
        }
    }


    private fun openDialogShowChapter() {
        Exception.checkSelected(modelObject.selectedSeason, "Temporada")
        var seasonApp: SeasonAppModel = (modelObject as SerieAppModel).selectedSeason!!
        ShowChapterDialog(owner, seasonApp).open()
    }


    //Seasons
    private fun actionAddSeasonOnAccept() {
        var seasonApp = SeasonAppModel(modelObject.newSeason())
        AddModSeasonDialog(owner, seasonApp) with {
            onAccept { addSeason(seasonApp.newSeason()) }
            onCancel { close() }
            open()
        }
    }

    private fun actionModifyOnAccept() {
        Exception.checkSelected(modelObject.selectedSeason, "Temporada")
        var seasonApp: SeasonAppModel = SeasonAppModel((modelObject as SerieAppModel).selectedSeason!!.model)
        AddModSeasonDialog(owner, seasonApp) with {
            onAccept { modifySeason(seasonApp) }
            onCancel { close() }
            open()
        }
    }

    private fun addSeason(season: Season) {
        try {
            modelObject.addSeason(season)
        } catch (e: ExistsException) {
            throw UserException(e.message)
        }
    }

    private fun modifySeason(season: SeasonAppModel) {
        modelObject.modifySeason(season)
    }
}