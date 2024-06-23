# Desafio Técnico - Vertical Logística

Neste desafio, é necessário desenvolver um sistema que integre dois sistemas diferentes. O sistema legado fornece um arquivo de pedidos desnormalizado, e nosso objetivo é transformá-lo em um arquivo JSON normalizado. Esse arquivo JSON deve ser disponibilizado via uma API REST. A entrada do sistema será um arquivo de texto onde cada linha representa uma parte de um pedido, com os campos padronizados por tamanho fixo.

## Índice

1. [Estrutura de Dados de entrada e saída](docs/estrutura_de_dados.md)
2. [Arquitetura Hexagonal](docs/arquitetura_hexagona.md)
3. [Configuração](docs/configuracao.md)
4. [Estrutura de Arquivos do projeto](docs/estrutura_de_arquivos.md)
5. [Desenho da API](docs/desenho_API.md)

## TODO:
1. Implementar salvar json no banco de dados e retornar json salvo no banco de dados nao em memoria
2. implementar filtros em orders 
3. Se possivel, fazer certo!!!!!