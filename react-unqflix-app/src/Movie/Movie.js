import React, { useState, useEffect } from 'react';
import topBar from '../Components/topBar'
import CarruselGenerico from '../Components/CarruselGenerico'
import getContentById from '../Content/GetContent'
import Button from 'react-bootstrap/Button'

const Movie = (props) => {
    const [idContent, setIdContent] = useState(props.location.state.idImagen ? props.location.state.idImagen : '')
    const [movie, setMovie] = useState('')

    const CuerpoMovie = ({ cont }) => {
        const { poster, title, description, video, id } = cont;

        const moverseAPlayer = () => {
            props.history.push('/player', ({ video, id }))
        }

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
                <div id="buttonContent" onClick={() => moverseAPlayer({ props })}>
                    <Button id="boton">Play</Button>
                </div>
            </div>
        )
    };

    useEffect(() => getContentById(setMovie, idContent), [idContent])

    return (
        <div>
            {topBar(props)}
            <div>
                {!!movie && <CuerpoMovie cont={movie} />}
            </div>
            <div>
                {!!movie && <CarruselGenerico titulo="Contenido Relacionado" lista={movie.relatedContent} p={props} actualizar={setIdContent} />}
            </div>
        </div>
    )
}

export default Movie