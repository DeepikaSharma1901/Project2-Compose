FROM node:18-alpine

WORKDIR /usr/src/app

# Install only runtime deps
COPY package*.json ./
RUN npm install --omit=dev

# App source
COPY . .

# Make it reachable from outside the container
ENV HOST=0.0.0.0 PORT=8080
EXPOSE 8080

CMD ["npm", "start"]
