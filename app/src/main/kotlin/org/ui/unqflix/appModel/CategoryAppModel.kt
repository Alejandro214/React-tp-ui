package org.ui.unqflix.appModel

import domain.Category
import org.uqbar.commons.model.annotations.Observable

@Observable
class CategoryAppModel(category: Category) {

    var id: String = ""
    var name: String = ""
    var model: Category = category


    init {
        this.id = model.id
        this.name = model.name
    }
}