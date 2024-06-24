### Passo a Passo para Testar Endpoints com Postman

#### Pré-requisitos
- **Java 17**.
- **Maven**.
- **Postman** instalado no seu computador. Se não tiver, baixe e instale através do [site oficial](https://www.postman.com/downloads/).
- **Serviço Backend** rodando localmente na porta 8080.
  
### Execução

Para executar a aplicação, siga os passos abaixo:

1. **Compilar o Projeto**:
   ```sh
   ./mvnw clean install
   ```

2. **Executar a Aplicação**:
   ```sh
   ./mvnw spring-boot:run
   ```

2. **Executar a Testes da Aplicação**:
   ```sh
   ./mvnw clean test
   ```


### Abrir a Collection do Postman

1. Abra o Postman.
2. Clique em "Collections" no menu à esquerda.
3. Clique em "import".
4. Selecione o arquivo postman_collection presente no diretório.

### Testar Requisição de Upload de Arquivo

1. Selecione a collection criada .
    - **Método:** `POST`
    - **URL:** `http://localhost:8080/api/files/upload`
4. Vá até a aba "Body" e selecione a opção "form-data".
5. Adicione um novo campo:
    - **Key:** `file`
    - **Type:** `File`
    - **Value:** Selecione o arquivo .txt do seu computador.
6. Clique em "Send" para enviar a requisição.

### Testar Requisição para Obter JSON completo

1. Duas abordagens foram usadas para o retorno do Json. A primeira consistiu no armazenamento do arquivo txt e tratamento em memória local, o que pode ser muito custoso e os dados não persistem na aplicação.
2. Para testar a primeira solução: dentro da mesma collection, clique em "Get Json".
    - **Método:** `GET`
    - **URL:** `http://localhost:8080/api/files/getJson`
3. Clique em "Send" para enviar a requisição e verificar a resposta.
4. A segunda abordagem, mais completa e correta, consistiu no desenvolvimento de consultas personalizadas ao banco de dados para retornar o json.
5. Para testar a segunda solução: dentro da mesma collection, clique em "get all users with orders and products".
    - **Método:** `GET`
    - **URL:** `http://localhost:8080/api/users/with-orders-and-products`

### Testar Requisição para Obter dados filtrados
1. Para testar o filtro de ordens por id de usuário: dentro da mesma collection, clique em "get orders by user id".
   - **Método:** `GET`
   - **URL:** `http://localhost:8080/api/orders/user/14`
2. Para testar o filtro de ordens por intervalo de data: dentro da mesma collection, clique em "get orders by date range".
   - **Método:** `GET`
   - **URL:** `http://localhost:8080/api/orders/date-range?startDate=2021-11-07&endDate=2021-11-25`
3. Para testar o filtro de ordens por id de ordem: dentro da mesma collection, clique em "get orders by order id".
   - **Método:** `GET`
   - **URL:** `http://localhost:8080/api/orders/875`
4. Para testar o filtro de usuário por id de usuário: dentro da mesma collection, clique em "Get users by Id".
   - **Método:** `GET`
   - **URL:** `http://localhost:8080/api/users/20`