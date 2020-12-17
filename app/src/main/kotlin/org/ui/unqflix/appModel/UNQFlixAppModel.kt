package org.ui.unqflix.appModel

import data.getUNQFlix
import data.idGenerator
import domain.*
import org.ui.unqflix.Excepciones.Exception
import org.uqbar.commons.model.annotations.Observable

@Observable
class UNQFlixAppModel {
    val system: UNQFlix = getUNQFlix()
    var inputSerie: String = ""
    var series: MutableList<SerieAppModel>? = this.initSeries()
    var selectedSerie: SerieAppModel? = null





    fun initSeries(): MutableList<SerieAppModel> {
        return system.series.map { SerieAppModel(it) }.toMutableList()
    }

    fun search() {

        this.series = system.searchSeries(inputSerie).map { SerieAppModel(it) }.toMutableList()


    }

    fun deleteSerie(selectedSerie: SerieAppModel?) {
        if (system.deleteSerie(selectedSerie!!.id)) {
            this.series?.remove(selectedSerie!!)
            if(series!!.isEmpty()){
                series = null
            }
        }
    }

    fun addSerie(serie: Serie) {
        Exception.checkTitle(serie.title, "Serie")
        if (system.addSerie(serie)) {
            var serieApp = SerieAppModel(serie)
            this.series?.add(serieApp)
        }
    }

    fun modifySerie(serieMod: SerieAppModel) {
        selectedSerie!!.title = serieMod.title
        selectedSerie!!.poster = serieMod.poster
        selectedSerie!!.description = serieMod.description
        selectedSerie!!.stateBoolean = serieMod.stateBoolean
        selectedSerie!!.reloadState()
        selectedSerie!!.selectedCategories = serieMod.selectedCategories
        selectedSerie!!.selectedRelatedContents = serieMod.selectedRelatedContents
        selectedSerie!!.reflexToModel()
    }

    fun newSerie(): Serie {
        return Serie(idGenerator.nextSerieId(), "", "", "", Unavailable(), mutableListOf())
    }
}