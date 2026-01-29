FROM node:14
LABEL Victor Hugo

COPY . /var/www
WORKDIR /var/www/app

RUN npm install -g @angular/cli
RUN npm install

EXPOSE 4200
ENTRYPOINT ng serve --host 0.0.0.0 --disableHostCheck
