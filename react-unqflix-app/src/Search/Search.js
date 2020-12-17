import React, { useState } from 'react';
import Content from '../Content/Contents';
import TopBar from '../Components/topBar'


const Search1 = (props) => {
    const [resultList,setResulList] = useState(props.location.state.result ? props.location.state.result : [])

    function imagenesDeCarrusel(js) {
        return (
            js.map(cont => (
                <li>
                    <Content  cont={cont} pr = {props} />
                </li>
            ))
        )
    }
    

    return (
        <div>
            {TopBar(props,setResulList)}
            <h2>SearchedText</h2>
            <div className="Searc_imagenes">
                <ul>
                    {console.log(resultList)}
                    {imagenesDeCarrusel(resultList.content)}
                </ul>
            </div>
        </div>
    )
}

// function imagenesDeCarrusel(js,props) {
//     return (
//         js.map(cont => (
//             <li>
//                 <Content  cont={cont} props = {props} />
//             </li>
//         ))

//     )
// }

export default Search1
