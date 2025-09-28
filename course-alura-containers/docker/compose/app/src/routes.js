const service = require('../src/service/user')

let working = (req, resp) => {
    resp.json({mensagem: 'it working'})
}

let listUsers = async (req, resp) => {
    let lista = await service.find()
    resp.json(lista)
}

module.exports = {
    config(app) {
        app.get('/', working)
        app.get('/user', listUsers)
    }
}
