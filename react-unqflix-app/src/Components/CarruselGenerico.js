import React from 'react';
import Slider from 'infinite-react-carousel';
import Content from '../Content/Contents.js';

export function imagenesDeCarrusel(js,p,actualizar) {

    return (
        js.map((cont, i) => (
            <Content key={i} cont={cont} pr={p} actualizar={actualizar}/>
        ))
    )
}

function CarruselGenerico ({ titulo, lista, p, actualizar }) { 
    return(
        <div>
            <h2 id="tituloCarrusel">{titulo}</h2>
            <Slider slidesToShow={7} adaptiveHeight={true} >
                {
                    imagenesDeCarrusel(lista,p,actualizar)
                }
            </Slider>
        </div>
    );
}

export default CarruselGenerico