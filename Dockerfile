FROM openjdk:11-jre-slim
RUN apt-get update && apt-get install -y iputils-ping telnet less net-tools
ENV TZ=Africa/Johannesburg
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY target/usermanagement-1.0.1.jar /opt/apps/usermanagement-1.0.1.jar
COPY app_settings/application.properties /opt/apps/config/application.properties
ENTRYPOINT ["java","-jar","/opt/apps/usermanagement-1.0.1.jar","--spring.config.location=file:/opt/apps/config/application.properties"]
