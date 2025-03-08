
# Agendador de Notificações

Esse projeto foi desenvolvido para um desafio técnico visando apresentar habilidades de desenvolvedor java backend e tem como escopo o cadastro de notificações para posterior envio.  
Obs: O projeto foi resolvido por Angélica Weiler do canal [Javanauta](https://www.youtube.com/@javanauta), e apenas replicado por mim como forma de estudo.


## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/JefersonFreire/agendamento-notificacao-api.git
```

Entre no diretório do projeto

```bash
  docker-compose up --build
```


## Documentação da API

#### Cadastra notificações pendentes

```http
  POST /agendamento
```

| Body   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `emailDestinatario` | `string` | **Obrigatório**. Email do destinatário da notificação |
| `telefoneDestinatario` | `string` | **Obrigatório**. Telefone do destinatário da notificação |
| `mensagem` | `string` | **Obrigatório**. A mensagem da notificação |
| `dataHoraEvento` | `LocalDateTime` | **Obrigatório**. Data hora do evento no formato dd-MM-yy HH:mm:ss|

#### Retorna uma notificação por id

```http
  GET /agendamento/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você quer buscar |

```http
  DELETE /agendamento/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você quer cancelar|


## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  mvn test
```

