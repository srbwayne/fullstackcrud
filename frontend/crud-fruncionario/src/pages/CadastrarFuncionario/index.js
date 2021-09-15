import { Form, Button, message, Layout, Menu, Input } from 'antd'
import {Link} from 'react-router-dom';
import FuncionariosService from '../../service/FuncionariosService';

const { Header, Content, Footer } = Layout;

export default function CadastrarFuncionario(){


    const layout = {
        labelCol: {
          span: 4,
        },
        wrapperCol: {
          span: 3,
        },
      };
      const tailLayout = {
        wrapperCol: {
          offset: 4,
          
        },
      };

    const onFinish = (values) =>{
        FuncionariosService.saveFuncionario(values);
        message.success("Funcionario salvo com sucesso!")
    }

    // const onFinishFailed = (erroInfo) =>{
    //     message.danger("Falha em salvar funcionario!")
    //     console.log('Failed:', erroInfo)
    // }

    return(
        <div className="container">
            <Layout className="layout">
                <Header>
                <div className="logo" />
                <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']}>
                    <Menu.Item key="1">
                        <Link to="/cadastrar-funcionario">
                        Cadastrar Funcionário
                        </Link> 
                    </Menu.Item>
                    <Menu.Item key="2">
                        <Link to="/listar-funcionarios">
                        Listar Funcionário
                        </Link>
                    </Menu.Item>
                </Menu>
                </Header>
                <Content style={{ padding: '0 50px' }}>
                <div className="site-layout-content">
                <h2>CADASTRAR FUNCIONÁRIO</h2>
                <Form  {...layout} name="basic"
                initialValues={{
                remember: true,
                }}
                onFinish={onFinish}
                
                >
                <Form.Item
                    label="matricula"
                    name="matricula"
                    rules={[
                    {
                        required: true,
                        message: 'Insira o código do ativo!',
                    },
                    ]}
                >
                <Input />
                </Form.Item>

                <Form.Item
                    label="Nome do Funcionário"
                    name="nome"
                    rules={[
                    {
                        required: true,
                        min: 2,
                        maxLength: 30,
                        message: 'Insira o nome do Funcionário!',
                    },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Sobrenome do Funcionário"
                    name="sobrenome"
                    rules={[
                    {
                        required: true,
                        message: 'Insira o sobrenome do funcionario!',
                    },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Email"
                    name="email"
                    rules={[
                    {
                        required: true,
                        message: 'Insira o Email do Funcionário!',
                    },
                    ]}
                >
                    <Input type="email" name="user_email" />
                </Form.Item>

                <Form.Item
                    label="Número do NIS (PIS)"
                    name="nis"
                    rules={[
                    {
                        required: true,
                        message: 'Insira o NIS/PIS do Funcionário!',
                    },
                    ]}
                >
                    <Input maxLength='14' />
                </Form.Item>
                
                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">
                    Salvar
                    </Button>
                </Form.Item>
            </Form>  
            </div>
            </Content>
            <Footer style={{ textAlign: 'center' }}>Cud Funcionário ©2021</Footer>
        </Layout>                   
    </div> 
    );
}