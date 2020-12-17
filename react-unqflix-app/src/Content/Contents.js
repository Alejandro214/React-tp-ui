import React from 'react'

function moverseAContent(id, pr, actualizar) {
    if (pr.location.state === undefined || pr.location.pathname === '/search') {
        movieOrSerie(id, pr)
    } else {
        if (pr.location.state.idImagen.includes("mov")) { // Estoy en una Película.
            if (id.includes("mov")) { // Quiero moverme a una Película.
                actualizar(id) // Actualizo.
            } else { // Quiero moverme a una Serie.
                pr.history.push('/Serie', { idImagen: id })
            }
        } else { // Estoy en una Serie.
            if (id.includes("ser")) { // Quiero moverme a una Serie.
                actualizar(id) // Actualizo.
            } else { // Quiero moverme a una Película
                pr.history.push('/Movie', { idImagen: id })
            }
        }
    }
}

function movieOrSerie(id, pr) {
    if (id.includes("mov")) {
        pr.history.push('/Movie', { idImagen: id })

    } else {
        pr.history.push('/Serie', { idImagen: id })
    }
}

function Content({ cont, pr, actualizar }) {

    const { id, poster, title } = cont;

    return (
        <div className="topImgSlideGeneric" onClick={() => moverseAContent(id, pr, actualizar)}>
            <div className="tituloAlternativo">{title}</div>
            <img className="imgSlideGeneric" src={poster} alt={title}
                onError={(e) => { e.target.onerror = null; e.target.src = "https://linnea.com.ar/wp-content/uploads/2018/09/404PosterNotFound.jpg" }}>
            </img>
        </div>
    );
}

export default Content