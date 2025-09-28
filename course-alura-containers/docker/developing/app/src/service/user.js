const util = require('util')
const Redis = require('ioredis')

const user = require('./../database/user')

const clientRedis = new Redis(
    process.env.REDIS_PORT,
    process.env.REDIS_HOST
)

let getByCache = async () => {
    let get = util.promisify(clientRedis.get).bind(clientRedis)

    try {
        let data = await get('users')
        if (data) {
            return JSON.parse(data)
        }
    } catch(err) {
        console.error(err)
    }

    return []
}

let setCache = (list) => {
    clientRedis.set('users', JSON.stringify(list))
}

let findUser = async () => {
    let list = await getByCache()

    if (list.length !== 0) {
        console.log('cached used!')
        return list
    }

    console.log('without cache')
    list = await user.find()

    setCache(list)
    return list
}

module.exports = {
    find: findUser
}