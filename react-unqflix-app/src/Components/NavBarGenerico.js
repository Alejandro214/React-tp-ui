import React from 'react';
import {Link} from 'react-router-dom';

function capList(caps) {
    return (
            <Link to="/reproductorDeCapitulo">
                {caps[0].title}
            </Link>
    )
}

function linkNav(listTemp) {

    const nTemp = "/season" + String(1);

    return (
            <Link to={nTemp}>
                {capList(listTemp[0].chapters)}
            </Link>
            )
}

const NavBar = ({ listTemp }) => {

    return(
        <nav className="nav">
            {linkNav(listTemp)}
        </nav>
    );
}

export default NavBar