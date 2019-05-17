import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8080',
});

instance.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8081';


export default  instance;
