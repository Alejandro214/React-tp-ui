import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom'
import topBar from '../Components/topBar'
import CarruselGenerico from '../Components/CarruselGenerico'
import getContentById from '../Content/GetContent'
import Button from 'react-bootstrap/Button'

const CuerpoSerie = ({ cont }) => {
    const { poster, title, description } = cont;

    return (
        <div className="bodyContent">
            <div className="imgBodyContent">
                <img 
                src={poster} alt={title} 
                onError={(e) => { e.target.onerror = null; e.target.src = "https://linnea.com.ar/wp-content/uploads/2018/09/404PosterNotFound.jpg" }}>
                </img>
            </div>
            <div className="titleBox">
                <h1 className="title">{title}</h1>
                <p className="description">{description}</p>
            </div>
        </div>
    )
};

const ListItemSeason = (season) => {
    // La primer seasons son las props (suponemos), la segunda es la lista de temporadas que no sabemos porque hay que sacarla de la primera.
    return (
        <li><Link data-toggle="tab" to={"#" + season.season.id}>{season.season.title}</Link></li>
    )
}

const TablaSeasons = ({ cont ,props}) => {

    const { seasons } = cont

    const crearTablasPorSeason = () => {
        return (
            seasons.map(season => (
                <ListItemSeason key={season.id} season={season} />
            ))
        )
    }

    const moverseAPlayer = (id,video) => {
        console.log(props)
        props.history.push('/player',{video,id})
    }

    const mapearChapters = (season) => {
        return (               
            season.chapters.map(chapter =>
                <div className = "chapterbtn">
                <Button onClick={() => moverseAPlayer(chapter.id,chapter.video)}>{chapter.title}</Button>
                </div>
            )
        )
    }

    const mapearSeasons = () => {
        return (
            seasons.splice(1).map(season =>
                <div id={season.id} className="tab-pane fade">
                    {mapearChapters(season)}
                </div>
            )

        )
    }

    const mapearPrimero = (season) => {
        return (
            season.chapters.map(chapter =>
                <div className = "chapterbtn">
                    <Button onClick={() => {console.log(chapter);moverseAPlayer(chapter.id,chapter.video)}}>{chapter.title}</Button>
                </div>
            )
        )
    }

    const crearTablaDeChapters = () => {
        return (
            <div className="tab-content">

                <div id={seasons[0].id} className="tab-pane fade in active">
                    {mapearPrimero(seasons[0])}
                </div>
                {mapearSeasons()}
            </div>
        )
    }

    return (
        <div className="container">
            <h3>Seasons</h3>
            <ul className="nav nav-tabs">
                {crearTablasPorSeason()}
                {crearTablaDeChapters()}
            </ul>
        </div>
    )
};


const Serie = (props) => {
    const [idContent, setIdContent] = useState(props.location.state.idImagen ? props.location.state.idImagen : '')
    const [serie, setSerie] = useState('')

    useEffect(() => getContentById(setSerie, idContent), [idContent])

    return (
        <div>
            {topBar(props)}
            <div>
                {!!serie && <CuerpoSerie cont={serie} />}
            </div>
            <div>
                {!!serie && <TablaSeasons cont={serie} props={props} />}
            </div>
            <div>
                {!!serie && <CarruselGenerico titulo="Contenido Relacionado" lista={serie.relatedContent} p={props} actualizar={setIdContent} />}
            </div>
        </div>
    )
}

export default Serie