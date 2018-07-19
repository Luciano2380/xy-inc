# xy-inc
1) Clonar o projeto no GitHub
2) Após clonar o projeto, abrir o projeto com as Ide Eclipse ou Spring Tool Suite
3) Após abrir o projeto, faça build, botão direito do mouse sobre o projeto, depois Run As -> Maven Install.
4) Para executar o projeto vá em Run -> xy-inc XyIncApplication ou Run As -> Spring Boot App

Após executar o projeto as url estão disponiveis

POST
http://localhost:8080/pois

GET
http://localhost:8080/pois/listarTodos

GET: parametros: coordx, coordy,d_max
http://localhost:8080/pois/listarProximidade?coord_x=20&coord_y=10&d_max=10

Url do banco de dados H2
http://localhost:8080/h2
Username: xy
Password: 1234

5) Para executar o teste JUnit, botão direito do mouse sobre o projeto, depois Run As -> JUnit Test


