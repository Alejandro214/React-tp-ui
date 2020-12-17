import axios from 'axios';


const login = (email, password) => axios.post('http://localhost:7000/login', { email: email, password: password });

const register = (email, name, password, imageLink, creditCard) => axios.post('http://localhost:7000/register', { name: name, email: email, password: password, image: imageLink, creditCard: creditCard });

const getUser = () => axios.get('http://localhost:7000/user', { headers: { Authorization: localStorage.getItem('Token') } })

const getContent = () => axios.get('http://localhost:7000/content', { headers: { Authorization: localStorage.getItem('Token') } })

const getBanners = () => axios.get('http://localhost:7000/banners', { headers: { Authorization: localStorage.getItem('Token') } })

const search   = (param) => axios.get(`http://localhost:7000/search?text=${param}`,{ headers: { Authorization: localStorage.getItem('Token') } })

const getContentByid = (id) => axios.get(`http://localhost:7000/content/${id}`, { headers: { Authorization: localStorage.getItem('Token') } })

const postContent = ( id) => axios.post('http://localhost:7000/user/fav', { id: id }, { headers: { Authorization: localStorage.getItem('Token') } })

const lastSeen = (id) => axios.post('http://localhost:7000/user/lastSeen', { id: id }, { headers: { Authorization: localStorage.getItem('Token') } })

export default { login, register,getUser,getContent,getBanners,search,getContentByid,postContent,lastSeen} 
