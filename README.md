# Guía de ejecución

## Instrucciones para ejecutar la aplicación

- ```bash
  git clone https://github.com/santiagomac/reto_tecnico_metro.git
  ```
Si se tiene un IDE como IntelliJ el proceso de compilación de gradle se hace automático, y su ejecución también es fácil
dandole al botón de **Run**

Si se quiere hacer por medio de terminal seguir los siguientes comando, se debe de tener gradle instalado

- ```bash
  gradle build
  ```
  
- ```bash
  ./gradlew bootRun
  ```
  
## Detalles para levantar los contenedore


El siguiente comando crea los contenedores de mongodb, kafka y kafka-ui un gestor de clusters de kafka.
- ```bash
  cd docker
  docker-compose up -d
  ```

## Ejemplo de cómo enviar transacciones

Recurrir al archivo [swagger](./swagger.yaml)

## Consideraciones asumidas

Se asumió los tipos de las transacciones principalamente deviceNumber y geoPosition, de como se debería
de recibir en la request.

También en el momento de registrar las transacciones en el broker de mensajería se asumió 
el nombre del tópico y que el schema que se va a manejar es JSON.

## Estrategias utilizadas

Se utilizó una arquitectura limpia, basada en el scaffold de Bancolombia, donde se divide cada una de las responsabilidades,
también se utilizó un patrón adaptador para la implementación de la base de datos, para que en el futuro si se cambia 
la base de datos no afecte. Un patrón data mapper para el mapeo de datos.

## Guía de uso
Para más simplicidad se puede usar el archivo [swagger](./swagger.yaml), pero si se quiere hacer uso de otro software
se puede usar Postman y usar la siguiente URL y cuerpo:
- ```http://localhost:8080/api/v1/transaction```

- ```json
  METHOD: "POST,
  {
    "transactionId": "123456",
    "timestamp": "2023-07-26T10:00:00Z",
    "deviceNumber": "12312312",
    "userId": "1231231231231",
    "geoPosition": "123123123123",
    "amount": 12000
  }
```