import axios from 'axios';

export const gameName = 'JigsawSudoku';

const globalURL = axios.create({
    baseURL: 'http://localhost:3000/api/',
})

export default globalURL;