import {BrowserRouter, Switch, Route} from 'react-router-dom'
import CadastrarFuncionario from '../pages/CadastrarFuncionario';
import ListarFuncionarios from '../pages/ListarFuncionarios';


export default function Routes(){
    return(
        <BrowserRouter>
            <Switch>
                <Route exact path="/" component={ListarFuncionarios} />
                <Route exact  path="/cadastrar-funcionario" component={CadastrarFuncionario} />
                <Route exact path="/listar-funcionarios" component={ListarFuncionarios} /> 
            </Switch>
        </BrowserRouter>
    );
}