import axios from 'axios';

export default axios.create({
  baseURL: 'https://4de1-71-183-107-211.ngrok-free.app/',
  headers: {"ngrok-skip-browser-warning": "true"}
})
