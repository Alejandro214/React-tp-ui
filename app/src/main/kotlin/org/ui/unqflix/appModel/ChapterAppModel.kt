package org.ui.unqflix.appModel

import data.idGenerator
import domain.Chapter
import org.uqbar.commons.model.annotations.Observable

@Observable
class ChapterAppModel(chapter: Chapter) {

    var id: String = ""
    var title: String = ""
    var description: String = ""
    var duration: Int = 0
    var video: String = ""
    var thumbnail: String = ""
    var model = chapter

    init {
        id = model.id
        title = model.title
        description = model.description
        duration = model.duration
        video = model.video
        thumbnail = model.thumbnail
    }

    fun newChapter():Chapter{
        return Chapter(idGenerator.nextChapterId(), title, description, duration, video, thumbnail)
    }

}