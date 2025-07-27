# Estágio 1: Build da Aplicação com Maven e Java 24
# Usamos uma imagem oficial com Maven e a versão 24 do JDK (Eclipse Temurin)
FROM maven:3-eclipse-temurin-24 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para o contêiner
# Isso é feito primeiro para aproveitar o cache de dependências do Docker
COPY pom.xml .

# Baixa todas as dependências do projeto
RUN mvn dependency:go-offline

# Copia todo o código-fonte do seu projeto
COPY src ./src

# Compila o projeto, cria o pacote .jar e pula os testes para um build mais rápido
# O resultado será salvo em /app/target/
RUN mvn clean package -DskipTests


# Estágio 2: Execução da Aplicação em um ambiente leve
# Usamos uma imagem base leve, apenas com o Java 24 (JRE) necessário para rodar
FROM eclipse-temurin:24-jre

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio de build para este novo contêiner
# O nome do JAR é baseado no seu pom.xml (artifactId-version.jar)
COPY --from=build /app/target/agendapro-0.0.1-SNAPSHOT.jar ./app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot
EXPOSE 8080

# Comando final que será executado quando o contêiner iniciar
# Ele simplesmente executa a sua aplicação Java
ENTRYPOINT ["java", "-jar", "app.jar"]
