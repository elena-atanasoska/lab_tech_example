FROM node:20-alpine as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent
RUN npm install react-scripts@5.0.1 -g --silent
COPY . ./

ARG REACT_APP_BACKEND_HOST
ENV REACT_APP_BACKEND_HOST=${BACKEND_CONTAINER_PORT}

EXPOSE 3000
CMD ["npm", "start"]