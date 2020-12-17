const moverseAContent = (id, props) => {
    if (id.includes("mov")) {
        props.history.push('/Movie', { idImagen: id })
    } else {
        props.history.push('/Serie', { idImagen: id })
    }
}

export default moverseAContent