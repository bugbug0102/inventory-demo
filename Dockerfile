FROM ubuntu:18.04
EXPOSE 80

COPY ./inventory-frontend/dist/inventory-frontend-app /var/www/html/
COPY ./inventory-backend/inventory-backend-app/target/inventory-backend-app-1.0-SNAPSHOT-spring-boot.jar /
COPY ./entrypoint.sh /

RUN apt-get update && \
    apt-get install --no-install-recommends -y nginx && \
    apt-get install --no-install-recommends -y openjdk-8-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    chmod +x ./entrypoint.sh

COPY ./nginx-site.conf /etc/nginx/sites-available
RUN rm -rf /etc/nginx/sites-enabled/default && \
    ln -s /etc/nginx/sites-available/nginx-site.conf /etc/nginx/sites-enabled/nginx-site.conf

ENTRYPOINT ["./entrypoint.sh"]

#docker run -it --rm -p 64300:80 bugbug0102/bugbug0102:inventory-demo-0.0.1