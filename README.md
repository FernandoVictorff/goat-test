# GOAT Test

### Para executar a aplicação é necessário rodar um banco de dados PostgreSQL.

#### Instalação do Docker

Para instalar o Docker, siga as instruções no site oficial:
[Instalação do Docker](https://docs.docker.com/engine/install/)

#### Executar PostgreSQL com Docker

Após instalar o Docker, execute o seguinte comando para rodar o PostgreSQL:

```sh
docker run --name meu_postgres -e POSTGRES_PASSWORD=adminadmin -v meu_volume_postgres:/var/lib/postgresql/data -p 5432:5432 -d postgres:14.0
