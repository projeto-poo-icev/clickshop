# Usar uma imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

CMD [ "./gradlew build -x test" ]

# Copia o arquivo JAR para o diretório de trabalho
COPY build/libs/clickshop-0.0.1-SNAPSHOT.jar app.jar


# Expõe a porta que a aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
