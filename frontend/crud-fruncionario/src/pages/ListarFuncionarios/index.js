import "antd/dist/antd.css";
import { Table, Button, message, Layout, Menu } from 'antd';
import {Link} from 'react-router-dom';
import {useState, useEffect} from 'react'
import FuncionariosService from "../../service/FuncionariosService";

const { Header, Content, Footer } = Layout;
const {Column} = Table;


export default function ListarFuncionarios(){ 

    const [funcionarios, setFuncionarios] = useState([]);

    useEffect(() => {
        refreshFuncionarios();
        return () => {      
        }
    }, [])

    async function refreshFuncionarios(){
        FuncionariosService.retrieveAllFuncionarios()
            .then(
                response => {
                    setFuncionarios(response.data)
                }
            
        )
    }

    function remove(record){
        FuncionariosService.deleteFuncionario(record.id)
        message.success('Funcionario removido com sucesso!');
    }

    return (
        <div className="container">
        <Layout className="layout">
            <Header>
            <div className="logo" />
            <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
                <Menu.Item key="1">
                    <Link to="/cadastrar-funcionario">
                    Cadastrar Funcionario
                    </Link> 
                </Menu.Item>
                <Menu.Item key="2">
                    <Link to="/listar-Funcionarios">
                    Listar Funcionarios
                    </Link>
                </Menu.Item>
            </Menu>
            </Header>
            <Content style={{ padding: '0 50px' }}>
            <div className="site-layout-content">
                <h2>FUNCIONARIOS</h2>
                {/* - Nome (Entre 2 e 30 caracteres)
                - Sobrenome (Entre 2 e 50 caracteres)
                - e-mail (Validar e-mail)
                - Número do NIS (PIS) (Somente números) */}
                    <Table dataSource={funcionarios}>
                        <Column title="id" dataIndex="id" key="id"/>
                        <Column title="Matricúla" dataIndex="matricula" key="matricula"/>
                        <Column title="Nome do Funcionário" dataIndex="nome" key="nome"/>
                        <Column title="Sobrenome do Funcionário" dataIndex="sobrenome" key="sobrenome"/>
                        <Column title="Email" dataIndex="email" key="email"/>
                        <Column title="Número do NIS (PIS)" dataIndex="nis" key="nis"/>
                        <Column title="Remover" key="atualizar"
                            render={(record) => (<Button onClick={() => remove(record)} 
                            type="primary">Remover</Button>)}
                        />
                            
                    </Table>
            </div>
            </Content>
            <Footer style={{ textAlign: 'center' }}>Crud Funcionario ©2021</Footer>
        </Layout>
        </div>
    );

}