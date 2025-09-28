require('dotenv').config()
const express = require('express')

const service = require('./src/service/user')

const app = express()
app.use(express.json())

app.get('/', (req, resp) => {
    resp.json({mensagem: 'olha ai!'})
})

app.get('/user', async (req, resp) => {
    resp.json(await service.find())
})

console.log('init server')
app.listen(process.env.NODE_PORT)
