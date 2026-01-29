require('dotenv').config()

const express = require('express')
const routes = require('./src/routes')

const app = express()
app.use(express.json())
routes.config(app)

console.log('init server')
app.listen(process.env.NODE_PORT)
