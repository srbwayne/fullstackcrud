import axios from 'axios';

const API_URL = 'http://localhost:8080/api/v1'

class FuncionarioService{

    retrieveAllFuncionarios(){
        return axios.get(`${API_URL}/funcionarios`)
    }

    saveFuncionario(funcionario){
        return axios.post(`${API_URL}/funcionarios`, funcionario)
    }

    deleteFuncionario(codigo){
        return axios.delete(`${API_URL}/funcionarios/${codigo}`)
    }

    updateFuncionario(funcionario){
        return axios.put(`${API_URL}/funcionarios`, funcionario)
    }


}

export default new FuncionarioService();