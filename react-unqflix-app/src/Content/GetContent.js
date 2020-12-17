import api from '../Api'

const getContentById = (setContent, idContent) => {
    api.getContentByid(idContent).then((response) => {
        setContent(response.data)
    }).catch(
        error => console.log(error)
    )
}

export default getContentById