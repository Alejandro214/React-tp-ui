package org.ui.unqflix.windows

import domain.Chapter
import org.ui.unqflix.appModel.ChapterAppModel
import org.ui.unqflix.Excepciones.Exception
import org.ui.unqflix.appModel.SeasonAppModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class ShowChapterDialog(owner: WindowOwner, model: SeasonAppModel) : Dialog<SeasonAppModel>(owner, model) {
    override fun createFormPanel(mainPanel: Panel) {
      //  Label(mainPanel) withText " ${modelObject.selectedSeason?.title}"

        Label(mainPanel) with {
            text = "${modelObject.title}"
            alignLeft()
        }
        Label(mainPanel) withText "Chapters:"

        table<ChapterAppModel>(mainPanel) {
            bindItemsTo("chaptersApp")
            bindSelectionTo("selectedChapter")
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
                title = "Duration"
                fixedSize = 100
                bindContentsTo("duration")
            }
        }

        Panel(mainPanel) with {
            asHorizontal()
            Button(it) with {
                caption = "Add new Chapter"
                onClick(Action{
                   actionAddChapter()
                })
            }
            Button(it) with {
                caption = "Modified Chapter"
                onClick(Action {
                    actionModifiedChapter()
                })
            }
        }
    }



    private  fun actionAddChapter(){
        var chapterApp = ChapterAppModel(modelObject.newChapter())
        AddModChapterDialog(owner, chapterApp) with {
            onAccept { addChapter(chapterApp.newChapter()) }
            onCancel { close() }
            open()
        }
    }

    private fun actionModifiedChapter(){
        Exception.checkSelected(modelObject.selectedChapter,"Capitulo")
        var chapterApp:ChapterAppModel = ChapterAppModel((modelObject as SeasonAppModel).selectedChapter!!.model)
        AddModChapterDialog(owner, chapterApp) with {
            onAccept{modifyChapter(chapterApp)}
            onCancel { close() }
            open()
        }
    }
    private fun addChapter(chapter: Chapter){
        modelObject.addChapter(chapter)
    }

    private  fun  modifyChapter(chapter: ChapterAppModel){
        modelObject.modifyChapter(chapter)
    }

}