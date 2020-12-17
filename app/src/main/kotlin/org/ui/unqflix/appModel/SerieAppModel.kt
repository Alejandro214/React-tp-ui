package org.ui.unqflix.appModel

import data.getUNQFlix
import data.idGenerator
import domain.*
import org.ui.unqflix.Excepciones.Exception
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class SerieAppModel(var serie: Serie) {
    var system = getUNQFlix()
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var poster: String = ""
    var stateBoolean: Boolean? = null
    var state: ContentState? = null
    var categories: MutableList<CategoryAppModel> = system.categories.map { CategoryAppModel(it) }.toMutableList()
    var seasons: MutableList<Season>? = null
    var seasonsApp: MutableList<SeasonAppModel>? = null
    var selectedSeason: SeasonAppModel? = null
    var cantSeaon: Int = 0
    var selectedCategory: CategoryAppModel? = null
    var unSelectedCategory: CategoryAppModel? = null
    var selectedCategories: MutableList<CategoryAppModel>? = null
    var selectedContent: ContentAppModel? = null
    var unSelectedContent: ContentAppModel? = null
    var nameState: String? = null
    var selectedRelatedContents: MutableList<ContentAppModel> = mutableListOf()
    var releatedSeries: MutableList<ContentAppModel> = system.series.map { ContentAppModel(it) }.toMutableList()
    var releatedMovies: MutableList<ContentAppModel> = system.movies.map { ContentAppModel(it) }.toMutableList()
    var releatedContents: Set<ContentAppModel> = releatedSeries.union(releatedMovies)
    var model = serie

    init {
        this.initSerie()
        this.id = model.id
        this.cantSeaon = this.seasons!!.size
        this.seasonsApp = this.initSeasons()

    }

    fun initSerie() {
        this.title = model.title
        this.description = model.description
        this.poster = model.poster
        this.state = model.state
        this.seasons = model.seasons
        this.selectedRelatedContents = model.relatedContent.map { ContentAppModel(it) }.toMutableList()
        this.selectedCategories = model.categories.map { CategoryAppModel(it) }.toMutableList()
        this.nameState = this.showState()
        this.stateBoolean = model.state.toString().contains("Ava")
    }

    fun reflexToModel() {
        model.title = this.title
        model.poster = this.poster
        model.description = this.description
        model.state = this.state!!
        model.categories = this.selectedCategories!!.map { it.model }.toMutableList()
        model.relatedContent = this.selectedRelatedContents.map { it.model }.toMutableList()
    }

    // Begin Adds ------------------------------------------------------------------------------------------------------
    fun addCategory() {
        if (selectedCategory == null)
            throw (UserException("No se ha seleccionado ninguna categoria"))

        if (selectedCategories!!.filter { it.name == selectedCategory!!.name }.none()) {
            selectedCategories!!.add(selectedCategory!!)
        }
    }

    fun addContent() {
        if (selectedContent == null)
            throw (UserException("No se ha seleccionado ningún contenido"))

        if (selectedRelatedContents.filter { it.title == selectedContent!!.title }.none()) {
            selectedRelatedContents.add(selectedContent!!)
        }
    }
    // End Adds --------------------------------------------------------------------------------------------------------

    // Begin Removes ---------------------------------------------------------------------------------------------------
    fun removeCategory() {
        if (unSelectedCategory == null)
            throw (UserException("No se ha seleccionado ninguna categoria"))

        if (selectedCategories!!.contains(unSelectedCategory!!)) {
            selectedCategories!!.remove(unSelectedCategory!!)
        }
    }

    fun removeContent() {
        if (unSelectedContent == null)
            throw (UserException("No se ha seleccionado ningun contenido"))

        if (selectedRelatedContents.contains(unSelectedContent!!)) {
            selectedRelatedContents.remove(unSelectedContent!!)
        }
    }
    // End Removes -----------------------------------------------------------------------------------------------------

    fun newSerie(): Serie {
        return Serie(idGenerator.nextSerieId(), title, description, poster, this.updateState(),
            selectedCategories!!.map { it.model }.toMutableList(),
            relatedContent = selectedRelatedContents.map { it.model }.toMutableList()
        )
    }

    fun reloadState() {
        this.state = this.updateState()
        this.nameState = this.showState()
    }

    fun updateState(): ContentState {
        return if (stateBoolean!!) {
            Available()
        } else {
            Unavailable()
        }
    }

    fun showState() = if (this.state.toString().contains ("Av")) {"✓"} else{"x"}

    fun initSeasons(): MutableList<SeasonAppModel> {
        return this.seasons!!.map { SeasonAppModel(it) }.toMutableList()
    }


    fun addSeason(season: Season) {
        Exception.checkTitle(season.title,"Temporada")
        var seasonApp = SeasonAppModel(season)
        if (model.addSeason(seasonApp.model)) {
            this.seasonsApp?.add(seasonApp)
        }
    }

    fun modifySeason(season: SeasonAppModel) {
        season.model.title = season.title
        season.model.description = season.description
        season.model.poster = season.poster
        val seasonApp = this.seasonsApp!!.find { it.id === season.id}!!
        seasonApp.title = season.title
        seasonApp.description = season.description
        seasonApp.poster = season.poster

    }

    fun newSeason(): Season {
        return Season(idGenerator.nextSeasonId(), "", "", "", mutableListOf())
    }
}


