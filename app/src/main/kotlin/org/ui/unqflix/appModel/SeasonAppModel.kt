package org.ui.unqflix.appModel

import data.idGenerator
import domain.Chapter
import domain.Season
import org.ui.unqflix.Excepciones.Exception
import org.uqbar.commons.model.annotations.Observable

@Observable

class SeasonAppModel(season : Season) {
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var chaptersApp: MutableList<ChapterAppModel>? = null
    var cantChaps: Int = 0
    var selectedChapter: ChapterAppModel? = null

    var model: Season = season


    init {
        this.id = model.id
        this.title = model.title
        this.description = model.description
        this.poster = model.poster
        this.chaptersApp = model.chapters.map { ChapterAppModel(it) }.toMutableList()
        this.cantChaps = this.chaptersApp!!.size
    }

    fun newSeason():Season{
        return Season(idGenerator.nextSeasonId(),this.title,this.description,this.poster, this.chaptersApp!!.map { it.model }.toMutableList())
    }
    fun newChapter():Chapter{
        return Chapter(idGenerator.nextChapterId(),"","",0,"","")
    }

    fun addChapter(chapter:Chapter){
        Exception.checkTitle(chapter.title,"Capítulo")
        var chapter = ChapterAppModel(chapter)
        if(this.model.chapters.add(chapter.model)) {
            this.chaptersApp?.add(chapter)
        }
    }


    fun modifyChapter(chapter: ChapterAppModel){
        chapter.model.title  = chapter.title
        chapter.model.description = chapter.description
        chapter.model.duration = chapter.duration
        chapter.model.thumbnail = chapter.thumbnail
        chapter.model.video = chapter.video
        val chapterApp = this.chaptersApp!!.find { it.id === chapter.id }!!
        chapterApp.title = chapter.title
        chapterApp.description = chapter.description
        chapterApp.duration = chapter.duration
        chapterApp.thumbnail = chapter.thumbnail
        chapterApp.video = chapter.video
    }


}