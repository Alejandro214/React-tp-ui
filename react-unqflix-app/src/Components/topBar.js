import React, { useState } from 'react';

import logo from '../Images/tick.png';
import api from '../Api'
import Button from 'react-bootstrap/Button'

const TopBar = (props,setResulList) => {
    const [input, setInput] = useState('')

    const avatar = localStorage.getItem('Avatar')

    const navegarAlHome = () => {
        props.history.push('/home')
    }

    const handleInput = (e) => {
        setInput(e.target.value)
    }

    const searchApi = () => {
        api.search(input).then((response) => {
            if(setResulList == undefined){
            props.history.push('/search', { result: response.data })
            }else{
                setResulList(response.data)
            }

        }).catch((e) => console.log(e))
    }

    const logout = () => {
        localStorage.clear()
        props.history.push('/login')
    }

    return (
        <div id="topBar">
            <span id="topLogo">
                <img id="logo" src={logo} alt="unqflix" onClick={navegarAlHome} />
                <p id="txhome">Home ^</p>
            </span>
            <span>
                <input type="text" value={input} placeholder='searchBar' onChange={handleInput} />
            </span>
            <span className="boton-Search" >
                <button type='button' className= "btn btn-info" onClick={searchApi}>Search</button>
            </span>
            <span id="topAvatar" onClick={logout}>
                <img id="avatar" src={avatar} alt="avatar usuario" />
                <p id="textoCerrar">Close Session ^</p>
            </span>
        </div>
    )
}

export default TopBar