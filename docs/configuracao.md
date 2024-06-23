### Passo a Passo para Testar Endpoints com Postman

#### Pré-requisitos
- **Java 17**.
- **Maven**.
- **Postman** instalado no seu computador. Se não tiver, baixe e instale através do [site oficial](https://www.postman.com/downloads/).
- **Serviço Backend** rodando localmente na porta 8080.
  
### Passo 1: Execução

Para executar a aplicação, siga os passos abaixo:

1. **Compilar o Projeto**:
   ```sh
   ./mvnw clean install
   ```

2. **Executar a Aplicação**:
   ```sh
   ./mvnw spring-boot:run
   ```


### Passo 2: Abrir a Collection do Postman

1. Abra o Postman.
2. Clique em "Collections" no menu à esquerda.
3. Clique em "import".
4. Selecione o arquivo postman_collection presente no diretório.

### Passo 3: Testar Requisição de Upload de Arquivo

1. Selecione a collection criada .
    - **Método:** `POST`
    - **URL:** `http://localhost:8080/api/files/upload`
4. Vá até a aba "Body" e selecione a opção "form-data".
5. Adicione um novo campo:
    - **Key:** `file`
    - **Type:** `File`
    - **Value:** Selecione o arquivo .txt do seu computador.
6. Clique em "Send" para enviar a requisição.

### Passo 4: Testar Requisição para Obter JSON

1. Dentro da mesma collection, clique em "Get Json".
    - **Método:** `GET`
    - **URL:** `http://localhost:8080/api/files/getJson`
2. Clique em "Send" para enviar a requisição e verificar a resposta.