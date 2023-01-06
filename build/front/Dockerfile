### STAGE 1: Build ###
FROM node:18.12.1 as build
WORKDIR /usr/src/app
COPY /VaccinationCOVID/package.json /VaccinationCOVID/package-lock.json ./
RUN npm install --force
COPY /VaccinationCOVID .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.23.2
COPY /VaccinationCOVID/nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/VaccinationCOVID /usr/share/nginx/html
EXPOSE 4200