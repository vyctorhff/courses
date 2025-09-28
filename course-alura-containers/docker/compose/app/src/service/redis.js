const util = require('util')
const Redis = require('ioredis')

const clientRedis = new Redis(
    process.env.REDIS_PORT,
    process.env.REDIS_HOST
)

class RedisService {

    async getByCache() {
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

    setCache(list) {
        clientRedis.set('users', JSON.stringify(list))
    }
}

module.exports = new RedisService()
