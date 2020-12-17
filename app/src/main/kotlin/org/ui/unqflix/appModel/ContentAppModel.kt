package org.ui.unqflix.appModel

import domain.Available
import domain.Content
import domain.ContentState
import org.uqbar.commons.model.annotations.Observable

@Observable
class ContentAppModel(content: Content) {
    var description: String = ""
    var poster: String = ""
    var title: String = ""
    var state: ContentState = Available()
    var relatedContent: MutableList<Content> = mutableListOf()
    var model = content

    init{
        this.description = model.description
        this.poster = model.poster
        this.title = model.title
        this.state = model.state
        this.relatedContent = model.relatedContent
    }
}
