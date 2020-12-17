import React, { useState, useEffect } from 'react';
import Slider from 'infinite-react-carousel';
import CarruselGenerico from '../Components/CarruselGenerico'
import TopBar from '../Components/topBar'
import api from '../Api'
import getLastSeenAndFavorites from '../Content/GetUserContent'
import moverseAContent from '../Content/MoveContent'

const Home = (props) => {
    const [banners, setBanners] = useState([])
    const [favorites, setFavorites] = useState([])
    const [lastSeens, setLastSeens] = useState([])

    const getBanners = () => {
        api.getBanners().then((response) => {
            let nvosBanners = []
            response.data.banners.forEach(banner => {
                nvosBanners.push(banner)
            });
            setBanners(nvosBanners)
        }).catch(
            error => console.log(error)
        )
    }

    const mapearBanners = (props) => {
        return (
            banners.map(
                ban => (
                    <div className="topImgSlideGeneric">
                        <div className="tituloAlternativo">{ban.title}</div>
                        <img className="imgSlidePrincipal" src={ban.poster} alt={ban.title}
                            onClick={() => moverseAContent(ban.id, props)}
                            onError={(e) => { e.target.onerror = null; e.target.src = "https://linnea.com.ar/wp-content/uploads/2018/09/404PosterNotFoundReverse.jpg" }}>
                        </img>
                    </div>
                )
            )
        )
    }

    useEffect(() => getBanners(), [])
    useEffect(() => getLastSeenAndFavorites(setFavorites, setLastSeens), [])

    return (
        <div>
            {TopBar(props)}
            <div className="Carruseles">
                <div id="CarruselPrincipal">
                    {!!banners.length &&
                        <Slider dots pauseOnHover={true} autoplay={true} duration={300} adaptiveHeight={true} >
                            {mapearBanners(props)}
                        </Slider>}
                </div>
                <div>
                    {!!lastSeens.length && <CarruselGenerico titulo="Recientemente visto" lista={lastSeens} p={props}/>}
                </div>
                <div>
                    {!!favorites.length && <CarruselGenerico titulo="Favoritos" lista={favorites} p={props} />}
                </div>
            </div>
        </div>
    )
}

export default Home