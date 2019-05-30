import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:8080',
});

instance.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8080';
instance.defaults.headers = {
  'Access-Control-Allow-Origin': 'http://localhost:8080/dashboard'

};

export default  instance;
