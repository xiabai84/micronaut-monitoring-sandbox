FROM openjdk:17

COPY docker/startup.sh startup.sh
COPY build/libs/micronaut-monitoring-*-all.jar application.jar

RUN chmod a+x startup.sh

EXPOSE 8080

ENV HEAP "256m"

CMD ["/startup.sh"]