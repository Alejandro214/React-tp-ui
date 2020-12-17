import api from '../Api'

const getLastSeenAndFavorites = (setFavorites, setLastSeens) => {
    api.getUser(localStorage).then((response) => {
        setFavorites(response.data.favorites)
        setLastSeens(response.data.lastSeen)
    }).catch(
        error => console.log(error)
    )
}

export default getLastSeenAndFavorites