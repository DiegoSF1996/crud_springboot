# CRUD em java com o Spring Framework
Esse pequeno CRUD foi desenvolvido para fins de estudo e consulta.
A aplicação foi conteinerizada, para utilizá-la basta digitar:

```docker-compose up -d ```


## Exemplo de uso

#### Cadastra um novo produto

```http
  POST http://localhost:8080/products
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **NOT BLANK**. O nome do produto |
| `value` | `bigint` | **NOT NULL**. Valor do produto |
