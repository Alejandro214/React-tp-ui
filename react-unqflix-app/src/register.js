import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import api from './Api'

function Back() {
    return (<h2>Back</h2>)
}

const Register = (props) => {
    const [email, setEmail] = useState(props.email ? props.email : '')
    const [password, setPassword] = useState(props.password ? props.password : '')
    const [name, setName] = useState(props.name ? props.name : '')
    const [imageLink, setImageLink] = useState(props.imageLink ? props.imageLink : '')
    const [creditCard, setCreditCard] = useState(props.creditCard ? props.creditCard : '')
    const [error,setError]  = useState(false)

    const handleEmail = (e) => {
        setEmail(e.target.value)
    }

    const handlePassword = (e) => {
        setPassword(e.target.value)
    }

    const handleName = (e) => {
        setName(e.target.value)
    }

    const handleImageLink = (e) => {
        setImageLink(e.target.value)
    }

    const handleCreditCard = (e) => {
        setCreditCard(e.target.value)
    }

    const registrarse = () => {
        api.register(email, name, password, imageLink, creditCard)
            .then(() => props.history.push('/login'))
            .catch((e) => {
                console.log(e.response)
                setError(true)
            })
    }

    return (
        <div className="boxInicio">
            <div className="user-register">
                <div className="Register-form">
                    <div>
                        <input type="text" value={email} name='email' placeholder='Email' onChange={handleEmail} />
                    </div>
                    <div>
                        <input type="text" value={name} name='name' placeholder='Name' onChange={handleName} />
                    </div>
                    <div>
                        <input type="password" value={password} name="password" placeholder="Password" onChange={handlePassword} />
                    </div>
                    <div>
                        <input type="text" value={imageLink} name='imageLink' placeholder='ImageLink' onChange={handleImageLink} />
                    </div>
                    <div>
                        <input type="text" value={creditCard} name="creditCard" placeholder="CreditCard" onChange={handleCreditCard} />
                    </div>
                    <div className="boton-register">
                        <button type='button' className= "btn btn-info" onClick={registrarse}> Register </button>
                    </div>
                    <div className = "error">
           {error &&
                     <label className = "mesajeError">Usuario ya existente</label>

           }
           </div>
                    <div className="back-btn">
                        <Link to={'/login'}>{Back()}</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Register