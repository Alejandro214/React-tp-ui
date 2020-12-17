import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import logo from './Images/tick.png';
import api from './Api'

function Register() {
  return <h2>Register</h2>;
}

const Login = (props) => {

  const [email, setEmail] = useState(props.email ? props.email : '')
  const [password, setPassword] = useState(props.password ? props.password : '')
  const [error,setError]        = useState(false)
  const setHome = () => {
    api.getUser().then((response) => {
      localStorage.setItem('Avatar',response.data.image)
      props.history.push('/home')
    })
  }

  const logearse = () => {
    api.login(email, password).then((response) => {
      const authorizationToken = response.headers.authorization
      localStorage.setItem('Token',authorizationToken)
      setHome()
    }).catch(
      (error) => {
        console.log(error) 
        setError(true)
      }
        
    )
  }

  const handleUsername = (e) => {
    setEmail(e.target.value);
  }

  const handlePassword = (e) => {
    setPassword(e.target.value);
  }

  return (
    <div className="boxInicio">
      <div className="user-login">

        <img id="login_logo" src={logo} alt="unqflix" />
        <div className="user-into">
          <input type="text" value={email} name='email' placeholder='User' onChange={handleUsername} />
        </div>
        <div className="password-into">
          <input type="password" value={password} name="password" placeholder="Password" onChange={handlePassword} />
        </div>
        <div className="boton-login" >
          <button type='button' className= "btn btn-info" onClick={logearse}>Login</button>
        </div>
        <div className = "error">
           {error &&
                     <label className = "mesajeError">Usuario o password es incorrecto o inexistente</label>

           }
           </div>
        <div className="register">
          <Link to={'/register'}>{Register()}</Link>
        </div>

      </div>
    </div>
  )
}

export default Login