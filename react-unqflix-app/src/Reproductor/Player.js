import React, { useEffect } from 'react';
import topBar from '../Components/topBar'
import Button from 'react-bootstrap/Button'
import ReactPlayer from 'react-player'
import api from '../Api'

const Player = (props) => {

    console.log(props)
    console.log(props.location.state.id)
    console.log(props.location.state.video)
    const agregarAUltimasVistas = () => {
        api.lastSeen(props.location.state.id)
            .catch(e => console.log(e))
    }

    useEffect(agregarAUltimasVistas)

    return (
        <div>
            {topBar(props)}
            <div className="modalPlayer">
                <ReactPlayer className="playerBox"
                    url={props.location.state.video}
                    width='1300px'
                    height='700px'
                    controls
                    muted
                    playbackRate={1.75}
                />
            </div>
            <Button onClick={props.history.goBack}> BACK </Button>
        </div>
    )
}

export default Player