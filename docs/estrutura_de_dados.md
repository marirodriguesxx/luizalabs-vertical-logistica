### Estrutura dos Dados de Entrada

Os dados de entrada seguem a seguinte estrutura:

- **id usuário**: 10 caracteres, numérico
- **nome**: 45 caracteres, texto
- **id pedido**: 10 caracteres, numérico
- **id produto**: 10 caracteres, numérico
- **valor do produto**: 12 caracteres, decimal
- **data compra**: 8 caracteres, numérico (formato: yyyymmdd)

Exemplo de dados de entrada:
```
0000000002 Medeiros00000123450000000111 256.2420201201
0000000001 Zarelli00000001230000000111 512.2420211201
0000000001 Zarelli00000001230000000122 512.2420211201
0000000002 Medeiros00000123450000000122 256.2420201201
```

### Estrutura dos Dados de Saída

Os dados normalizados devem ser retornados no formato JSON, com a seguinte estrutura:
```json
[
  {
    "user_id": 1,
    "name": "Zarelli",
    "orders": [
      {
        "order_id": 123,
        "total": "1024.48",
        "date": "2021-12-01",
        "products": [
          {
            "product_id": 111,
            "value": "512.24"
          },
          {
            "product_id": 122,
            "value": "512.24"
          }
        ]
      }
    ]
  },
  {
    "user_id": 2,
    "name": "Medeiros",
    "orders": [
      {
        "order_id": 12345,
        "total": "512.48",
        "date": "2020-12-01",
        "products": [
          {
            "product_id": 111,
            "value": "256.24"
          },
          {
            "product_id": 122,
            "value": "256.24"
          }
        ]
      }
    ]
  }
]
```